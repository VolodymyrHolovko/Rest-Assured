package BookingREST.Computation;

import BookingREST.Awards.Awards;
import BookingREST.Awards.AwardsData;
import BookingREST.Awards.AwardsResponse;
import BookingREST.Businesses.CreateBusiness;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class ComputationTests {
    String baseUrl = "https://staging.eservia.com:8087/api/v1.0/computations/";
    AwardsData awardsData = new AwardsData();
    ComputationData computationData = new ComputationData();
    int businesId;
    int staffId;
    int awardsId;
    int promoterId;
    int planId;
    String token;

    @BeforeClass
    public void preTests() {
        CreateBusiness createBusiness = new CreateBusiness();
        this.businesId = createBusiness.validBusiness();
        this.staffId = createBusiness.B_returnStaff();
        this.promoterId = createBusiness.returnPromoter();
        this.planId = createBusiness.returnPlan();
        this.token = createBusiness.B_returncreatedToken();
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(awardsData.createAwards(businesId, staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8087/api/v1.0/awards/").thenReturn().body();
        AwardsResponse awardsResponse = new Gson().fromJson(response.asString(), AwardsResponse.class);
        Awards awards = awardsResponse.getData();
        this.awardsId = awards.getId();
    }

    @Test
    public void createComputation(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(computationData.createComputation(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        ComputationResponse computationResponse = new Gson().fromJson(response.asString(), ComputationResponse.class);
        Computation computation = computationResponse.getData();
        Assert.assertEquals(businesId,computation.getBusiness_id());
        Assert.assertEquals(staffId,computation.getStaff_id());
        Assert.assertEquals("2017-09-21T14:32:28+03:00",computation.getStarted_at());
        Assert.assertEquals("2017-09-21T19:32:28+03:00",computation.getEnded_at());
        Assert.assertEquals("UAH",computation.getCurrency());
        Assert.assertEquals(200,computation.getAmount());
    }
}
