package BookingREST.WorkingDays;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Staffs.Staff;
import BookingREST.Staffs.StaffData;
import BookingREST.Staffs.StaffResponse;
import BookingREST.Staffs.StaffTests;
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

public class WorkingDaysTests {
    String token;
    String baseURL = "http://213.136.86.27:8083/api/v1.0/working-days/";
    String baseURLBeauty = "http://213.136.86.27:8084/api/v1.0/working-days/";
    String baseUrlByAdress = "http://213.136.86.27:8083/api/v1.0/addresses/";
    String baseURLStaff = "http://213.136.86.27:8084/api/v1.0/staffs/";
    public int id;
    public int idBeauty;
    String rule;
    Faker faker = new Faker();
    String email = faker.name().firstName()+"@mail.com"+"a";
    String phone = faker.regexify("+380[0-9]{9}");
    String ruleStaff;
    int business_id;
    int object_id;
    int object_id2;
    WorkingDaysData workingDaysData = new WorkingDaysData();
    StaffData staffData = new StaffData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.object_id = getBusiness.W_returnAdressId();

        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.createStaff(business_id,object_id,phone,email))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLStaff).thenReturn().body();
        StaffResponse staffResponse= new Gson().fromJson(respons.asString(), StaffResponse.class);
        Staff staff= staffResponse.data;
        this.object_id2 = staff.getId();
    }

    @Test
    public void A_addWorkingDays() {
        WorkingDays days = workingDaysData.addWorkingDays(business_id, object_id,"address");
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(days)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        WorkingDaysResponse workingDaysResponse = new Gson().fromJson(response.asString(), WorkingDaysResponse.class);
        WorkingDays addWorkingDay = workingDaysResponse.data;
        this.id = addWorkingDay.getId();
        Assert.assertEquals(days.getRule(), addWorkingDay.getRule());
        Assert.assertEquals(business_id, addWorkingDay.getBusiness_id());
        Assert.assertEquals("address", addWorkingDay.getObject_type());
        Assert.assertEquals(object_id, addWorkingDay.getObject_id());
        Assert.assertEquals(false,addWorkingDay.isIs_exclusion());
    }
    @Test
    public void B_updateWorkingDays() {
    WorkingDays daysUpdate = workingDaysData.updateWorkingDays();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(daysUpdate)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        WorkingDaysResponse workingDaysResponse = new Gson().fromJson(response.asString(), WorkingDaysResponse.class);
        WorkingDays updateWorkingDay = workingDaysResponse.data;
        this.id = updateWorkingDay.getId();
        this.rule = updateWorkingDay.getRule();
        Assert.assertEquals(daysUpdate.getRule(), updateWorkingDay.getRule());
        Assert.assertEquals(true, updateWorkingDay.isIs_exclusion());
    }
    @Test
    public void C_getWorkingDaysId() {
        WorkingDays daysGet = workingDaysData.updateWorkingDays();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        WorkingDaysResponse workingDaysResponse = new Gson().fromJson(response.asString(), WorkingDaysResponse.class);
        WorkingDays getWorkingDay = workingDaysResponse.data;
        this.id = getWorkingDay.getId();
        Assert.assertEquals(rule,getWorkingDay.getRule());
        Assert.assertEquals(business_id, getWorkingDay.getBusiness_id());
        Assert.assertEquals("address", getWorkingDay.getObject_type());
        Assert.assertEquals(object_id, getWorkingDay.getObject_id());
        Assert.assertEquals(true, getWorkingDay.isIs_exclusion());
        Assert.assertEquals(true, getWorkingDay.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void D_getWorkingDaysQuery() {
        WorkingDays daysGetQuery = workingDaysData.updateWorkingDays();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + "?business_id=" + business_id).thenReturn().body();
        WorkingDaysResponseArray workingDaysResponseArray = new Gson().fromJson(response.asString(), WorkingDaysResponseArray.class);
        WorkingDays getWorkingDayQuery = workingDaysResponseArray.data.get(0);
        this.id = getWorkingDayQuery.getId();
        Assert.assertEquals(rule,getWorkingDayQuery.getRule());
        Assert.assertEquals(business_id, getWorkingDayQuery.getBusiness_id());
        Assert.assertEquals("address", getWorkingDayQuery.getObject_type());
        Assert.assertEquals(object_id, getWorkingDayQuery.getObject_id());
        Assert.assertEquals(true, getWorkingDayQuery.isIs_exclusion());
        Assert.assertEquals(true, getWorkingDayQuery.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void E_getAdressIdWorkingDays() {
        WorkingDays daysGetByAdress = workingDaysData.updateWorkingDays();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrlByAdress+object_id+"/working-days/").thenReturn().body();
        WorkingDaysResponseArray workingDaysResponseArray = new Gson().fromJson(response.asString(), WorkingDaysResponseArray.class);
        WorkingDays getWorkingDayByAdress = workingDaysResponseArray.data.get(0);
        this.id = getWorkingDayByAdress.getId();
        Assert.assertEquals(rule,getWorkingDayByAdress.getRule());
        Assert.assertEquals(business_id, getWorkingDayByAdress.getBusiness_id());
        Assert.assertEquals("address", getWorkingDayByAdress.getObject_type());
        Assert.assertEquals(object_id, getWorkingDayByAdress.getObject_id());
        Assert.assertEquals(true, getWorkingDayByAdress.isIs_exclusion());
        Assert.assertEquals(true, getWorkingDayByAdress.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void F_deleteWorkingDays() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        WorkingDaysResponse workingDaysResponse = new Gson().fromJson(response.asString(), WorkingDaysResponse.class);
        WorkingDays deleteWorkingDay = workingDaysResponse.data;
        Assert.assertEquals(id, deleteWorkingDay.getId());
        Assert.assertEquals(true, deleteWorkingDay.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void G_addWorkingDaysBeauty() {
        WorkingDays daysBeauty = workingDaysData.addWorkingDaysBeauty(business_id, object_id2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(daysBeauty)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLBeauty).thenReturn().body();
        WorkingDaysResponse workingDaysResponse = new Gson().fromJson(response.asString(), WorkingDaysResponse.class);
        WorkingDays addWorkingDayBeauty = workingDaysResponse.data;
        this.idBeauty = addWorkingDayBeauty.getId();
        this.ruleStaff = addWorkingDayBeauty.getRule();
        Assert.assertEquals(daysBeauty.getRule(), addWorkingDayBeauty.getRule());
        Assert.assertEquals(business_id, addWorkingDayBeauty.getBusiness_id());
        Assert.assertEquals("staff", addWorkingDayBeauty.getObject_type());
        Assert.assertEquals(object_id2, addWorkingDayBeauty.getObject_id());
        Assert.assertEquals(false,addWorkingDayBeauty.isIs_exclusion());
    }
}
