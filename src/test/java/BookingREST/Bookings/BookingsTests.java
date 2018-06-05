package BookingREST.Bookings;

import Auth.Users.GetUserToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Favorites.Favorites;
import BookingREST.Favorites.FavoritesResponse;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class BookingsTests {
    String baseUrl = "http://213.136.86.27:8085/api/v1.0/bookings/";
    BookingsData bookingsData = new BookingsData();
    int bookingId;
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
        BookingsResponse bookingsResponse= new Gson().fromJson(response.asString(), BookingsResponse.class);
        Booking booking = bookingsResponse.data;
        this.bookingId = booking.getId();
        Assert.assertEquals(businesId,booking.getBusiness_id());
        Assert.assertEquals(adressId,booking.getAddress_id());
        Assert.assertEquals(staffId,booking.getStaff_id());
        Assert.assertEquals(serviceId,booking.getService_id());
        Assert.assertEquals(20,booking.getDuration());
        Assert.assertEquals(700,booking.getAmount());
        Assert.assertEquals("f4423ba3-545b-4cee-ad63-c030f1dbf8e3",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-06-25 17:00:00",booking.getDate());
    }

    @Test
    public void updateBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingsData.updateBooking(staffId,serviceId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseUrl+bookingId+"/").thenReturn().body();
        BookingsResponse bookingsResponse= new Gson().fromJson(response.asString(), BookingsResponse.class);
        Booking booking = bookingsResponse.data;
        Assert.assertEquals(businesId,booking.getBusiness_id());
        Assert.assertEquals(adressId,booking.getAddress_id());
        Assert.assertEquals(staffId,booking.getStaff_id());
        Assert.assertEquals(serviceId,booking.getService_id());
        Assert.assertEquals(20,booking.getDuration());
        Assert.assertEquals(700,booking.getAmount());
        Assert.assertEquals("f4423ba3-545b-4cee-ad63-c030f1dbf8e3",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-06-25 14:00:00",booking.getDate());
    }

    @Test
    public void getBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+bookingId+"/").thenReturn().body();
        BookingsResponse bookingsResponse= new Gson().fromJson(response.asString(), BookingsResponse.class);
        Booking booking = bookingsResponse.data;
        Assert.assertEquals(businesId,booking.getBusiness_id());
        Assert.assertEquals(adressId,booking.getAddress_id());
        Assert.assertEquals(staffId,booking.getStaff_id());
        Assert.assertEquals(serviceId,booking.getService_id());
        Assert.assertEquals(20,booking.getDuration());
        Assert.assertEquals(700,booking.getAmount());
        Assert.assertEquals("f4423ba3-545b-4cee-ad63-c030f1dbf8e3",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-06-25 14:00:00",booking.getDate());
    }

    @AfterClass
    public void deletePreTest(){
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + businesId + "/").thenReturn().body();

    }
}
