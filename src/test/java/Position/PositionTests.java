package Position;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.given;


public class PositionTests {

    private  String token;
    private  String baseURL = "http://staging.eservia.com:8003/api/v0.0/Positions";
    String name = (LocalTime.now()).toString();
    int Ids;
    PositionData positionData = new PositionData();

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }

    @Test
    public void A_CreatePosition(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(positionData.CreatePosition(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        PositionResponse positionResponse = new Gson().fromJson(response.asString(),  PositionResponse.class);
        Position position = positionResponse.data;
        this.Ids = position.getId();
        Assert.assertEquals(name,position.getName());
        Assert.assertEquals("TestShortName",position.getShortName());
        Assert.assertEquals("TestComment",position.getComment());
        Assert.assertEquals("Salary",position.getScheduleType());
        Assert.assertEquals(700,position.getSalary());
        Assert.assertEquals(800 ,position.getPrepaidExpense());
    }

    @Test
    public void B_UpdatePosition(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(positionData.UpdatePosition(name,Ids))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL).thenReturn().body();
        PositionResponse positionResponse = new Gson().fromJson(response.asString(),  PositionResponse.class);
        Position position = positionResponse.data;
        this.Ids = position.getId();
        Assert.assertEquals(name,position.getName());
        Assert.assertEquals("OtherTestShortname",position.getShortName());
        Assert.assertEquals("OtherTestComment",position.getComment());
        Assert.assertEquals("Free",position.getScheduleType());
        Assert.assertEquals(400, position.getSalary());
        Assert.assertEquals(900, position.getPrepaidExpense());
        }

    @Test
    public void C_getIdPosition(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids).thenReturn().body();
        PositionResponse positionResponse = new Gson().fromJson(response.asString(),  PositionResponse.class);
        Position position = positionResponse.data;
        this.Ids = position.getId();
        Assert.assertEquals(name,position.getName());
        Assert.assertEquals("OtherTestShortname",position.getShortName());
        Assert.assertEquals("OtherTestComment",position.getComment());
        Assert.assertEquals("Free",position.getScheduleType());
        Assert.assertEquals(400, position.getSalary());
        Assert.assertEquals(900, position.getPrepaidExpense());
    }

    @Test
    public void getAllPosition(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://staging.eservia.com:8003/api/v0.0/Positions");
        Assert.assertEquals(200,response.getStatusCode());
    }
    @Test
    public void D_GetPosition(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL).thenReturn().body();
        PositiondBoolean positiondBoolean = new Gson().fromJson(response.asString(),  PositiondBoolean.class);
        Assert.assertEquals(null,positiondBoolean.getDescription());
        Assert.assertEquals(null,positiondBoolean.getError());
        Assert.assertEquals(true,positiondBoolean.isSuccess());

    }
    @Test
    public void E_DeletePosition(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+"/"+Ids).thenReturn().body();
        PositionErrors positionResponse = new Gson().fromJson(response.asString(),  PositionErrors.class);
        Assert.assertEquals(true,positionResponse.isSuccess());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        PositionErrors positionResponse1 = new Gson().fromJson(responseBody.asString(),PositionErrors.class);
        Position position = positionResponse1.error;
        Assert.assertEquals("Position does not exist",position.getErrorDescription());
        Assert.assertEquals("PersonnelService.Personnel",position.getErrorSource());


    }




}
