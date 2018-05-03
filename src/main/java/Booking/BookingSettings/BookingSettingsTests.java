package Booking.BookingSettings;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class BookingSettingsTests {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/BookingSettings";
    String token;
    String adressId ="?addressId=2";
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
        System.out.println(response.asString());
        this.id = bookingSettings.getId();
        Assert.assertEquals(id, bookingSettings.getId());
        Assert.assertEquals(true, bookingSettings.isAutomaticBookingConfirmation());
        Assert.assertEquals(true, bookingSettings.isAutomaticBookingRejection());
        Assert.assertEquals(50, bookingSettings.getMaxAmountOfDaysAdvanceForBooking());
        Assert.assertEquals(3600000, bookingSettings.getAvailableTimeForCreateBooking());
        Assert.assertEquals(12, bookingSettings.getMaxAmountPeopleForBooking());
        Assert.assertEquals(1800000, bookingSettings.getAvailableTimeForEditBooking());
        Assert.assertEquals(3600000, bookingSettings.getMinimumDurationOfBooking());
        Assert.assertEquals(300000, bookingSettings.getServiceTimeAfterBookingEnd());
        Assert.assertEquals(true, bookingSettings.isBookingIsAllowed());
        Assert.assertEquals("Europe/Kiev", bookingSettings.getIdTimeZone());
        Assert.assertEquals(2, bookingSettings.getAddressId());
        Assert.assertEquals(7, bookingSettings.getWorkSchedule().size());
    }
    @Test
    public void B_updateBookingSettings() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingSettingsData.updateBookingSettings(id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI).thenReturn().body();
        BookingSettingsResponse bookingSettingsResponse = new Gson().fromJson(response.asString(), BookingSettingsResponse.class);
        BookingSettings updateBookingSettings = bookingSettingsResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals(id, updateBookingSettings.getId());
        Assert.assertEquals(true, updateBookingSettings.isAutomaticBookingConfirmation());
        Assert.assertEquals(true, updateBookingSettings.isAutomaticBookingRejection());
        Assert.assertEquals(50, updateBookingSettings.getMaxAmountOfDaysAdvanceForBooking());
        Assert.assertEquals(3600000, updateBookingSettings.getAvailableTimeForCreateBooking());
        Assert.assertEquals(12, updateBookingSettings.getMaxAmountPeopleForBooking());
        Assert.assertEquals(1800000, updateBookingSettings.getAvailableTimeForEditBooking());
        Assert.assertEquals(3600000, updateBookingSettings.getMinimumDurationOfBooking());
        Assert.assertEquals(300000, updateBookingSettings.getServiceTimeAfterBookingEnd());
        Assert.assertEquals(true, updateBookingSettings.isBookingIsAllowed());
        Assert.assertEquals("Europe/Kiev", updateBookingSettings.getIdTimeZone());
        Assert.assertEquals(2, updateBookingSettings.getAddressId());
        Assert.assertEquals(7, updateBookingSettings.getWorkSchedule().size());
    }
    @Test
    public void C_getBookingSettings() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+adressId).thenReturn().body();
        BookingSettingsResponse bookingSettingsResponse = new Gson().fromJson(response.asString(), BookingSettingsResponse.class);
        BookingSettings getBookingSettings = bookingSettingsResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals(id, getBookingSettings.getId());
        Assert.assertEquals(true, getBookingSettings.isAutomaticBookingConfirmation());
        Assert.assertEquals(true, getBookingSettings.isAutomaticBookingRejection());
        Assert.assertEquals(50, getBookingSettings.getMaxAmountOfDaysAdvanceForBooking());
        Assert.assertEquals(3600000, getBookingSettings.getAvailableTimeForCreateBooking());
        Assert.assertEquals(12, getBookingSettings.getMaxAmountPeopleForBooking());
        Assert.assertEquals(1800000, getBookingSettings.getAvailableTimeForEditBooking());
        Assert.assertEquals(3600000, getBookingSettings.getMinimumDurationOfBooking());
        Assert.assertEquals(300000, getBookingSettings.getServiceTimeAfterBookingEnd());
        Assert.assertEquals(true, getBookingSettings.isBookingIsAllowed());
        Assert.assertEquals("Europe/Kiev", getBookingSettings.getIdTimeZone());
        Assert.assertEquals(2, getBookingSettings.getAddressId());
        Assert.assertEquals(7, getBookingSettings.getWorkSchedule().size());
    }

}

