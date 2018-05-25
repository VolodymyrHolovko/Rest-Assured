package BookingREST.ServiceGroups;


import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
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



public class ServiceGroupTests {
    String token;
    private String baseURL = "http://213.136.86.27:8084/api/v1.0/service-groups/";
    int Ids;
    int businessID;
    ServiceGroupData serviceGroupData = new ServiceGroupData();
    CreateBusiness createBusiness = new CreateBusiness();


    @BeforeClass
    public void getToken() {
        businessID = createBusiness.validBusiness();
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
    }


    @Test
    public void A_CreateServiceGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceGroupData.CreateServiceGroup(businessID))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ServiceGroupResponse serviceGroupResponse = new Gson().fromJson(response.asString(), ServiceGroupResponse.class);
        ServiceGroup serviceGroup = serviceGroupResponse.data;
        this.Ids = serviceGroup.getId();
        Assert.assertEquals(businessID,serviceGroup.getBusiness_id());
        Assert.assertEquals("Servicegroupname",serviceGroup.getName());
    }


    @Test
    public void B_UpdateServiceGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceGroupData.UpdateServiceGroup())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+Ids+"/").thenReturn().body();
        ServiceGroupResponse serviceGroupResponse = new Gson().fromJson(response.asString(), ServiceGroupResponse.class);
        ServiceGroup serviceGroup = serviceGroupResponse.data;
        this.Ids = serviceGroup.getId();
        Assert.assertEquals(businessID,serviceGroup.getBusiness_id());
        Assert.assertEquals("UpdatedServicegroupname",serviceGroup.getName());
    }


    @Test
    public void C_GetUpdatedServiceGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+Ids+"/").thenReturn().body();
        ServiceGroupResponse serviceGroupResponse = new Gson().fromJson(response.asString(), ServiceGroupResponse.class);
        ServiceGroup serviceGroup = serviceGroupResponse.data;
        this.Ids = serviceGroup.getId();
        Assert.assertEquals(businessID,serviceGroup.getBusiness_id());
        Assert.assertEquals("UpdatedServicegroupname",serviceGroup.getName());
    }

    @Test
    public void D_GetAllServiceGroups(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());


    }


    @Test
    public void E_DeleteServicesGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+Ids+"/").thenReturn().body();
        ServiceGroupResponse serviceGroupResponse = new Gson().fromJson(response.asString(), ServiceGroupResponse.class);
        ServiceGroup serviceGroup = serviceGroupResponse.data;
        Assert.assertEquals(businessID,serviceGroup.getBusiness_id());
        Assert.assertEquals("UpdatedServicegroupname",serviceGroup.getName());
        Assert.assertTrue(serviceGroup.getDeleted_at().startsWith("2018"));
        this.Ids = serviceGroup.getId();

        ResponseBody responseGet = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+Ids+"/").thenReturn().body();
        ServiceGroupResponse serviceGroupGetResponse = new Gson().fromJson(response.asString(),ServiceGroupResponse.class);
        ServiceGroup serviceGroupGet = serviceGroupGetResponse.data;
        Assert.assertEquals(businessID,serviceGroup.getBusiness_id());
        Assert.assertEquals("UpdatedServicegroupname",serviceGroup.getName());
        Assert.assertTrue(serviceGroup.getDeleted_at().startsWith("2018"));

    }






}
