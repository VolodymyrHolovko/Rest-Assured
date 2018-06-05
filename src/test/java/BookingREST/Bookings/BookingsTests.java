package BookingREST.Bookings;

import Auth.Users.GetUserToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class BookingsTests {
    String baseUrl = "http://213.136.86.27:8085/api/v1.0/bookings/";
    BookingsData bookingsData = new BookingsData();
    int businesId;
    int adressId;
    int staffId;
    int serviceId;
    String usertoken;
    String token;
    String userId = "f4423ba3-545b-4cee-ad63-c030f1dbf8e3";


    @BeforeClass
    public void preTests(){
        CreateBusiness createBusiness = new CreateBusiness();
        this.businesId=createBusiness.validBusiness();
        this.adressId=createBusiness.A_returnAdressId();
        this.staffId=createBusiness.B_returnStaff();
        this.serviceId=createBusiness.B_returnService();

        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        this.token = createBusiness.B_returncreatedToken();


    }

    @Test
    public void createBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingsData.createBooking(businesId,adressId,staffId,serviceId,userId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
    }
}
