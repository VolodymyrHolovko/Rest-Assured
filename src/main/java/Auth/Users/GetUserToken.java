package Auth.Users;

import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetUserToken {
    AuthUserData authUserData = new AuthUserData();
    String tokenCustomer;
    String baseURL = "https://auth.staging.eservia.com/api/v0.0/Auth/SignIn";

    @Test
    public String GetUserToken() {
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .body(authUserData.signIn())
                .when().post(baseURL).thenReturn().body();
        AuthUserResponse authUserResponse = new Gson().fromJson(responseBody.asString(), AuthUserResponse.class);
        AuthUser authUser = authUserResponse.data;
        this.tokenCustomer = authUser.getToken();
        String userToken = "Bearer " + authUser.getToken();
        return userToken;
    }
}
