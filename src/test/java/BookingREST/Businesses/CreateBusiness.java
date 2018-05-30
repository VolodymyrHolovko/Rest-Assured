package BookingREST.Businesses;

import Auth.Users.GetUserToken;
import BookingREST.Addresses.Address;
import BookingREST.Addresses.AddressData;
import BookingREST.Addresses.AddressResponse;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Comments.CommentData;
import BookingREST.Plans.Plan;
import BookingREST.Plans.PlanData;
import BookingREST.Plans.PlanResponse;
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

import static com.jayway.restassured.RestAssured.given;

public class CreateBusiness {
    String token;
    CommentData commentData = new CommentData();
    SectorData sectorData = new SectorData();
    BusinesessData businesessData = new BusinesessData();
    PromoterData promoterData = new PromoterData();
    StrategyData strategyData = new StrategyData();
    PlanData planData = new PlanData();
    AddressData addressData = new AddressData();
    String baseUrl = "http://213.136.86.27:8083/api/v1.0/businesses/";

    Faker faker = new Faker();
    String sectorName = faker.name().username()+faker.name().firstName();
    String firstName = faker.name().firstName()+faker.name().firstName();
    String lastName = faker.name().username()+faker.name().firstName();
    String name = faker.name().username()+faker.name().firstName();
    String alias = faker.name().firstName().toLowerCase()+faker.name().firstName().toLowerCase();
    String email = faker.name().lastName()+faker.name().firstName()+"@mail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    int sectorId;
    int promoterId;
    int strategyId;
    int businessId;
    int adressId;
    int planId;
    String usertoken;
    String uesrId;

    public int validBusiness(){


        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        GetUserToken getUserToken1= new GetUserToken();
        this.uesrId = getUserToken1.GetUserId();

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

        ResponseBody responseess = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(planData.freePlan(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/plans/").thenReturn().body();
        PlanResponse planResponse = new  Gson().fromJson(responseess.asString(), PlanResponse.class);
        Plan plan = planResponse.getData();
        this.planId = plan.getId();

        ResponseBody responsee = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.createBusinesses(promoterId,1,sectorId,alias))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/businesses/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(responsee.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(promoterId,businesses.getPromoter_id());
        Assert.assertEquals(1,businesses.getStrategy_id());
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

        ResponseBody responss = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/plans/"+planId+"/subscribe/").thenReturn().body();

        ResponseBody responseessa = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addressData.CreateAddress(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://213.136.86.27:8083/api/v1.0/addresses/").thenReturn().body();
        AddressResponse addressResponse= new Gson().fromJson(responseessa.asString(), AddressResponse.class);
        Address address= addressResponse.getData();
        this.adressId = address.getId();


        ResponseBody response1 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/verify/").thenReturn().body();
        BusinesessResponse businesessResponse1= new Gson().fromJson(response1.asString(), BusinesessResponse.class);
        Businesses businesses1= businesessResponse1.data;

        Assert.assertEquals(true,businesses1.is_verified);
        return businessId;
    }

    public int W_returnAdressId(){
        return  adressId;
    }
}
