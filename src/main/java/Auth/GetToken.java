package Auth;

import Departments.Tables.TableResponse;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetToken {
    AuthData authData = new AuthData();
    String token;
    String sessionId;

String baseURL = "http://staging.eservia.com:8003/api/v0.0/Auth/";
@Test
    public String GetFinallyToken() {
        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .body(authData.password())
                .when().post(baseURL+"Password").thenReturn().body();
        AuthResponse authResponse = new Gson().fromJson(responseBody.asString(),  AuthResponse.class);
        Auth auth = authResponse.data;
        this.token = auth.getToken();
        this.sessionId = auth.getSessionId();

        ResponseBody responseBody1 = given().
                contentType(ContentType.JSON)
                .header("Authorization", "Bearer "+token)
                .body(authData.getToken(sessionId))
                .when().post(baseURL+"ChoosePosition").thenReturn().body();
        AuthResponse authResponse1 = new Gson().fromJson(responseBody1.asString(),  AuthResponse.class);
        Auth auth1 = authResponse1.data;
        String token1 = "Bearer "+auth1.getToken();
        return token1;
    }




    }

