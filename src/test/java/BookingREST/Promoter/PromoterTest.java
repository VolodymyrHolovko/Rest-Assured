package BookingREST.Promoter;

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

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class PromoterTest {
    String token;
    String baseURI = "https://staging.eservia.com:8083/api/v1.0/promoters/";
    Faker faker = new Faker();
    char rсc = (char)('A' + new Random().nextInt(28));
    String firstNameUpdate = faker.name().firstName()+faker.name().firstName().toLowerCase()+faker.name().firstName()+"a";
    String lastNameUpdate = faker.name().lastName()+faker.name().firstName().toLowerCase()+faker.name().firstName()+"q";
    String emailUpdate = faker.name().firstName().hashCode()+faker.name().firstName()+"@dmail.com";
    String phoneUpdate = faker.regexify("+380[0-9]{9}");
    String firstName = faker.name().firstName()+faker.name().firstName().toLowerCase()+faker.name().firstName();
    String lastName = faker.name().lastName()+faker.name().firstName().toLowerCase()+faker.name().firstName();
    String email = faker.name().firstName().hashCode()+faker.name().firstName()+"@dmail.com";
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
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/U82NZUzFnOvCzOSf.jpg", addPromoter.getPhoto());
        Assert.assertEquals(1, addPromoter.getStatus());
    }

    @Test
    public void B_updatePromoter() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.updatePromoters(firstNameUpdate, lastNameUpdate, emailUpdate, phoneUpdate))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI + id + "/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter updatePromoter = promoterResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals(firstNameUpdate, updatePromoter.getFirst_name());
        Assert.assertEquals(lastNameUpdate, updatePromoter.getLast_name());
        Assert.assertEquals(emailUpdate, updatePromoter.getEmail());
        Assert.assertEquals(phoneUpdate, updatePromoter.getPhone());
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/5CMjoBr5A3tvTHRv", updatePromoter.getPhoto());
        Assert.assertEquals(1, updatePromoter.getStatus());

    }
    @Test
    public void C_changePromotersPassword() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.changePasswords())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI + id + "/" + "password").thenReturn().body();
        System.out.println(response.asString());

    }
    @Test
    public void D_deactivatePromoter() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI + id + "/" + "deactivate" + "/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter activatePromoter = promoterResponse.data;
        Assert.assertEquals(0, activatePromoter.getStatus());
    }
    @Test
    public void E_activatePromoter() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI + id + "/" + "activate" + "/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter activatePromoter = promoterResponse.data;
        Assert.assertEquals(1, activatePromoter.getStatus());
    }
    @Test
    public void F_getPromoterId() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI + id + "/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter getPromoter = promoterResponse.data;
        Assert.assertEquals(id, getPromoter.getId());
        Assert.assertEquals(firstNameUpdate, getPromoter.getFirst_name());
        Assert.assertEquals(lastNameUpdate, getPromoter.getLast_name());
        Assert.assertEquals(emailUpdate, getPromoter.getEmail());
        Assert.assertEquals(phoneUpdate, getPromoter.getPhone());
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/5CMjoBr5A3tvTHRv", getPromoter.getPhoto());
        Assert.assertEquals(1, getPromoter.getStatus());
    }

    @Test
    public void G_deletePromoter() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + id + "/").thenReturn().body();
        System.out.println(response.asString());
    }
}
