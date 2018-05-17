package _BookingREST.Promoter;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class PromoterTest {
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhZG1pbiIsImlhdCI6MTUyNjU1NzQ0OCwiZXhwIjoxNTI2NTYxMDQ4LCJuYmYiOjE1MjY1NTc0NDgsImp0aSI6IlVYTnhITnBHU0k5dVJNaW8iLCJzdWIiOjEsInBydiI6IjRjOGY3MmM2NmVhOTUxYzViNDA4NDRjYjE0MjAxNDdkNWY3NDk3YzMifQ.Jd9XcZR-MDBVCPjb_BMCRqpfYjGTEdHAguQGWZRbDfeglyEkIKiVwMS41Yo4QPTRAlWMBBSnIRH8K52GS-B-2wKekF9Evsks59eEX-NyGW5-rtsaRgNRCuC_cxMaRQb_f3-IPv637P0AOWlGFhzk214ZAkd846kzuNtqjxLx-j27DC8Oh3-JHvNsk2Dz2tIX1qYTG-ATuQMb7jYchyODu_CDySMe4QxrV4LxvJH77vdGkLIa9EhZ_BsoALIVYsyV6KDHVrxFbOpILxtolz211lWd---mH_Q4MaZzghW8lgbhMn-RUBQxqeu9gqBh2lC9pOdItp0dbUX4Wr6w92k5eg";
    String baseURI = "http://213.136.86.27:8083/api/v1.0/promoters/";
    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.name().firstName()+"@mail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    public int id;
    PromoterData promoterData = new  PromoterData();

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
