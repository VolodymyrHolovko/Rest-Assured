package BookingREST.Plans;

import BookingREST.AuthBusiness.AuthBusinessTest;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static com.jayway.restassured.RestAssured.given;

public class PlanTests {

    private String token;
    private String baseURL = "http://213.136.86.27:8083/api/v1.0/plans/";
    int Ids;
    int planId;
    PlanData planData = new PlanData();
    PlanSettingsData planSettingsData = new PlanSettingsData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
    }

    @Test
    public void A_createPlan(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(planData.CreatePlan())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        PlanResponse planResponse = new  Gson().fromJson(response.asString(), PlanResponse.class);
        Plan plan = planResponse.data;
        this.planId = plan.getId();
        Assert.assertEquals("tempPlanName",plan.getName());
        Assert.assertEquals("tempPlanDescription",plan.getDescription());
        Assert.assertEquals(0,plan.getPrice());
        Assert.assertEquals("UAH",plan.getCurrency());
        Assert.assertEquals(1000,plan.getInterval());
        Assert.assertEquals(false,plan.isIs_unique());
        Assert.assertEquals(1,plan.getStatus());
    }

    @Test
    public void BC_UpdatePlan(){
    ResponseBody response = given()
            .contentType(ContentType.JSON)
            .header("Authorization",token)
            .body(planData.UpdatePlan(1))
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when().put(baseURL+planId+"/").thenReturn().body();
    PlanResponse planResponse = new Gson().fromJson(response.asString(), PlanResponse.class);
    Plan plan = planResponse.data;
    this.Ids = plan.getId();
    Assert.assertEquals("updatedPlanName",plan.getName());
    Assert.assertEquals("updatedPlanDescription",plan.getDescription());
    Assert.assertEquals(800,plan.getPrice());
    Assert.assertEquals("USD",plan.getCurrency());
    Assert.assertEquals(3000,plan.getInterval());
    Assert.assertEquals(true,plan.isIs_unique());
    }

    @Test
    public void B_GetUpdatedPlan(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body(planData.UpdatePlan(1))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+planId+"/").thenReturn().body();
        PlanResponse planResponse = new Gson().fromJson(response.asString(), PlanResponse.class);
        Plan plan = planResponse.data;
        this.Ids = plan.getId();
        Assert.assertEquals("updatedPlanName",plan.getName());
        Assert.assertEquals("updatedPlanDescription",plan.getDescription());
        Assert.assertEquals(800,plan.getPrice());
        Assert.assertEquals("USD",plan.getCurrency());
        Assert.assertEquals(3000,plan.getInterval());
        Assert.assertEquals(true,plan.isIs_unique());
    }

    @Test
    public void C_DeactivatePlan(){
    ResponseBody response = given()
            .contentType(ContentType.JSON)
            .header("Authorization",token)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when().patch(baseURL+planId+"/deactivate/").thenReturn().body();
    PlanResponse planResponse = new Gson().fromJson(response.asString(), PlanResponse.class);
    Plan plan = planResponse.data;
    Assert.assertEquals("updatedPlanName",plan.getName());
    Assert.assertEquals("updatedPlanDescription",plan.getDescription());
    Assert.assertEquals(800,plan.getPrice());
    Assert.assertEquals("USD",plan.getCurrency());
    Assert.assertEquals(3000,plan.getInterval());
    Assert.assertEquals(true,plan.isIs_unique());
    Assert.assertEquals(0,plan.getStatus());
    }

    @Test
    public void D_ActivatePlan(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+planId+"/activate/").thenReturn().body();
        PlanResponse planResponse = new Gson().fromJson(response.asString(), PlanResponse.class);
        Plan plan = planResponse.data;
        Assert.assertEquals("updatedPlanName",plan.getName());
        Assert.assertEquals("updatedPlanDescription",plan.getDescription());
        Assert.assertEquals(800,plan.getPrice());
        Assert.assertEquals("USD",plan.getCurrency());
        Assert.assertEquals(3000,plan.getInterval());
        Assert.assertEquals(true,plan.isIs_unique());
        Assert.assertEquals(1,plan.getStatus());
    }



    @Test
    public void F_GetAllPlans(){
        /*ResponseBodyData response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL).thenReturn().body();*/

        //RestAssured.baseURI = baseURL;
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());
    }




    @Test
    public void H_UpdatePlanSettings(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body(planSettingsData.UpdatePlanSettings())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+planId+"/settings/").thenReturn().body();
        PlanSettingsResponse planSettingsResponse = new Gson().fromJson(response.asString(), PlanSettingsResponse.class);
        PlanSettings planSettings = planSettingsResponse.data;
        Assert.assertEquals(50,planSettings.getAddresslimit());
        Assert.assertEquals(60,planSettings.getAddresspurchase_limit());
        Assert.assertEquals(70,planSettings.getStafflimit());
        Assert.assertEquals(80,planSettings.getStaffpurchase_limit());
        Assert.assertEquals(true,planSettings.getStaffauthentication());
        Assert.assertEquals(true,planSettings.getMarketing());
        Assert.assertEquals(20,planSettings.getBookinguser_history_limit());
        Assert.assertEquals(1000,planSettings.getBookingcreate_max_duration());
        Assert.assertEquals(true,planSettings.getBookinganalytics());
    }

    @Test
    public void I_GetPlanSettings(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+planId+"/settings/").thenReturn().body();
        PlanSettingsResponse planSettingsResponse = new Gson().fromJson(response.asString(), PlanSettingsResponse.class);
        PlanSettings planSettings = planSettingsResponse.data;
        Assert.assertEquals(50,planSettings.getAddresslimit());
        Assert.assertEquals(60,planSettings.getAddresspurchase_limit());
        Assert.assertEquals(70,planSettings.getStafflimit());
        Assert.assertEquals(80,planSettings.getStaffpurchase_limit());
        Assert.assertEquals(true,planSettings.getStaffauthentication());
        Assert.assertEquals(true,planSettings.getMarketing());
        Assert.assertEquals(20,planSettings.getBookinguser_history_limit());
        Assert.assertEquals(1000,planSettings.getBookingcreate_max_duration());
        Assert.assertEquals(true,planSettings.getBookinganalytics());
    }

    @Test
    public void J_DeletePlan(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+planId+"/").thenReturn().body();
        PlanResponse planResponse = new Gson().fromJson(response.asString(), PlanResponse.class);
        Plan plan = planResponse.data;
        this.Ids = plan.getId();



        ResponseBody responseGet = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+planId+"/").thenReturn().body();
        PlanResponse planGetResponse = new Gson().fromJson(response.asString(),PlanResponse.class);
        Plan planGet = planGetResponse.data;
        Assert.assertEquals(Ids,plan.getId());
        Assert.assertTrue(plan.getDeleted_at().startsWith("2018"));
    }


}
