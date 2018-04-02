package Departments;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class DepartmentTest {
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjY1Mjg3OSwibmJmIjoxNTIyNjUyODc5LCJwcm9tb3Rlcl9pZCI6IjI4IiwiZXhwIjoxNTIyNzM5Mjc5LCJidXNpbmVzc2VzIjpbeyJpZCI6MzksImFkZHJlc3NlcyI6WzEyMSwxMzMsMTUzLDE4NCwxODVdfV19.VZTN7R1q7lNwA5ZhY5A9hvJ93qILJtfjlgO9orOYIlPdv8ANv0Q1ioOLKTAbrBw1bezHW47FogCX6yDDoZRMgdP5MDgUCIMdbsiqrXfECRX2oNWiEcVHHLdltEfwECx9iHn4svtpncVHSWldg7KW2WiQzd_F2cgESuERhAhM1g295Ab2KFgAD25eKJT0hNNd5yQqIIrl7BqGgxZWYDPcvPGBP1Zf2SKyKpYM9a-41q4ZDJZ0znJ2EnDgx68_A6ARIPDeMMwku_EtVOLs264deNhELkPs_xNfB7Mr3o4W2g4yfdQc20Kt9exgLN9GIL_wWwVpwIsN36qvhlakmcCbeg";
    private  String baseURL = "http://staging.eservia.com:8009/api/v0.0/Departments";
    String name = (LocalTime.now()).toString();
    int Ids;
    DepartmentData departmentsData = new DepartmentData();

    @Test
    public void A_GreateDepartment() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(departmentsData.CreatePreparingDepartments(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentResponse.class);
        Department department = departmentResponse.data;
        this.Ids = department.getId();
        Assert.assertEquals(1,department.getTypeId());
        Assert.assertEquals(false,department.isMain());
        Assert.assertEquals(true,department.isActive());
        Assert.assertEquals(121,department.getAddressId());
        Assert.assertEquals(name,department.getName());
    }

    @Test
    public void B_UpdateDepartment() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(departmentsData.UpdateDepartment(name,Ids))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL).thenReturn().body();
        DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentResponse.class);
        Department department = departmentResponse.data;
        this.Ids = department.getId();
        Assert.assertEquals(2,department.getTypeId());
        Assert.assertEquals(false,department.isMain());
        Assert.assertEquals(true,department.isActive());
        Assert.assertEquals(121,department.getAddressId());
        Assert.assertEquals(name,department.getName());
    }

    @Test
    public void C_deactivateDepartment(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+"/"+Ids+"/Deactivate");
        DepartmentErrors departmentResponse = new Gson().fromJson(response.asString(),  DepartmentErrors.class);
        Assert.assertEquals(true,departmentResponse.isSuccess());
        Assert.assertEquals(true,departmentResponse.isData());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        DepartmentResponse departmentResponse1 = new Gson().fromJson(responseBody.asString(),DepartmentResponse.class);
        Department department = departmentResponse1.data;
        Assert.assertEquals(false,department.isActive());
    }

    @Test
    public void D_activateDepartment(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+"/"+Ids+"/Activate");
        DepartmentErrors departmentResponse = new Gson().fromJson(response.asString(),  DepartmentErrors.class);
        Assert.assertEquals(true,departmentResponse.isSuccess());
        Assert.assertEquals(true,departmentResponse.isData());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        DepartmentResponse departmentResponse1 = new Gson().fromJson(responseBody.asString(),DepartmentResponse.class);
        Department department = departmentResponse1.data;
        Assert.assertEquals(true,department.isActive());
    }

    @Test
    public void F_DeleteDepartment(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+"/"+Ids);
        DepartmentErrors departmentResponse = new Gson().fromJson(response.asString(),  DepartmentErrors.class);
        Assert.assertEquals(true,departmentResponse.isSuccess());
        Assert.assertEquals(true,departmentResponse.isData());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        DepartmentErrors departmentResponse1 = new Gson().fromJson(responseBody.asString(),DepartmentErrors.class);
        Department department = departmentResponse1.error;
        Assert.assertEquals("Department does not exist",department.getErrorDescription());
        Assert.assertEquals("DepartmentsService.Service",department.getErrorSource());
    }

    @Test
    public void G_CheckSellingDepartment(){
        String CheckSelling = "http://staging.eservia.com:8009/api/v0.0/Departments/Selling?addressId=23";
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(CheckSelling);
        DepartmentListResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentListResponse.class);


        for (Department department : departmentResponse.getData()) {
            Assert.assertEquals(2, department.getTypeId());
            Assert.assertEquals(23, department.getAddressId());
        }
    }
    public int getId(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(departmentsData.CreatePreparingDepartments(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentResponse.class);
        Department department = departmentResponse.data;
        int id = department.getId();
        return  id;
    }

}
