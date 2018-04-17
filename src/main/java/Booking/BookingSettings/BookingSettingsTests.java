package Booking.BookingSettings;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class BookingSettingsTests {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/BookingSettings";
    String token;
    int id;
    BookingSettingsData bookingSettingsData = new BookingSettingsData();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }
    @Test
    public  void A_addBookingSettings() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingSettingsData.addBookingSettings())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        BookingSettingsResponse bookingSettingsResponse = new Gson().fromJson(response.asString(), BookingSettingsResponse.class);
        BookingSettings bookingSettings = bookingSettingsResponse.data;
        System.out.println(response.asInputStream());
        this.id = bookingSettings.getId();
    }
}
