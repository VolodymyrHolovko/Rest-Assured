package BookingREST.Fine;

import BookingREST.Awards.Awards;
import BookingREST.Awards.AwardsData;
import BookingREST.Awards.AwardsResponse;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
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

public class FineTests {
    String baseUrl = "http://staging.eservia.com:8087/api/v1.0/fines/";
    FineData fineData= new FineData();
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
    public void A_createFine(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(fineData.createFine(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        FineResponse fineResponse= new Gson().fromJson(response.asString(), FineResponse.class);
        Fine fine= fineResponse.data;
        this.id = fine.getId();
        Assert.assertEquals(businesId,fine.getBusiness_id());
        Assert.assertEquals(staffId,fine.getStaff_id());
        Assert.assertEquals("2017-09-21T17:32:28+03:00",fine.getAccrual_at());
        Assert.assertEquals("UAH",fine.getCurrency());
        Assert.assertEquals(2000,fine.getAmount());
        Assert.assertEquals("То всьо шо ти заробив? Не позорься",fine.getComment());
    }

    @Test
    public void B_updateFine(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(fineData.updateFine(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+id+"/").thenReturn().body();
        FineResponse fineResponse= new Gson().fromJson(response.asString(), FineResponse.class);
        Fine fine= fineResponse.data;
        Assert.assertEquals(businesId,fine.getBusiness_id());
        Assert.assertEquals(staffId,fine.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",fine.getAccrual_at());
        Assert.assertEquals("USD",fine.getCurrency());
        Assert.assertEquals(200,fine.getAmount());
        Assert.assertEquals("То всьо шо ти заробив лошара? Не позорься",fine.getComment());
    }

    @Test
    public void C_getFine(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+id+"/").thenReturn().body();
        FineResponse fineResponse= new Gson().fromJson(response.asString(), FineResponse.class);
        Fine fine= fineResponse.data;
        Assert.assertEquals(businesId,fine.getBusiness_id());
        Assert.assertEquals(staffId,fine.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",fine.getAccrual_at());
        Assert.assertEquals("USD",fine.getCurrency());
        Assert.assertEquals(200,fine.getAmount());
        Assert.assertEquals("То всьо шо ти заробив лошара? Не позорься",fine.getComment());
    }

    @Test
    public void D_getFineByBusiness(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8087/api/v1.0/businesses/"+businesId+"/fines/").thenReturn().body();
        FineResponseArray fineResponse= new Gson().fromJson(response.asString(), FineResponseArray.class);
        Fine fine= fineResponse.getData().get(0);
        Assert.assertEquals(businesId,fine.getBusiness_id());
        Assert.assertEquals(staffId,fine.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",fine.getAccrual_at());
        Assert.assertEquals("USD",fine.getCurrency());
        Assert.assertEquals(200,fine.getAmount());
        Assert.assertEquals("То всьо шо ти заробив лошара? Не позорься",fine.getComment());
    }

    @Test
    public void E_deleteFine(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+id+"/").thenReturn().body();
        FineResponse fineResponse= new Gson().fromJson(response.asString(), FineResponse.class);
        Fine fine= fineResponse.data;
        Assert.assertEquals(businesId,fine.getBusiness_id());
        Assert.assertEquals(staffId,fine.getStaff_id());
        Assert.assertEquals("2017-10-21T17:32:28+03:00",fine.getAccrual_at());
        Assert.assertEquals("USD",fine.getCurrency());
        Assert.assertEquals(200,fine.getAmount());
        Assert.assertEquals("То всьо шо ти заробив лошара? Не позорься",fine.getComment());
        Assert.assertEquals(true,fine.getDeleted_at().contains("2018"));
    }

    @AfterClass
    public void deleteAwards() {
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
