package token;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.json.simple.JSONObject;

import static com.jayway.restassured.RestAssured.given;

public class GetToken {

    public String GetToken() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", "cadmin@eservia.com");
        requestParams.put("password", "manager");
        requestParams.put("deviceId", "string");
        requestParams.put("authTypeId", 1);
        ResponseBody response = given().contentType(ContentType.JSON).body(requestParams).when().post("http://cluster.test.eservia.com/api/v0.0/Authorize").thenReturn().body();
        String token = response.jsonPath().get("data.token");
        String token1 = "Bearer " + token;
        return token1;
    }
}
