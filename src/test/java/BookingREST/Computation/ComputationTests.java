package BookingREST.Computation;

import BookingREST.Awards.Awards;
import BookingREST.Awards.AwardsData;
import BookingREST.Awards.AwardsResponse;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Fine.Fine;
import BookingREST.Fine.FineData;
import BookingREST.Fine.FineResponse;
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

public class ComputationTests {
    String baseUrl = "https://staging.eservia.com:8087/api/v1.0/computations/";
    AwardsData awardsData = new AwardsData();
    FineData fineData = new FineData();
    ComputationData computationData = new ComputationData();
    int businesId;
    int staffId;
    int awardsId;
    int promoterId;
    int planId;
    String token;
    int computationId;
    int fineId;

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
        ResponseBody response1 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(fineData.createFine(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8087/api/v1.0/fines/").thenReturn().body();
        FineResponse fineResponse= new Gson().fromJson(response1.asString(), FineResponse.class);
        Fine fine= fineResponse.getData();
        this.fineId = fine.getId();
    }

    @Test
    public void A_createComputation(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(computationData.createComputation(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        ComputationResponse computationResponse = new Gson().fromJson(response.asString(), ComputationResponse.class);
        Computation computation = computationResponse.getData();
        this.computationId = computation.getId();
        Assert.assertEquals(businesId,computation.getBusiness_id());
        Assert.assertEquals(staffId,computation.getStaff_id());
        Assert.assertEquals("2017-09-21T14:32:28+03:00",computation.getStarted_at());
        Assert.assertEquals("2017-09-21T19:32:28+03:00",computation.getEnded_at());
        Assert.assertEquals("UAH",computation.getCurrency());
        Assert.assertEquals(200,computation.getAmount());
    }

    @Test
    public void B_getComputation(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+computationId+"/").thenReturn().body();
        ComputationResponse computationResponse = new Gson().fromJson(response.asString(), ComputationResponse.class);
        Computation computation = computationResponse.getData();
        this.computationId = computation.getId();
        Assert.assertEquals(businesId,computation.getBusiness_id());
        Assert.assertEquals(staffId,computation.getStaff_id());
        Assert.assertEquals("2017-09-21T14:32:28+03:00",computation.getStarted_at());
        Assert.assertEquals("2017-09-21T19:32:28+03:00",computation.getEnded_at());
        Assert.assertEquals("UAH",computation.getCurrency());
        Assert.assertEquals(200,computation.getAmount());
    }

    @Test
    public void C_getComputationsByBusiness(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8087/api/v1.0/"+"businesses/"+businesId+"/computations/").thenReturn().body();
        ComputationResponseArray computationResponse = new Gson().fromJson(response.asString(), ComputationResponseArray.class);
        Computation computation = computationResponse.getData().get(0);
        this.computationId = computation.getId();
        Assert.assertEquals(businesId,computation.getBusiness_id());
        Assert.assertEquals(staffId,computation.getStaff_id());
        Assert.assertEquals("2017-09-21T14:32:28+03:00",computation.getStarted_at());
        Assert.assertEquals("2017-09-21T19:32:28+03:00",computation.getEnded_at());
        Assert.assertEquals("UAH",computation.getCurrency());
        Assert.assertEquals(200,computation.getAmount());
    }

    @Test
    public void D_createComputation2(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(computationData.createComputation2(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        ComputationResponse computationResponse = new Gson().fromJson(response.asString(), ComputationResponse.class);
        Computation computation = computationResponse.getData();
        this.computationId = computation.getId();
        Assert.assertEquals(businesId,computation.getBusiness_id());
        Assert.assertEquals(staffId,computation.getStaff_id());
        Assert.assertEquals("2017-09-23T14:32:28+03:00",computation.getStarted_at());
        Assert.assertEquals("2017-09-23T19:32:28+03:00",computation.getEnded_at());
        Assert.assertEquals("UAH",computation.getCurrency());
        Assert.assertEquals(-2000,computation.getAmount());
    }

    @AfterClass
    public void deleteAwards() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter()).when()
                .delete("https://staging.eservia.com:8083/api/v1.0/businesses/" + businesId + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        this.businesId = businesses.getId();

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter()).when()
                .delete("https://staging.eservia.com:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();

        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }
}
