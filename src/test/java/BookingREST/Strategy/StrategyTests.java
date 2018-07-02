package BookingREST.Strategy;

import BookingREST.AuthBusiness.AuthBusinessTest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class StrategyTests {
    Faker faker = new Faker();
    String baseURI = "http://staging.eservia.com:8083/api/v1.0/strategies/";
    String name = faker.name().firstName().toLowerCase()+faker.name().firstName().toLowerCase();
    String collectParametr = "collect/?id=" + name;
    String token;
    public int id;
    StrategyData strategyData = new StrategyData();

    @BeforeClass
    public  void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
    }
    @Test
    public void A_createStrategy() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(strategyData.addPromoters(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(response.asString(), StrategyResponse.class);
        Strategy addStrategy = strategyResponse.data;
        System.out.println(response.asString());
        this.id = addStrategy.getId();
        Assert.assertEquals(name, addStrategy.getStrategy());
        Assert.assertEquals(1, addStrategy.getStatus());
        Assert.assertEquals(id, addStrategy.getId());
    }
    @Test
    public void B_deactivateStrategy() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI + id + "/" + "deactivate" + "/").thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(response.asString(), StrategyResponse.class);
        Strategy activateStrategy = strategyResponse.data;
        Assert.assertEquals(0, activateStrategy.getStatus());
    }

    @Test
    public void C_activateStrategy() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI + id + "/" + "activate" + "/").thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(response.asString(), StrategyResponse.class);
        Strategy activateStrategy = strategyResponse.data;
        Assert.assertEquals(1, activateStrategy.getStatus());
    }
    @Test
    public void C_getStrategyId() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI + id + "/").thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(response.asString(), StrategyResponse.class);
        Strategy getStrategyId = strategyResponse.data;
        Assert.assertEquals(name, getStrategyId.getStrategy());
        Assert.assertEquals(1, getStrategyId.getStatus());
        Assert.assertEquals(id, getStrategyId.getId());
    }

    @Test
    public void deleteStrategy() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + id + "/").thenReturn().body();
        System.out.println(response.asString());
    }
}
