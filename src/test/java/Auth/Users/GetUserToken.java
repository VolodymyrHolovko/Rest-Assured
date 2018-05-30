package Auth.Users;

import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetUserToken {
    AuthUserData authUserData = new AuthUserData();
    public String token;
    String baseURL = "http://auth.staging.eservia.com/api/v0.0/Auth/SignIn";

    public String GetUserToken() {
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .body(authUserData.signIn())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        AuthUserResponse authUserResponse = new Gson().fromJson(responseBody.asString(), AuthUserResponse.class);
        AuthUser authUser = authUserResponse.data;
        this.token = authUser.getToken();
        String userToken = "Bearer " + authUser.getToken();
        return userToken;
    }

    public String GetUserId() {
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .body(authUserData.signIn())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        AuthUserResponse authUserResponse = new Gson().fromJson(responseBody.asString(), AuthUserResponse.class);
        AuthUser authUser = authUserResponse.data;
        String userId = authUser.getUserId();
        return userId;
    }
}
