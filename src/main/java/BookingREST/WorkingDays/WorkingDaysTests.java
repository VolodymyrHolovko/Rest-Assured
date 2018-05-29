package BookingREST.WorkingDays;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
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
    String dateTime;
    String endTime;
    public int id;
    int business_id;
    int object_id;
    WorkingDaysData workingDaysData = new WorkingDaysData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.object_id = getBusiness.W_returnAdressId();
    }

    @Test
    public void A_addWorkingDays() {
        WorkingDays days = workingDaysData.addWorkingDays(business_id, object_id);
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
        Assert.assertEquals(daysGet.getRule(),getWorkingDay.getRule());
        Assert.assertEquals(business_id, getWorkingDay.getBusiness_id());
        Assert.assertEquals("address", getWorkingDay.getObject_type());
        Assert.assertEquals(object_id, getWorkingDay.getObject_id());
        Assert.assertEquals(true, getWorkingDay.isIs_exclusion());
        Assert.assertEquals(true, getWorkingDay.getUpdated_at().startsWith("2018"));
    }
}
