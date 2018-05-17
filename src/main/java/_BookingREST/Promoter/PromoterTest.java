package _BookingREST.Promoter;

import _BookingREST.AuthBusiness.AuthBusinessTest;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class PromoterTest {
    String token;
    String baseURI = "http://213.136.86.27:8083/api/v1.0/promoters/";
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.name().firstName()+"@mail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    public int id;
    PromoterData promoterData = new  PromoterData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
    }

    @Test
    public void A_createPromoter() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.addPromoters(firstName, lastName, email, phone))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter addPromoter = promoterResponse.data;
        System.out.println(response.asString());
        this.id = addPromoter.getId();
        Assert.assertEquals(id, addPromoter.getId());
        Assert.assertEquals(firstName, addPromoter.getFirst_name());
        Assert.assertEquals(lastName, addPromoter.getLast_name());
        Assert.assertEquals(phone, addPromoter.getPhone());
        Assert.assertEquals(email, addPromoter.getEmail());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/3YnaXlHgoss1oryE.jpg", addPromoter.getPhoto());
        Assert.assertEquals(1, addPromoter.getStatus());
    }
    @AfterClass
    public void deletePromoter() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + id + "/").thenReturn().body();
        System.out.println(response.asString());
    }
}
