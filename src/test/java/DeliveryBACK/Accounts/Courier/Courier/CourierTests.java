package DeliveryBACK.Accounts.Courier.Courier;

import DeliveryBACK.Tokens.Tokens;
import DeliveryBACK.Tokens.TokensCreator;
import Departments.Department;
import Departments.DepartmentResponse;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CourierTests {
    String baseURL = "https://staging.eservia.com:8004/api/Accounts/courier";
    String token;
    CourierData courierData = new CourierData();

    @BeforeClass
    public void setup(){
        TokensCreator tokens = new TokensCreator();
        this.token = tokens.getSuperAdminToken();
    }

    @Test
    public void createCoutrier(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(courierData.createCourier())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        CourierResponse courierResponse= new Gson().fromJson(response.asString(),  CourierResponse.class);
        Courier courier= courierResponse.data;
        Assert.assertEquals("test",courier.getUserData().get(0).getFirstName());
    }
}
