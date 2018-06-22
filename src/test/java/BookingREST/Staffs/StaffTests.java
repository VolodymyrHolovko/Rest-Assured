package BookingREST.Staffs;

import Auth.Users.GetUserToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.BusinessesTests;
import BookingREST.Businesses.CreateBusiness;
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

import static com.jayway.restassured.RestAssured.given;

public class StaffTests {
    String baseUrl = "http://213.136.86.27:8084/api/v1.0/staffs/";
    String usertoken;
    String token;
    int businessId;
    int staffId;
    String userId;
    Faker faker = new Faker();
    String email = faker.name().firstName() + faker.name().firstName()+"@mail.com" ;
    String phone = faker.regexify("+380[0-9]{9}");
    int addresId;
    int promoterId;
    int planId;
    StaffData staffData = new StaffData();
    CreateBusiness createBusiness = new CreateBusiness();
    BusinessesTests businessesTests = new BusinessesTests();


    @BeforeClass
    public void beforeClass() {
        businessId = createBusiness.validBusiness();
        addresId = createBusiness.A_returnAdressId();
        this.planId = createBusiness.returnPlan();
        this.promoterId = createBusiness.returnPromoter();
        GetUserToken getUserToken = new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        GetUserToken getUserToken1 = new GetUserToken();
        this.userId = getUserToken1.GetUserId();
    }

    @Test
    public void A_createStaff() {
        ResponseBody respons = given().contentType(ContentType.JSON).header("Authorization", token).body(staffData.createStaff(businessId, addresId, phone, email)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().post(baseUrl).thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff = staffResponse.data;
        this.staffId = staff.getId();
        Assert.assertEquals(businessId, staff.getBusiness_id());
        Assert.assertEquals(addresId, staff.getAddress_id());
        Assert.assertEquals("Max", staff.getFirst_name());
        Assert.assertEquals("Lutkovec", staff.getLast_name());
        Assert.assertEquals(phone, staff.getPhone());
        Assert.assertEquals(email, staff.getEmail());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png", staff.getPhoto());
        Assert.assertEquals("Паріхмахєр", staff.getPosition());
        Assert.assertEquals("Підстрижу по повній", staff.getDescription());
        Assert.assertEquals(1, staff.getStatus());
    }

    @Test
    public void B_UpdateStaff() {
        ResponseBody respons = given().contentType(ContentType.JSON).header("Authorization", token).body(staffData.updateStaff(phone, email)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().put(baseUrl + staffId + "/").thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff = staffResponse.data;
        Assert.assertEquals("Maxa", staff.getFirst_name());
        Assert.assertEquals("Lutkoveca", staff.getLast_name());
        Assert.assertEquals(phone, staff.getPhone());
        Assert.assertEquals(email, staff.getEmail());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png", staff.getPhoto());
        Assert.assertEquals("Паріхмахєр", staff.getPosition());
        Assert.assertEquals("Підстрижу по повній", staff.getDescription());
    }

    @Test
    public void C_DeactivateStaff() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().patch(baseUrl + staffId + "/deactivate").thenReturn().body();

        ResponseBody respons = given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().get(baseUrl + staffId + "/").thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff = staffResponse.data;
        Assert.assertEquals(0, staff.getStatus());
    }

    @Test
    public void D_ActivateStaff() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().patch(baseUrl + staffId + "/activate").thenReturn().body();

        ResponseBody respons = given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().get(baseUrl + staffId + "/").thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff = staffResponse.data;
        Assert.assertEquals(1, staff.getStatus());
    }

    @Test
    public void E_getAllStaffs() {
        RequestSpecification httpRequest = RestAssured.given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseUrl);
        Assert.assertEquals(200, response.getStatusCode());
    }


    @Test
    public void G_DeleteStaff() {
        RequestSpecification httpRequest = RestAssured.given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseUrl + staffId + "/");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @AfterClass
    public void deleteBeforee() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + businessId + "/").thenReturn().body();
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


