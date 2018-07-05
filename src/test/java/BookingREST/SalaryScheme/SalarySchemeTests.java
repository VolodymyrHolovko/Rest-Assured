package BookingREST.SalaryScheme;

import Auth.Users.GetUserToken;
import BookingREST.Bookings.Booking;
import BookingREST.Bookings.BookingsResponse;
import BookingREST.Businesses.CreateBusiness;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class SalarySchemeTests {
    String baseUrl = "http://staging.eservia.com:8087/api/v1.0/salary-schemes/";
    SalarySchemeData salarySchemeData = new SalarySchemeData();
    int businesId;
    int staffId;
    String token;

    @BeforeClass
    public void preTests(){
        CreateBusiness createBusiness = new CreateBusiness();
        this.businesId=createBusiness.validBusiness();
        this.staffId=createBusiness.B_returnStaff();
        this.token = createBusiness.B_returncreatedToken();
    }

    @Test
    public void A_createSalaryScheme(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(salarySchemeData.createSalaryScheme(businesId,staffId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        SalarySchemeResponse salarySchemeResponse= new Gson().fromJson(response.asString(), SalarySchemeResponse.class);
        SalaryScheme salaryScheme = salarySchemeResponse.data;
    }
}
