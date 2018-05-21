package BookingREST.Businesses;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Promoter.Promoter;
import BookingREST.Promoter.PromoterData;
import BookingREST.Promoter.PromoterResponse;
import BookingREST.Sector.Sector;
import BookingREST.Sector.SectorData;
import BookingREST.Sector.SectorResponse;
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

public class BusinessesTests {
    String token;
    SectorData sectorData = new SectorData();
    BusinesessData businesessData = new BusinesessData();
    PromoterData promoterData = new PromoterData();
    String baseUrl = "http://213.136.86.27:8083/api/v1.0/sectors/";

    Faker faker = new Faker();
    String sectorName = faker.name().firstName().toLowerCase();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.name().firstName()+"@mail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    int sectorId;
    int promoterId;
    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.createSector(sectorName))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/sectors/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(respons.asString(), SectorResponse.class);
        Sector sector = sectorResponse.getData();
        this.sectorId =sector.getId();

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.addPromoters(firstName, lastName, email, phone))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/promoters/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter addPromoter = promoterResponse.getData();
        this.promoterId = addPromoter.getId();
    }

    @Test
    public void A_createSector(){


    }
}
