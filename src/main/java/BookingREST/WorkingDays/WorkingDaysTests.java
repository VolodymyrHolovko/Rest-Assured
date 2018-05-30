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
    String baseUrlByAdress = "http://213.136.86.27:8083/api/v1.0/addresses/";
    public int id;
    String rule;
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
    public void F_deleteAdress() {
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
}
