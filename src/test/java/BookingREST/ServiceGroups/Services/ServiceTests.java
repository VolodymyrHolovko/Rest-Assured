package BookingREST.ServiceGroups.Services;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
//import org.testng.annotations.BeforeClass;
import BookingREST.ServiceGroups.ServiceGroup;
import BookingREST.ServiceGroups.ServiceGroupData;
import BookingREST.ServiceGroups.ServiceGroupResponse;
import BookingREST.Staffs.Staff;
import BookingREST.Staffs.StaffData;
import BookingREST.Staffs.StaffResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.List;
import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class ServiceTests {
        String token;
        private String baseURL = "https://staging.eservia.com:8084/api/v1.0/services/";
        private String baseURLServiceGroups = "https://staging.eservia.com:8084/api/v1.0/service-groups/";
        private String baseURLAddresses8083 = "https://staging.eservia.com:8083/api/v1.0/addresses/";
        private String baseURLAddresses8084 = "https://staging.eservia.com:8084/api/v1.0/addresses/";
        private String baseURLStaff = "https://staging.eservia.com:8084/api/v1.0/staffs/";
        int staffId;
        int Ids;
        int additionalServiceID;
        int businessID;
        int serviceGroupID;
        int addressID;
        int StaffRelationID;
        int GroupRelationID;
        int planId;
        int promoterId;
        int AddresssRelationID;
        ServiceData serviceData = new ServiceData();
        CreateBusiness createBusiness = new CreateBusiness();
    public String randomString(){
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String randomString="";
        int length = 15;
        Random random = new Random();
        char[] text = new char[length];
        for(int i = 0;i<length;i++){
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        for (int i = 0;i<text.length;i++){
            randomString+=text[i];
        }
        return randomString;
    }
        Faker faker = new Faker();
        String email = randomString()+"@mail.com"+"a";
        String phone = faker.regexify("+380[0-9]{9}");
        StaffData staffData = new StaffData();



    @BeforeClass
    public void getToken() {
        this.businessID= createBusiness.validBusiness();
        this.planId=createBusiness.returnPlan();
        this.promoterId = createBusiness.returnPromoter();
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
        ServiceGroup serviceGroup = serviceGroupResponse.getData();
        this.serviceGroupID = serviceGroup.getId();
        addressID=createBusiness.A_returnAdressId();

        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.createStaff(businessID,addressID,phone,email))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLStaff).thenReturn().body();
        StaffResponse staffResponse= new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff= staffResponse.getData();
        this.staffId = staff.getId();
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
        RequestSpecification httpsRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpsRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void G_GetServicesByServiceGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLServiceGroups+serviceGroupID+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(Ids,addressServices.get(0).getId());
        Assert.assertEquals(additionalServiceID,addressServices.get(1).getId());
    }


    @Test
    public void Ga_GetRelationsByServiceGroup(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLServiceGroups+serviceGroupID+"/services/relations/").thenReturn().body();
        Integer idsInteger = Ids;
        Integer additionalIdsInteger = additionalServiceID;
        Assert.assertTrue(response.asString().contains(idsInteger.toString()));
        Assert.assertTrue(response.asString().contains(additionalIdsInteger.toString()));

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
                 AddresssRelationID = addressServices.get(1).getId();
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
        Assert.assertEquals(Ids,addressServices.get(1).getId());
        Assert.assertEquals(additionalServiceID,addressServices.get(2).getId());
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
        Assert.assertEquals(Ids,addressServices.get(0).getService_id());
    }


    @Test
    public void Ja_GetServicesRelationsFromAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLAddresses8084+addressID+"/services/relations/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(additionalServiceID,addressServices.get(1).getService_id());
        Assert.assertEquals(AddresssRelationID,addressServices.get(1).getId());
    }


    @Test
    public void K_AttachServiceToStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body("{ \"services\": [ "+Ids+","+additionalServiceID+" ] }")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLStaff+staffId+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(Ids,addressServices.get(0).getService_id());
        Assert.assertEquals(additionalServiceID,addressServices.get(1).getService_id());
        StaffRelationID = addressServices.get(1).getId();
    }

    @Test
    public void L_GetServicesByStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLStaff+staffId+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(Ids,addressServices.get(0).getId());
        Assert.assertEquals(additionalServiceID,addressServices.get(1).getId());
    }


    @Test
    public void M_DetachServicesFromStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body("{ \"services\": [ "+Ids+" ] }")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLStaff+staffId+"/services/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(Ids,addressServices.get(0).getService_id());
    }

    @Test
    public  void  N_GetRelationsByStaff(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLStaff+staffId+"/services/relations/").thenReturn().body();
        AddressServicesResponse addressServicesResponse = new Gson().fromJson(response.asString(), AddressServicesResponse.class);
        List<Service> addressServices = addressServicesResponse.getData();
        Assert.assertEquals(additionalServiceID,addressServices.get(0).getService_id());
        Assert.assertEquals(StaffRelationID,addressServices.get(0).getId());
    }

    @Test
    public  void  O_DeleteService(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+Ids+"/").thenReturn().body();
        ServiceResponse serviceResponse = new Gson().fromJson(response.asString(), ServiceResponse.class);
        Service service = serviceResponse.data;
        Assert.assertEquals(businessID,service.getBusiness_id());
        Assert.assertEquals("updatedServiceName",service.getName());
        Assert.assertTrue(service.getDeleted_at().startsWith("2018"));
        this.Ids = service.getId();

        ResponseBody responseGet = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+Ids+"/").thenReturn().body();
        ServiceResponse serviceGetResponse = new Gson().fromJson(response.asString(),ServiceResponse.class);
        Service serviceGet = serviceGetResponse.data;
        Assert.assertEquals(businessID,serviceGet.getBusiness_id());
        Assert.assertEquals("updatedServiceName",serviceGet.getName());
        Assert.assertTrue(serviceGet.getDeleted_at().startsWith("2018"));
    }
    @AfterClass
    public void deletePreTest(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/businesses/" + businessID + "/").thenReturn().body();
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();
        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }

}
