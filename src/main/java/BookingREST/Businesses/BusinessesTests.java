package BookingREST.Businesses;

import BookingREST.Addresses.Address;
import BookingREST.Addresses.AddressResponse;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Promoter.Promoter;
import BookingREST.Promoter.PromoterData;
import BookingREST.Promoter.PromoterResponse;
import BookingREST.Sector.Sector;
import BookingREST.Sector.SectorData;
import BookingREST.Sector.SectorResponse;
import BookingREST.Strategy.Strategy;
import BookingREST.Strategy.StrategyData;
import BookingREST.Strategy.StrategyResponse;
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
    StrategyData strategyData = new StrategyData();
    String baseUrl = "http://213.136.86.27:8083/api/v1.0/businesses/";

    Faker faker = new Faker();
    String sectorName = faker.name().firstName().toLowerCase();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String name = faker.name().firstName();
    String alias = faker.name().firstName();
    String email = faker.name().firstName()+"@mail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    int sectorId;
    int promoterId;
    int strategyId;
    int businessId;
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

        ResponseBody responses = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(strategyData.addPromoters(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/strategies/").thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(responses.asString(), StrategyResponse.class);
        Strategy addStrategy = strategyResponse.getData();
        System.out.println(response.asString());
        this.strategyId = addStrategy.getId();
    }

    @Test
    public void A_createBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.createBusinesses(promoterId,strategyId,sectorId,alias))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/businesses/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(promoterId,businesses.getPromoter_id());
        Assert.assertEquals(strategyId,businesses.getStrategy_id());
        Assert.assertEquals(sectorId,businesses.getSector_id());
        Assert.assertEquals("maximum",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія",businesses.getDescription());
        Assert.assertEquals(alias,businesses.getAlias());
        Assert.assertEquals(false,businesses.is_verified);
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0",businesses.getUrl());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec",businesses.getLink_facebook());
    }

    @Test
    public void B_updateBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.updateBusiness())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals("maximum1",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства1",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія1",businesses.getDescription());
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java1/3.12.0",businesses.getUrl());
        Assert.assertEquals("http://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("http://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv1/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec1",businesses.getLink_facebook());
    }

    @Test
    public void C_getBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals("maximum1",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства1",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія1",businesses.getDescription());
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java1/3.12.0",businesses.getUrl());
        Assert.assertEquals("http://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("http://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv1/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec1",businesses.getLink_facebook());
    }

    @Test
    public void D_verifyBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/verify/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(true,businesses.is_verified);
    }

    @Test
    public void E_displayBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/display/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(true,businesses.is_searchable);
    }

    @Test
    public void F_displayBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/hide/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(false,businesses.is_searchable);
    }

    @Test
    public void G_promoterBusines(){
        this.alias = faker.name().firstName();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .body(businesessData.promoterBusinesses(strategyId,sectorId,alias))
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/promoters/"+promoterId+"/businesses/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals(false,businesses.is_searchable);
        Assert.assertEquals(alias, businesses.getAlias());
        Assert.assertEquals(true,businesses.getId()>businessId);
    }

    @Test
    public void H_promoterBusines(){
        this.alias = faker.name().firstName();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://213.136.86.27:8083/api/v1.0/promoters/"+promoterId+"/businesses/").thenReturn().body();
        BusinessArray businessArray= new Gson().fromJson(response.asString(), BusinessArray.class);

    }



}
