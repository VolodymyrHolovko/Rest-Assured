package BookingREST.ServiceGroups.Services;

import BookingREST.Addresses.Address;
import BookingREST.Addresses.AddressData;
import BookingREST.Addresses.AddressResponse;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
//import org.testng.annotations.BeforeClass;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.ServiceGroups.ServiceGroup;
import BookingREST.ServiceGroups.ServiceGroupData;
import BookingREST.ServiceGroups.ServiceGroupResponse;
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


import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class ServiceTests {
        String token;
        private String baseURL = "http://213.136.86.27:8084/api/v1.0/services/";
        private String baseURLServiceGroups = "http://213.136.86.27:8084/api/v1.0/service-groups/";
        private String baseURLAddresses8083 = "http://213.136.86.27:8083/api/v1.0/addresses/";
        private String baseURLAddresses8084 = "http://213.136.86.27:8084/api/v1.0/addresses/";
        int Ids;
        int additionalServiceID;
        int businessID;
        int serviceGroupID;
        int addressID;
        ServiceData serviceData = new ServiceData();
        CreateBusiness createBusiness = new CreateBusiness();



    @BeforeClass
    public void getToken() {
        businessID = createBusiness.validBusiness();
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();

        ServiceGroupData serviceGroupData = new ServiceGroupData();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceGroupData.CreateServiceGroup(businessID))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLServiceGroups).thenReturn().body();
        ServiceGroupResponse serviceGroupResponse = new Gson().fromJson(response.asString(), ServiceGroupResponse.class);
        ServiceGroup serviceGroup = serviceGroupResponse.data;
        this.serviceGroupID = serviceGroup.getId();
        addressID=createBusiness.W_returnAdressId();
        }

    @Test
        public void A_CreateService(){
            ResponseBody response = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(serviceData.CreateService(businessID,serviceGroupID))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post(baseURL).thenReturn().body();
            ServiceResponse serviceResponse = new Gson().fromJson(response.asString(), ServiceResponse.class);
            Service service = serviceResponse.data;
            this.Ids = service.getId();
            Assert.assertEquals(businessID,service.getBusiness_id());
            Assert.assertEquals(serviceGroupID,service.getService_group_id());
            Assert.assertEquals("ServiceName",service.getName());
            Assert.assertEquals(20,service.getDuration());
            Assert.assertEquals(700,service.getPrice());
            Assert.assertEquals("UAH",service.getCurrency());
            Assert.assertEquals(1,service.getStatus());


        ResponseBody responseAdditionalService = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceData.CreateService(businessID,serviceGroupID))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ServiceResponse serviceAdditionalResponse = new Gson().fromJson(responseAdditionalService.asString(), ServiceResponse.class);
        Service additionalService = serviceAdditionalResponse.data;
        additionalServiceID = additionalService.getId();
    }

    @Test
    public void B_UpdateService(){
            ResponseBody response = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(serviceData.UpdateService())
                    .when().put(baseURL+Ids+"/").thenReturn().body();
        ServiceResponse serviceResponse = new Gson().fromJson(response.asString(), ServiceResponse.class);
        Service service = serviceResponse.data;
        this.Ids = service.getId();
        Assert.assertEquals(businessID,service.getBusiness_id());
        Assert.assertEquals(serviceGroupID,service.getService_group_id());
        Assert.assertEquals("updatedServiceName",service.getName());
        Assert.assertEquals(30,service.getDuration());
        Assert.assertEquals(800,service.getPrice());
        Assert.assertEquals("USD",service.getCurrency());
    }


    @Test
    public void C_GetUpdatedService(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceData.UpdateService())
                .when().get(baseURL+Ids+"/").thenReturn().body();
        ServiceResponse serviceResponse = new Gson().fromJson(response.asString(), ServiceResponse.class);
        Service service = serviceResponse.data;
        this.Ids = service.getId();
        Assert.assertEquals(businessID,service.getBusiness_id());
        Assert.assertEquals(serviceGroupID,service.getService_group_id());
        Assert.assertEquals("updatedServiceName",service.getName());
        Assert.assertEquals(30,service.getDuration());
        Assert.assertEquals(800,service.getPrice());
        Assert.assertEquals("USD",service.getCurrency());
        Assert.assertEquals(1,service.getStatus());
    }

    @Test
    public void D_DeactivateService(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceData.UpdateService())
                .when().patch(baseURL+Ids+"/deactivate/").thenReturn().body();
        ServiceResponse serviceResponse = new Gson().fromJson(response.asString(), ServiceResponse.class);
        Service service = serviceResponse.data;
        this.Ids = service.getId();
        Assert.assertEquals(businessID,service.getBusiness_id());
        Assert.assertEquals(serviceGroupID,service.getService_group_id());
        Assert.assertEquals("updatedServiceName",service.getName());
        Assert.assertEquals(30,service.getDuration());
        Assert.assertEquals(800,service.getPrice());
        Assert.assertEquals("USD",service.getCurrency());
        Assert.assertEquals(0,service.getStatus());
    }


    @Test
    public void E_ActivateService(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceData.UpdateService())
                .when().patch(baseURL+Ids+"/activate/").thenReturn().body();
        ServiceResponse serviceResponse = new Gson().fromJson(response.asString(), ServiceResponse.class);
        Service service = serviceResponse.data;
        this.Ids = service.getId();
        Assert.assertEquals(businessID,service.getBusiness_id());
        Assert.assertEquals(serviceGroupID,service.getService_group_id());
        Assert.assertEquals("updatedServiceName",service.getName());
        Assert.assertEquals(30,service.getDuration());
        Assert.assertEquals(800,service.getPrice());
        Assert.assertEquals("USD",service.getCurrency());
        Assert.assertEquals(1,service.getStatus());
    }

    @Test
    public void F_GetAllServices(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void G_GetServicesByServiceGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get(baseURLServiceGroups+serviceGroupID+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(Ids,addressServices.get(0).getId());
        Assert.assertEquals(additionalServiceID,addressServices.get(1).getId());
    }


    @Test
    public void H_AttachServiceToAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body("{ \"services\": [ "+Ids+","+additionalServiceID+" ] }")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLAddresses8084+addressID+"/services/").thenReturn().body();
                 AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
                 List<Service> addressServices = addressServicesResponse.getData();
                 Assert.assertEquals(Ids,addressServices.get(0).getService_id());
                 Assert.assertEquals(additionalServiceID,addressServices.get(1).getService_id());
    }


    @Test
    public void I_GetServicesByAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLAddresses8084+addressID+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(Ids,addressServices.get(0).getId());
        Assert.assertEquals(additionalServiceID,addressServices.get(1).getId());
    }


    @Test
    public void J_DetachServicesFromAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body("{ \"services\": [ "+Ids+" ] }")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLAddresses8084+addressID+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(additionalServiceID,addressServices.get(0).getId());
    }


}
