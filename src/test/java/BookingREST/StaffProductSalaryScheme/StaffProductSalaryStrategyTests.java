package BookingREST.StaffProductSalaryScheme;

import BookingREST.AddressProductSalaryStrategy.AddressProductSalaryStrategy;
import BookingREST.AddressProductSalaryStrategy.AddressProductSalaryStrategyData;
import BookingREST.AddressProductSalaryStrategy.AddressProductSalaryStrategyResponse;
import BookingREST.AddressProductSalaryStrategy.AddressProductSalaryStrategyresponseArray;
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

public class StaffProductSalaryStrategyTests {
    String baseUrl = "http://staging.eservia.com:8087/api/v1.0/staff-product-salary-strategies/";
    StaffProductSalaryStrategy staffProductSalaryStrategy= new StaffProductSalaryStrategy();
    StaffProductSalaryStrategyData staffProductSalaryStrategyData= new StaffProductSalaryStrategyData();
    SalarySchemeData salarySchemeData = new SalarySchemeData();
    int businesId;
    int staffId;
    int salarySchemeId;
    int promoterId;
    int planId;
    String token;
    int addressProductSalarySchemeId;

    @BeforeClass
    public void preTests(){
        CreateBusiness createBusiness = new CreateBusiness();
        this.businesId=createBusiness.validBusiness();
        this.staffId=createBusiness.B_returnStaff();
        this.promoterId = createBusiness.returnPromoter();
        this.planId = createBusiness.returnPlan();
        this.token = createBusiness.B_returncreatedToken();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(salarySchemeData.createSalaryScheme(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://staging.eservia.com:8087/api/v1.0/salary-schemes/").thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.getData();
        this.salarySchemeId = salaryScheme.getId();
    }

    @Test
    public void A_createFixedSalarySchemeStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffProductSalaryStrategyData.createStaffStrategy(businesId,salarySchemeId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();

        StaffProductSalaryStrategyResponse staffProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), StaffProductSalaryStrategyResponse.class);
        StaffProductSalaryStrategy staffProductSalaryStrategy= staffProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = staffProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,staffProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,staffProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(20,staffProductSalaryStrategy.getPercent());
    }

    @Test
    public void B_updateFixedSalarySchemeStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffProductSalaryStrategyData.updateStrategy())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+addressProductSalarySchemeId+"/").thenReturn().body();
        StaffProductSalaryStrategyResponse staffProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), StaffProductSalaryStrategyResponse.class);
        StaffProductSalaryStrategy staffProductSalaryStrategy= staffProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = staffProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,staffProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,staffProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,staffProductSalaryStrategy.getPercent());
    }

    @Test
    public void C_getFixedSalarySchemeStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+addressProductSalarySchemeId+"/").thenReturn().body();
        StaffProductSalaryStrategyResponse staffProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), StaffProductSalaryStrategyResponse.class);
        StaffProductSalaryStrategy staffProductSalaryStrategy= staffProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = staffProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,staffProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,staffProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,staffProductSalaryStrategy.getPercent());
    }

    @Test
    public void D_getFixedSalarySchemeByBusinessStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8087/api/v1.0/businesses/"+businesId+"/staff-product-salary-strategies/").thenReturn().body();
        StaffProductSalaryStrategyResponseArray staffProductSalaryStrategyResponseArray= new Gson().fromJson(response.asString(), StaffProductSalaryStrategyResponseArray.class);
        StaffProductSalaryStrategy staffProductSalaryStrategy= staffProductSalaryStrategyResponseArray.getData().get(0);
        this.addressProductSalarySchemeId = staffProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,staffProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,staffProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,staffProductSalaryStrategy.getPercent());
    }

    @Test
    public void E_deleteFixedSalarySchemeStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+addressProductSalarySchemeId+"/").thenReturn().body();
        StaffProductSalaryStrategyResponse staffProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), StaffProductSalaryStrategyResponse.class);
        StaffProductSalaryStrategy staffProductSalaryStrategy= staffProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = staffProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,staffProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,staffProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,staffProductSalaryStrategy.getPercent());
        Assert.assertEquals(true,staffProductSalaryStrategy.getDeleted_at().contains("2018"));
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
