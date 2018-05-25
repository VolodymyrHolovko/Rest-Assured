package BookingREST.Staffs;

import Auth.Users.GetUserToken;
import BookingREST.Addresses.Address;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Comments.CommentData;
import BookingREST.Comments.Comments;
import BookingREST.Comments.CommentsResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
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
    String email = faker.name().firstName()+"@mail.com"+"a";
    String phone = faker.regexify("+380[0-9]{9}");
    int addresId;
    StaffData staffData = new StaffData();
    CreateBusiness createBusiness = new CreateBusiness();



    @BeforeClass
    public void beforeClass(){
        businessId = createBusiness.validBusiness();
        addresId = createBusiness.W_returnAdressId();

        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        GetUserToken getUserToken1= new GetUserToken();
        this.userId = getUserToken1.GetUserId();
    }

    @Test
    public void A_createStaff(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.createStaff(businessId,addresId,phone,email))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        StaffResponse staffResponse= new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff= staffResponse.data;
        this.staffId = staff.getId();
        Assert.assertEquals(businessId,staff.getBusiness_id());
        Assert.assertEquals(addresId,staff.getAddress_id());
        Assert.assertEquals("Max",staff.getFirst_name());
        Assert.assertEquals("Lutkovec",staff.getLast_name());
        Assert.assertEquals(phone,staff.getPhone());
        Assert.assertEquals(email,staff.getEmail());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",staff.getPhoto());
        Assert.assertEquals("Паріхмахєр",staff.getPosition());
        Assert.assertEquals("Підстрижу по повній",staff.getDescription());
        Assert.assertEquals(1,staff.getStatus());
    }

    @Test
        public void B_UpdateStaff(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.updateStaff(phone,email))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+staffId+"/").thenReturn().body();
        StaffResponse staffResponse= new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff= staffResponse.data;
        Assert.assertEquals("Maxa",staff.getFirst_name());
        Assert.assertEquals("Lutkoveca",staff.getLast_name());
        Assert.assertEquals(phone,staff.getPhone());
        Assert.assertEquals(email,staff.getEmail());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png",staff.getPhoto());
        Assert.assertEquals("Паріхмахєр",staff.getPosition());
        Assert.assertEquals("Підстрижу по повній",staff.getDescription());
    }
}
