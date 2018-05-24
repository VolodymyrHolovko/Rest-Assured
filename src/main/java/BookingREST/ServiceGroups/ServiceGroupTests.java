package BookingREST.ServiceGroups;


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



public class ServiceGroupTests {
    private String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJidXNpbmVzcy5wcm9tb3RlciIsImlhdCI6MTUyNzE1MDg5MCwiZXhwIjoxNTI3MTU0NDkwLCJuYmYiOjE1MjcxNTA4OTAsImp0aSI6IlJZMnR6cDJWaTlHc0hmbzEiLCJzdWIiOjIxLCJwcnYiOiI4NjQ0MmUzNDE2Y2NlOWVkMmI1NjhhOTAxZTJmMTExZjUzMjlmNDc1IiwiYnVzaW5lc3NlcyI6W3siaWQiOjgwLCJhZGRyZXNzZXMiOls3Ml19LHsiaWQiOjg0LCJhZGRyZXNzZXMiOls3Myw3Niw3OSw5MSw5MiwxMDQsMTA1LDEwNl19LHsiaWQiOjg1LCJhZGRyZXNzZXMiOltdfSx7ImlkIjo5MSwiYWRkcmVzc2VzIjpbXX1dfQ.S-MFahI10SnbDvFDsdscH6vsGniO0fI8KdUAWTF6k_niJbmZKeseVVPO-AbQs1WeY7h7NfXOIf7bnRJbWa7bAQyE73sk-jUeupF157bRwjsS6NvKHmQUZ22nejAZCsEASPxIXEARUMUbL8cnbYL1ukG1b2JWh6QxO4NCFTo6V9IX3SdXmMdRr5ftPjnmfdG2OxQcj5nmFnJJswGjHoPJs-5Xbofc0ShyyLZXLdpmhvyywGljtsTO3aIB0-2kgW8J6kBDiwSD_HEL8rzlPNIJN2iqrFlhGA9Fwkz-B68SOtDXGAl17L2fEKxpABxMgEox8293xfekFcjnHf0QO5EY_g";
    private String baseURL = "http://213.136.86.27:8084/api/v1.0/service-groups/";
    int Ids;
    int businessID = 84;
    ServiceGroupData serviceGroupData = new ServiceGroupData();


   /* @BeforeClass
    public void getToken() {
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
    }*/


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
    public void D_DeleteServicesGroup(){
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


    @Test
    public void GetAllServiceGroups(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());


    }



}
