package BookingREST.AuthBusiness;

import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AuthBusinessTest {
    String baseURL = "http://staging.eservia.com:8082/api/v1.0/auth/sign-in/";
    String token;
    AuthBusinessData authBusinessData = new AuthBusinessData();

    @Test
    public String GetAdminToken() {
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .body(authBusinessData.authBisiness())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();

        AuthBusinessResponse authBusinessResponse = new Gson().fromJson(responseBody.asString(), AuthBusinessResponse.class);
        AuthBusiness authBusiness = authBusinessResponse.data;
        this.token = authBusiness.getAccess_token();
        String adminToken = "Bearer " + token;
        return  adminToken;

    }
}
