package Booking;

import Auth.GetToken;
import Departments.Department;
import Departments.DepartmentResponse;
import Departments.Tables.TableResponse;
import Departments.Tables.Tables;
import Departments.Tables.TablesTest;
import Departments.Tables.TablesData;
import Departments.DepartmentData;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.given;

public class BookingTest {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/Bookings";
    String token;
    int TableId;
    int DEpIds;
    public  int id;
    BookingData bookingData = new BookingData();

@BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }
    @Test
    public void addBookingAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(bookingData.addBookingAdmin(TableId, DEpIds))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI+ "/" + "Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking booking = bookingResponse.data;
        System.out.println(response.asString());
        this.id = booking.getId();
        Assert.assertEquals(101,booking.getDepartmentId());
        Assert.assertEquals(114,booking.getTableIds().get(0).intValue());
        Assert.assertEquals(3,booking.getPeopleCount());
        Assert.assertEquals("хочу живої музики без мертвих музикантів", booking.getRequestDescription());
        Assert.assertEquals("2018-04-12T18:55:33.000", booking.getBookingDateTime());
        Assert.assertEquals("2018-04-12T19:40:33.000", booking.getBookingEndTime());
        Assert.assertEquals(1, booking.getAddressId());
    }
}
