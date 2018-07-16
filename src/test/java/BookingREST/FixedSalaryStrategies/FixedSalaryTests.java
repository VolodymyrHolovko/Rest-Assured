package BookingREST.FixedSalaryStrategies;

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

public class FixedSalaryTests {
    String baseUrl = "https://staging.eservia.com:8087/api/v1.0/fixed-salary-strategies/";
    FizedSalaryData fixedSalaryData= new FizedSalaryData();
    SalarySchemeData salarySchemeData = new SalarySchemeData();
    int businesId;
    int staffId;
    int salarySchemeId;
    int promoterId;
    int planId;
    String token;
    int fixedSalarySchemeId;

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
                .when().post("https://staging.eservia.com:8087/api/v1.0/salary-schemes/").thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.getData();
        this.salarySchemeId = salaryScheme.getId();
    }

    @Test
    public void A_createFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(fixedSalaryData.createFixedSalary(businesId,salarySchemeId,1))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        FixedStrategyResponse fixedStrategyResponse= new Gson().fromJson(response.asString(), FixedStrategyResponse.class);
        FixedSalary fixedSalary= fixedStrategyResponse.data;
        this.fixedSalarySchemeId = fixedSalary.getId();
        Assert.assertEquals(businesId,fixedSalary.getBusiness_id());
        Assert.assertEquals(salarySchemeId,fixedSalary.getSalary_scheme_id());
        Assert.assertEquals(1,fixedSalary.getAggregation());
        Assert.assertEquals("USD",fixedSalary.getCurrency());
        Assert.assertEquals(20,fixedSalary.getAmount());
    }

    @Test
    public void B_updateFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(fixedSalaryData.updateFixedSalary(businesId,salarySchemeId,1))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+fixedSalarySchemeId+"/").thenReturn().body();
        FixedStrategyResponse fixedStrategyResponse= new Gson().fromJson(response.asString(), FixedStrategyResponse.class);
        FixedSalary fixedSalary= fixedStrategyResponse.data;
        this.fixedSalarySchemeId = fixedSalary.getId();
        Assert.assertEquals(businesId,fixedSalary.getBusiness_id());
        Assert.assertEquals(salarySchemeId,fixedSalary.getSalary_scheme_id());
        Assert.assertEquals(1,fixedSalary.getAggregation());
        Assert.assertEquals("UAH",fixedSalary.getCurrency());
        Assert.assertEquals(30,fixedSalary.getAmount());
    }

    @Test
    public void C_getFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+fixedSalarySchemeId+"/").thenReturn().body();
        FixedStrategyResponse fixedStrategyResponse= new Gson().fromJson(response.asString(), FixedStrategyResponse.class);
        FixedSalary fixedSalary= fixedStrategyResponse.data;
        this.fixedSalarySchemeId = fixedSalary.getId();
        Assert.assertEquals(businesId,fixedSalary.getBusiness_id());
        Assert.assertEquals(salarySchemeId,fixedSalary.getSalary_scheme_id());
        Assert.assertEquals(1,fixedSalary.getAggregation());
        Assert.assertEquals("UAH",fixedSalary.getCurrency());
        Assert.assertEquals(30,fixedSalary.getAmount());
    }

    @Test
    public void D_getFixedSalarySchemeByBusiness(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8087/api/v1.0/businesses/"+businesId+"/fixed-salary-strategies/").thenReturn().body();
        FizedSalaryArrayResponse fizedSalaryArrayResponse= new Gson().fromJson(response.asString(), FizedSalaryArrayResponse.class);
        FixedSalary fixedSalary= fizedSalaryArrayResponse.getData().get(0);
        this.fixedSalarySchemeId = fixedSalary.getId();
        Assert.assertEquals(businesId,fixedSalary.getBusiness_id());
        Assert.assertEquals(salarySchemeId,fixedSalary.getSalary_scheme_id());
        Assert.assertEquals(1,fixedSalary.getAggregation());
        Assert.assertEquals("UAH",fixedSalary.getCurrency());
        Assert.assertEquals(30,fixedSalary.getAmount());
    }
    @Test
    public void E_deleteFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+fixedSalarySchemeId+"/").thenReturn().body();
        FixedStrategyResponse fixedStrategyResponse= new Gson().fromJson(response.asString(), FixedStrategyResponse.class);
        FixedSalary fixedSalary= fixedStrategyResponse.data;
        this.fixedSalarySchemeId = fixedSalary.getId();
        Assert.assertEquals(businesId,fixedSalary.getBusiness_id());
        Assert.assertEquals(salarySchemeId,fixedSalary.getSalary_scheme_id());
        Assert.assertEquals(1,fixedSalary.getAggregation());
        Assert.assertEquals("UAH",fixedSalary.getCurrency());
        Assert.assertEquals(30,fixedSalary.getAmount());
        Assert.assertEquals(true,fixedSalary.getDeleted_at().contains("2018"));
    }

    @AfterClass
    public void deleteBeforee() {
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
