package BookingREST.Awards;

import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.SalaryScheme.SalaryScheme;
import BookingREST.SalaryScheme.SalarySchemeData;
import BookingREST.SalaryScheme.SalarySchemeResponse;
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

public class AwardsTests {
    String baseUrl = "http://staging.eservia.com:8087/api/v1.0/awards/";
    AwardsData awardsData = new AwardsData();
    int businesId;
    int staffId;
    int id;
    int promoterId;
    int planId;
    String token;

    @BeforeClass
    public void preTests(){
        CreateBusiness createBusiness = new CreateBusiness();
        this.businesId=createBusiness.validBusiness();
        this.staffId=createBusiness.B_returnStaff();
        this.promoterId = createBusiness.returnPromoter();
        this.planId = createBusiness.returnPlan();
        this.token = createBusiness.B_returncreatedToken();
    }

    @Test
    public void A_createSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(awardsData.createAwards(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        AwardsResponse awardsResponse= new Gson().fromJson(response.asString(), AwardsResponse.class);
        Awards awards= awardsResponse.data;
        this.id = awards.getId();
        Assert.assertEquals(businesId,awards.getBusiness_id());
        Assert.assertEquals(staffId,awards.getStaff_id());
        Assert.assertEquals("2017-09-21T17:32:28+03:00",awards.getAccrual_at());
        Assert.assertEquals("UAH",awards.getCurrency());
        Assert.assertEquals(200,awards.getAmount());
        Assert.assertEquals("Прекрасна нагорода",awards.getComment());
    }

    @Test
    public void B_updateSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(awardsData.updateAwards(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+id+"/").thenReturn().body();
        AwardsResponse awardsResponse= new Gson().fromJson(response.asString(), AwardsResponse.class);
        Awards awards= awardsResponse.data;
        Assert.assertEquals(businesId,awards.getBusiness_id());
        Assert.assertEquals(staffId,awards.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",awards.getAccrual_at());
        Assert.assertEquals("USD",awards.getCurrency());
        Assert.assertEquals(2000,awards.getAmount());
        Assert.assertEquals("Прекрасна нагорода пацанам",awards.getComment());
    }

    @Test
    public void C_getSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+id+"/").thenReturn().body();
        AwardsResponse awardsResponse= new Gson().fromJson(response.asString(), AwardsResponse.class);
        Awards awards= awardsResponse.data;
        Assert.assertEquals(businesId,awards.getBusiness_id());
        Assert.assertEquals(staffId,awards.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",awards.getAccrual_at());
        Assert.assertEquals("USD",awards.getCurrency());
        Assert.assertEquals(2000,awards.getAmount());
        Assert.assertEquals("Прекрасна нагорода пацанам",awards.getComment());
    }

    @Test
    public void D_getSalarySchemeByBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8087/api/v1.0/businesses/"+businesId+"/awards/").thenReturn().body();
        AwardsResponseArray awardsResponse= new Gson().fromJson(response.asString(), AwardsResponseArray.class);
        Awards awards= awardsResponse.getData().get(0);
        Assert.assertEquals(businesId,awards.getBusiness_id());
        Assert.assertEquals(staffId,awards.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",awards.getAccrual_at());
        Assert.assertEquals("USD",awards.getCurrency());
        Assert.assertEquals(2000,awards.getAmount());
        Assert.assertEquals("Прекрасна нагорода пацанам",awards.getComment());
    }

    @Test
    public void E_deleteSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+id+"/").thenReturn().body();
        AwardsResponse awardsResponse= new Gson().fromJson(response.asString(), AwardsResponse.class);
        Awards awards= awardsResponse.data;
        Assert.assertEquals(businesId,awards.getBusiness_id());
        Assert.assertEquals(staffId,awards.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",awards.getAccrual_at());
        Assert.assertEquals("USD",awards.getCurrency());
        Assert.assertEquals(2000,awards.getAmount());
        Assert.assertEquals("Прекрасна нагорода пацанам",awards.getComment());
        Assert.assertEquals(true,awards.getDeleted_at().contains("2018"));
    }

    @AfterClass
    public void deleteBeforee() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter()).when()
                .delete("http://213.136.86.27:8083/api/v1.0/businesses/" + businesId + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        this.businesId = businesses.getId();

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter()).when()
                .delete("http://213.136.86.27:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();

        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }
}
