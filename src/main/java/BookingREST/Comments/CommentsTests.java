package BookingREST.Comments;

import Auth.Users.GetUserToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessData;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
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

public class CommentsTests {
    String baseUrl="http://213.136.86.27:8083/api/v1.0/promoters/";
    String usertoken;
    String token;
    int sector_id;
    int strategy_id;
    int promoterId;
    int businessId;
    Faker faker = new Faker();
    String sectorName = faker.name().firstName().toLowerCase();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String name = faker.name().firstName();
    String alias = faker.name().firstName().toLowerCase();
    String email = faker.name().firstName()+"@smail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    SectorData sectorData = new SectorData();
    CommentData commentData = new CommentData();
    StrategyData strategyData = new StrategyData();
    PromoterData promoterData = new PromoterData();
    BusinesessData businesessData = new BusinesessData();

    @BeforeClass
    public void beforeClass(){
        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

    ResponseBody responseSector = given().contentType(ContentType.JSON).header("Authorization", token).body(sectorData.createSector(sectorName)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().post("http://213.136.86.27:8083/api/v1.0/sectors/").thenReturn().body();
    SectorResponse sectorResponse = new Gson().fromJson(responseSector.asString(), SectorResponse.class);
    Sector sector = sectorResponse.data;
        this.sector_id =sector.getId();

    ResponseBody responseStrategy = given().contentType(ContentType.JSON).header("Authorization", token).body(strategyData.addPromoters(name)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().post("http://213.136.86.27:8083/api/v1.0/strategies/").thenReturn().body();
    StrategyResponse strategyResponse = new Gson().fromJson(responseStrategy.asString(), StrategyResponse.class);
    Strategy addStrategy = strategyResponse.data;
        this.strategy_id =addStrategy.getId();

    ResponseBody responsePromoter = given().contentType(ContentType.JSON).header("Authorization", token).body(promoterData.addPromoters(firstName, lastName, email, phone)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().post("http://213.136.86.27:8083/api/v1.0/promoters/").thenReturn().body();
    PromoterResponse promoterResponse = new Gson().fromJson(responsePromoter.asString(), PromoterResponse.class);
    Promoter addPromoter = promoterResponse.getData();
        this.promoterId =addPromoter.getId();

    ResponseBody responseBusiness = given().contentType(ContentType.JSON).header("Authorization", token).body(businesessData.createBusinesses(promoterId, strategy_id, sector_id, alias)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().post("http://213.136.86.27:8083/api/v1.0/businesses/").thenReturn().body();
    BusinesessResponse businesessResponse = new Gson().fromJson(responseBusiness.asString(), BusinesessResponse.class);
    Businesses businesses = businesessResponse.data;
        this.businessId =businesses.getId();
}



}
