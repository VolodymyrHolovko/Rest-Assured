package BookingREST.AddressProductSalaryStrategy;

import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.FixedSalaryStrategies.FixedSalary;
import BookingREST.FixedSalaryStrategies.FixedStrategyResponse;
import BookingREST.FixedSalaryStrategies.FizedSalaryData;
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

public class AddressProductSalaryStrategyTests {
    String baseUrl = "https://staging.eservia.com:8087/api/v1.0/address-product-salary-strategies/";
    AddressProductSalaryStrategyData addressProductSalaryStrategyData= new AddressProductSalaryStrategyData();
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
                .body(addressProductSalaryStrategyData.createStrategy(businesId,salarySchemeId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();

        AddressProductSalaryStrategyResponse addressProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), AddressProductSalaryStrategyResponse.class);
        AddressProductSalaryStrategy addressProductSalaryStrategy= addressProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = addressProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,addressProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,addressProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(20,addressProductSalaryStrategy.getPercent());
    }

    @Test
    public void B_updateFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addressProductSalaryStrategyData.updateStrategy())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+addressProductSalarySchemeId+"/").thenReturn().body();
        AddressProductSalaryStrategyResponse addressProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), AddressProductSalaryStrategyResponse.class);
        AddressProductSalaryStrategy addressProductSalaryStrategy= addressProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = addressProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,addressProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,addressProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,addressProductSalaryStrategy.getPercent());
    }

    @Test
    public void C_getFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+addressProductSalarySchemeId+"/").thenReturn().body();
        AddressProductSalaryStrategyResponse addressProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), AddressProductSalaryStrategyResponse.class);
        AddressProductSalaryStrategy addressProductSalaryStrategy= addressProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = addressProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,addressProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,addressProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,addressProductSalaryStrategy.getPercent());
    }

    @Test
    public void D_getFixedSalarySchemeByBusiness(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8087/api/v1.0/businesses/"+businesId+"/address-product-salary-strategies/").thenReturn().body();
        AddressProductSalaryStrategyresponseArray addressProductSalaryStrategyresponseArray= new Gson().fromJson(response.asString(), AddressProductSalaryStrategyresponseArray.class);
        AddressProductSalaryStrategy addressProductSalaryStrategy= addressProductSalaryStrategyresponseArray.getData().get(0);
        this.addressProductSalarySchemeId = addressProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,addressProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,addressProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,addressProductSalaryStrategy.getPercent());
    }

    @Test
    public void E_deleteFixedSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+addressProductSalarySchemeId+"/").thenReturn().body();
        AddressProductSalaryStrategyResponse addressProductSalaryStrategyResponse= new Gson().fromJson(response.asString(), AddressProductSalaryStrategyResponse.class);
        AddressProductSalaryStrategy addressProductSalaryStrategy= addressProductSalaryStrategyResponse.data;
        this.addressProductSalarySchemeId = addressProductSalaryStrategy.getId();
        Assert.assertEquals(businesId,addressProductSalaryStrategy.getBusiness_id());
        Assert.assertEquals(salarySchemeId,addressProductSalaryStrategy.getSalary_scheme_id());
        Assert.assertEquals(30,addressProductSalaryStrategy.getPercent());
        Assert.assertEquals(true,addressProductSalaryStrategy.getDeleted_at().contains("2018"));
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
