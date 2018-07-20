package BookingREST.Businesses;

import Auth.Users.GetUserToken;
import BookingREST.Addresses.Address;
import BookingREST.Addresses.AddressData;
import BookingREST.Addresses.AddressResponse;
import BookingREST.AuthBusiness.AuthBusiness;
import BookingREST.AuthBusiness.AuthBusinessData;
import BookingREST.AuthBusiness.AuthBusinessResponse;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Comments.CommentData;
import BookingREST.Plans.*;
import BookingREST.Promoter.Promoter;
import BookingREST.Promoter.PromoterData;
import BookingREST.Promoter.PromoterResponse;
import BookingREST.Sector.Sector;
import BookingREST.Sector.SectorData;
import BookingREST.Sector.SectorResponse;
import BookingREST.ServiceGroups.ServiceGroup;
import BookingREST.ServiceGroups.ServiceGroupData;
import BookingREST.ServiceGroups.ServiceGroupResponse;
import BookingREST.ServiceGroups.Services.Service;
import BookingREST.ServiceGroups.Services.ServiceData;
import BookingREST.ServiceGroups.Services.ServiceResponse;
import BookingREST.Staffs.Staff;
import BookingREST.Staffs.StaffData;
import BookingREST.Staffs.StaffResponse;
import BookingREST.Strategy.Strategy;
import BookingREST.Strategy.StrategyData;
import BookingREST.Strategy.StrategyResponse;
import BookingREST.WorkingDays.WorkingDays;
import BookingREST.WorkingDays.WorkingDaysData;
import BookingREST.WorkingDays.WorkingDaysResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class


CreateBusiness {
    String token;
    AuthBusinessData authBusinessData = new AuthBusinessData();
    StaffData staffData = new StaffData();
    ServiceData serviceData = new ServiceData();
    ServiceGroupData serviceGroupData = new ServiceGroupData();
    CommentData commentData = new CommentData();
    SectorData sectorData = new SectorData();
    BusinesessData businesessData = new BusinesessData();
    PromoterData promoterData = new PromoterData();
    StrategyData strategyData = new StrategyData();
    PlanData planData = new PlanData();
    WorkingDaysData workingDaysData = new WorkingDaysData();
    AddressData addressData = new AddressData();
    PlanSettingsData planSettingsData = new PlanSettingsData();
    String baseUrl = "https://staging.eservia.com:8083/api/v1.0/businesses/";
    char rc = (char)('A' + new Random().nextInt(600));
    Faker faker = new Faker();
    String sectorName = faker.name().username()+faker.name().firstName()+rc;
    String firstName = faker.name().firstName()+faker.name().firstName()+rc;
    String lastName = faker.name().username()+faker.name().firstName()+rc;
    String name = faker.name().username()+faker.name().firstName()+rc;
    String alias = faker.name().firstName().toLowerCase()+faker.name().firstName().toLowerCase()+rc;
    String email = faker.name().lastName()+faker.name().firstName()+rc+"@mail.com";
    String phone = faker.regexify("+380[0-9]{9}");
    int sectorId;
    int promoterId;
    int strategyId;
    int businessId;
    int adressId;
    int planId;
    String usertoken;
    String uesrId;
    int staffId;
    int serviceGroupId;
    int serviceId;
    String username;
    String password="12345678";
    String createdtoken;

    public int validBusiness(){


        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        GetUserToken getUserToken1= new GetUserToken();
        this.uesrId = getUserToken1.GetUserId();

        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();


        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.createSector(sectorName))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/sectors/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(respons.asString(), SectorResponse.class);
        Sector sector = sectorResponse.getData();
        this.sectorId =sector.getId();

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.addPromoters(firstName, lastName, email, phone))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/promoters/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter addPromoter = promoterResponse.getData();
        this.promoterId = addPromoter.getId();
        this.username = addPromoter.getEmail();

        ResponseBody responses = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(strategyData.addPromoters(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/strategies/").thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(responses.asString(), StrategyResponse.class);
        Strategy addStrategy = strategyResponse.getData();
        System.out.println(response.asString());
        this.strategyId = addStrategy.getId();

        ResponseBody responseess = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(planData.freePlan(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/plans/").thenReturn().body();
        PlanResponse planResponse = new  Gson().fromJson(responseess.asString(), PlanResponse.class);
        Plan plan = planResponse.getData();
        this.planId = plan.getId();

        ResponseBody responser = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body(planSettingsData.UpdatePlanSettings())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put("https://staging.eservia.com:8083/api/v1.0/plans/"+planId+"/settings/").thenReturn().body();


        ResponseBody responsee = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.createBusinesses(promoterId,1,1,alias))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/businesses/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(responsee.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(promoterId,businesses.getPromoter_id());
        Assert.assertEquals(1,businesses.getStrategy_id());
//        Assert.assertEquals(sectorId,businesses.getSector_id());
        Assert.assertEquals("maximum",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія",businesses.getDescription());
        Assert.assertEquals(alias,businesses.getAlias());
        Assert.assertEquals(false,businesses.is_verified);
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0",businesses.getUrl());
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec",businesses.getLink_facebook());

        ResponseBody responss = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/plans/"+planId+"/subscribe/").thenReturn().body();

        ResponseBody responseessa = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addressData.CreateAddress(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/addresses/").thenReturn().body();
        AddressResponse addressResponse= new Gson().fromJson(responseessa.asString(), AddressResponse.class);
        Address address= addressResponse.getData();
        this.adressId = address.getId();

        ResponseBody response1 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/verify/").thenReturn().body();
        BusinesessResponse businesessResponse1= new Gson().fromJson(response1.asString(), BusinesessResponse.class);
        Businesses businesses1= businesessResponse1.data;
        Assert.assertEquals(true,businesses1.is_verified);

        ResponseBody staffsees = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.createStaff(businessId,adressId,phone,email))
                .when().post("https://staging.eservia.com:8084/api/v1.0/staffs/").thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(staffsees.asString(), StaffResponse.class);
        Staff staff = staffResponse.getData();
        this.staffId = staff.getId();

        WorkingDays days = workingDaysData.hardCode(businessId, adressId,"address");
        ResponseBody response7 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(days)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/working-days/").thenReturn().body();


        WorkingDays dayss = workingDaysData.hardCode(businessId, staffId,"staff");
        ResponseBody response8 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(dayss)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8084/api/v1.0/working-days/").thenReturn().body();

        ResponseBody response5 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceGroupData.CreateServiceGroup(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8084/api/v1.0/service-groups/").thenReturn().body();
        ServiceGroupResponse serviceGroupResponse = new Gson().fromJson(response5.asString(), ServiceGroupResponse.class);
        ServiceGroup serviceGroup = serviceGroupResponse.data;
        this.serviceGroupId = serviceGroup.getId();

        ResponseBody response6 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(serviceData.CreateService(businessId,serviceGroupId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8084/api/v1.0/services/").thenReturn().body();
        ServiceResponse serviceResponse = new Gson().fromJson(response6.asString(), ServiceResponse.class);
        Service service = serviceResponse.getData();
        this.serviceId = service.getId();
        Assert.assertEquals(businessId,service.getBusiness_id());

        ResponseBody responseed = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body("{ \"services\": [ "+adressId+","+serviceId+" ] }")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8084/api/v1.0/addresses/"+adressId+"/services/").thenReturn().body();

        ResponseBody responsed = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body("{ \"services\": [ "+staffId+","+serviceId+" ] }")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8084/api/v1.0/staffs/"+staffId+"/services/").thenReturn().body();

        ResponseBody responseBody = given().
                contentType(ContentType.JSON)
                .body(authBusinessData.createdPromoter(username,password))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/auth/sign-in").thenReturn().body();
        AuthBusinessResponse authBusinessResponse = new Gson().fromJson(responseBody.asString(), AuthBusinessResponse.class);
        AuthBusiness authBusiness = authBusinessResponse.getData();
        this.createdtoken = "Bearer "+authBusiness.getAccess_token();

        return businessId;
    }

    public int A_returnAdressId(){
        return  adressId;
    }
    public int B_returnStaff(){
        return  staffId;
    }
    public int B_returnService(){
        return  serviceId;
    }
    public String B_returncreatedToken(){
        return  createdtoken;
    }
    public int returnPromoter(){
        return  promoterId;
    }
    public int returnPlan(){
        return  planId;
    }
}
