package Booking;

import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class BookingTest {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/Bookings/";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjE0MjE2OCwibmJmIjoxNTIyMTQyMTY4LCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIyMjI4NTY4LCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxNDYsMTYxLDE4OV19LHsiaWQiOjU2LCJhZGRyZXNzZXMiOlsxNTFdfV19.Cfg8Aq9wkXAU2QHBlYq2Qi4pewaWl4if3GvMgQrfrhr52LD9WY_TaiLy5onLyxR839LrfHwhBrznbLAhP8VjEcqSd_GQQ1YML2i5e8JEjmYsXeMbKgklpEfwA5fOq-3_THrBQEkCm6IFFDACDN7pQRWWIDfQtrHLv2lPl3mjPNQirGC0vVVfFngJ10Enw_w1HpPMEzbmOE5SFbmBqJpmo1e3pAmMn6xpympkeOBGegTgn6F2Sf-1-nWcaC9ILurAVtH_C22553aG1iUGJrUTDiSdvOUX4Q-oSOSWhMtS5jPqDKXpkNU9rWsZzQzER5K11UfE9uv5nmUUOdHltAenqA";
    public  int id;
    BookingData bookingData = new BookingData();

    @Test
    public void addBookingAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(bookingData.addBookingAdmin())
                .when().post(baseURI+ "/" +"Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);  
    }
}
