package DeliveryBack.Tokens;

import Position.Position;
import Position.PositionResponse;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;

import static com.jayway.restassured.RestAssured.given;

public class TokensCreator {
    String baseURL = "https://staging.eservia.com:8004/api/Tokens";
    TokensData data = new TokensData();

    public String getLogisticsToken(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .body(data.getLogisticsToken())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        TokensResponse tokensResponse= new Gson().fromJson(response.asString(),  TokensResponse.class);
        Tokens tokens= tokensResponse.data;
        String logisToken = tokens.getTokens().get(0);
        return logisToken;
    }

    public String getAdminsToken(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .body(data.getAdminToken())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        TokensResponse tokensResponse= new Gson().fromJson(response.asString(),  TokensResponse.class);
        Tokens tokens= tokensResponse.data;
        String adminToken = tokens.getTokens().get(0);
        return adminToken;
    }

    public String getSuperAdminToken(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .body(data.getSuperAdminToken())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        TokensResponse tokensResponse= new Gson().fromJson(response.asString(),  TokensResponse.class);
        Tokens tokens= tokensResponse.data;
        String superAdminToken = tokens.getTokens().get(0);
        return superAdminToken;
    }

    public String getCourierToken(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .body(data.getCourier())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        TokensResponse tokensResponse= new Gson().fromJson(response.asString(),  TokensResponse.class);
        Tokens tokens= tokensResponse.data;
        String courierToken = tokens.getTokens().get(0);
        return courierToken;
    }

}
