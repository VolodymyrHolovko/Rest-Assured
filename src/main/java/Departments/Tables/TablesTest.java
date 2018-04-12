package Departments.Tables;

import Departments.DepartmentTest;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Auth.GetToken;

import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.given;

public class TablesTest {
    TablesData tablesData = new TablesData();
    String token;
    String code = LocalTime.now().toString();
    int DepartmentId;
    int TableId;
    String baseURL = "http://staging.eservia.com:8009/api/v0.0/Tables";

    @BeforeClass
    public void getDepartmentId(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        DepartmentTest departmentTest = new DepartmentTest();
        this.DepartmentId = departmentTest.getId();
}

    @Test
    public void A_createTable(){
    ResponseBody response = given()
            .contentType(ContentType.JSON)
            .header("Authorization", token)
            .body(tablesData.createTable(DepartmentId,code))
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when().post(baseURL).thenReturn().body();
    TableResponse tableResponse= new Gson().fromJson(response.asString(),  TableResponse.class);
    Tables tables = tableResponse.data;
    this.TableId = tables.getId();
    Assert.assertEquals(code,tables.getCode());
    Assert.assertEquals(true,tables.isBookingAvailable());
    Assert.assertEquals(DepartmentId,tables.getDepartmentId());
    Assert.assertEquals(3,tables.getCapacity());
}

    @Test
    public void B_updateTable(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(tablesData.updateTable(DepartmentId,code,TableId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL).thenReturn().body();
        TableResponse tableResponse= new Gson().fromJson(response.asString(),  TableResponse.class);
        Tables tables = tableResponse.data;
        this.TableId = tables.getId();
        Assert.assertEquals(code,tables.getCode());
        Assert.assertEquals(false,tables.isBookingAvailable());
        Assert.assertEquals(DepartmentId,tables.getDepartmentId());
        Assert.assertEquals(2,tables.getCapacity());
        Assert.assertEquals(TableId,tables.getId());
    }

    @Test
    public void C_setbeaconId(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(tablesData.setBeaconId(TableId,code))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+"/SetBeacon").thenReturn().body();
        TableResponse tableResponse= new Gson().fromJson(response.asString(),  TableResponse.class);
        Tables tables = tableResponse.data;
        Assert.assertEquals(TableId,tables.getId());
        Assert.assertEquals(code,tables.getBeaconId());
    }

    @Test
    public void D_getTableId() {
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get(baseURL + "/" + TableId).thenReturn().body();
        TableResponse tableResponse = new Gson().fromJson(responseBody.asString(), TableResponse.class);
        Tables tables = tableResponse.data;
        Assert.assertEquals(code, tables.getCode());
        Assert.assertEquals(DepartmentId, tables.getDepartmentId());
        Assert.assertEquals(TableId, tables.getId());
        Assert.assertEquals(false, tables.isBookingAvailable());
        Assert.assertEquals(code, tables.getBeaconId());
    }
    @Test
    public void E_getTableByBeaconId(){
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get(baseURL+"/Beacon/"+code).thenReturn().body();
        TableResponse tableResponse = new Gson().fromJson(responseBody.asString(),TableResponse.class);
        Tables tables = tableResponse.data;
        Assert.assertEquals(code,tables.getCode());
    }
    @Test
    public void F_deleteTableId(){
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().delete(baseURL+"/"+TableId).thenReturn().body();
        ResponseBody responseBody1= given().
                contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get(baseURL+"/"+TableId).thenReturn().body();
        TableResponse tableResponse = new Gson().fromJson(responseBody1.asString(),TableResponse.class);
        Tables tables = tableResponse.error;
        Assert.assertEquals("Table does not exist",tables.getErrorDescription());
    }
}
