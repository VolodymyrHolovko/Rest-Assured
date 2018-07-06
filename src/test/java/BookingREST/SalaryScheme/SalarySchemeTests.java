package BookingREST.SalaryScheme;

import Auth.Users.GetUserToken;
import BookingREST.Bookings.Booking;
import BookingREST.Bookings.BookingsResponse;
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

public class SalarySchemeTests {
    String baseUrl = "http://staging.eservia.com:8087/api/v1.0/salary-schemes/";
    SalarySchemeData salarySchemeData = new SalarySchemeData();
    int businesId;
    int staffId;
    int salarySchemeId;
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
                .body(salarySchemeData.createSalaryScheme(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.data;
        this.salarySchemeId = salaryScheme.getId();
        Assert.assertEquals(businesId,salaryScheme.getBusiness_id());
        Assert.assertEquals(staffId,salaryScheme.getStaff_id());
        Assert.assertEquals("2017-08-21T17:32:28+03:00",salaryScheme.getStarted_at());
    }

    @Test
    public void B_UpdateSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(salarySchemeData.updateSalaryScheme(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+salarySchemeId+"/").thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.data;
        this.salarySchemeId = salaryScheme.getId();
        Assert.assertEquals(businesId,salaryScheme.getBusiness_id());
        Assert.assertEquals(staffId,salaryScheme.getStaff_id());
        Assert.assertEquals("2017-09-21T17:32:28+03:00",salaryScheme.getStarted_at());
    }

    @Test
    public void C_getSalarySchemeById(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+salarySchemeId+"/").thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.data;
        this.salarySchemeId = salaryScheme.getId();
        Assert.assertEquals(businesId,salaryScheme.getBusiness_id());
        Assert.assertEquals(staffId,salaryScheme.getStaff_id());
        Assert.assertEquals("2017-09-21T17:32:28+03:00",salaryScheme.getStarted_at());
    }

    @Test
    public void D_getAllSalarySchemes(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(" http://staging.eservia.com:8087/api/v1.0/businesses/"+businesId+"/salary-schemes/").thenReturn().body();
        SalarySchemeResponseArray salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponseArray.class);
        SalaryScheme salaryScheme = salarySchemeResponse.getData().get(0);
        this.salarySchemeId = salaryScheme.getId();
        Assert.assertEquals(businesId,salaryScheme.getBusiness_id());
        Assert.assertEquals(staffId,salaryScheme.getStaff_id());
        Assert.assertEquals("2017-09-21T17:32:28+03:00",salaryScheme.getStarted_at());
    }

    @Test
    public void E_deleteSalarySchemes(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+salarySchemeId+"/").thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.data;
        this.salarySchemeId = salaryScheme.getId();
        Assert.assertEquals(businesId,salaryScheme.getBusiness_id());
        Assert.assertEquals(staffId,salaryScheme.getStaff_id());
        Assert.assertEquals("2017-09-21T17:32:28+03:00",salaryScheme.getStarted_at());
        Assert.assertEquals(true,salaryScheme.getDeleted_at().contains("2018"));
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
