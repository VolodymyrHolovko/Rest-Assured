package BookingREST.Comments;

import Auth.Users.GetUserToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessData;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
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
    int businessId;
    CreateBusiness createBusiness = new CreateBusiness();



    @BeforeClass
    public void beforeClass(){
        businessId = createBusiness.validBusiness();
        
        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();


}



}
