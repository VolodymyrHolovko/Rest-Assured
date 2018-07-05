package BookingREST.Sector;

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

public class SectorTests {

    String token;
    SectorData sectorData = new SectorData();
    String baseUrl = "http://staging.eservia.com:8083/api/v1.0/sectors/";
    int id;

    Faker faker = new Faker();
    String sectorName = faker.name().firstName().toLowerCase()+faker.name().nameWithMiddle().toLowerCase();
    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
    }

    @Test
    public void A_createSector(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.createSector(sectorName))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(response.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        this.id =sector.getId();
        Assert.assertEquals(sectorName,sector.getSector());
        Assert.assertEquals("Fishing",sector.getName_en());
        Assert.assertEquals("Rasha parasha",sector.getName_ru());
        Assert.assertEquals("Рибалка",sector.getName_uk());
        Assert.assertEquals(1,sector.getStatus());
    }

    @Test
    public void B_updateSector(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+id+"/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(response.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        Assert.assertEquals(sectorName,sector.getSector());
        Assert.assertEquals("Fishing1",sector.getName_en());
        Assert.assertEquals("Rasha parasha1",sector.getName_ru());
        Assert.assertEquals("Рибалка1",sector.getName_uk());
        Assert.assertEquals(1,sector.getStatus());
    }

    @Test
    public void C_getSector(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+id+"/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(response.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        Assert.assertEquals(sectorName,sector.getSector());
        Assert.assertEquals("Fishing1",sector.getName_en());
        Assert.assertEquals("Rasha parasha1",sector.getName_ru());
        Assert.assertEquals("Рибалка1",sector.getName_uk());
        Assert.assertEquals(1,sector.getStatus());
    }

    @Test
    public void D_deactivateSector(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseUrl+id+"/deactivate/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(response.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        Assert.assertEquals(0,sector.getStatus());

    }

    @Test
    public void E_activateSector(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseUrl+id+"/activate/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(response.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        Assert.assertEquals(1,sector.getStatus());
    }

    @Test
    public void F_deleteSector(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+id+"/").thenReturn().body();

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+id+"/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(response.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        Assert.assertEquals(true,sector.getDeleted_at().startsWith("2018"));

    }
}
