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
    String baseUrl = "http://staging.eservia.com:8085/api/v1.0/bookings/";
    BookingsData bookingsData = new BookingsData();
    int bookingId;
    int businesId;
    int adressId;
    int staffId;
    int serviceId;
    String usertoken;
    String token;
    String customerId;
    int promoterId;
    int planId;
    String userId = "0af3a970-8793-49dd-843d-498f7c1602de";



    @BeforeClass
    public void preTests(){
        CreateBusiness createBusiness = new CreateBusiness();

        this.businesId=createBusiness.validBusiness();
        this.adressId=createBusiness.A_returnAdressId();
        this.staffId=createBusiness.B_returnStaff();
        this.serviceId=createBusiness.B_returnService();
        this.promoterId = createBusiness.returnPromoter();
        this.planId = createBusiness.returnPlan();

        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();
        this.customerId= getUserToken.GetUserId();

        this.token = createBusiness.B_returncreatedToken();
    }

    @Test
    public void A_createBooking(){
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
        Assert.assertEquals("0af3a970-8793-49dd-843d-498f7c1602de",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-07-25 17:00:00",booking.getDate());
    }

    @Test
    public void B_updateBooking(){
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
        Assert.assertEquals("0af3a970-8793-49dd-843d-498f7c1602de",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-07-25 14:00:00",booking.getDate());
    }

    @Test
    public void C_getBooking(){
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
        Assert.assertEquals("0af3a970-8793-49dd-843d-498f7c1602de",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-07-25 14:00:00",booking.getDate());
    }

    @Test
    public void D_getLockedTimeSlotsBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8085/api/v1.0/businesses/"+businesId+"/time-slots/?address_id="+adressId+"&service_id="+serviceId+"&staff_id="+staffId+"&date=2018-06-25").thenReturn().body();
        BookingListResponse bookingListResponse= new Gson().fromJson(response.asString(), BookingListResponse.class);
        Assert.assertEquals(false,response.asString().contains("2018-07-25 14:00:00"));
        Assert.assertEquals(false,response.asString().contains("2018-07-25 13:45:00"));
        Assert.assertEquals(false,response.asString().contains("2018-07-25 14:15:00"));
    }
    

    @Test
    public void F_cancelBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseUrl+bookingId+"/cancel").thenReturn().body();
        BookingsResponse bookingsResponse= new Gson().fromJson(response.asString(), BookingsResponse.class);
        Booking booking = bookingsResponse.data;
        Assert.assertEquals(businesId,booking.getBusiness_id());
        Assert.assertEquals(adressId,booking.getAddress_id());
        Assert.assertEquals(staffId,booking.getStaff_id());
        Assert.assertEquals(serviceId,booking.getService_id());
        Assert.assertEquals(20,booking.getDuration());
        Assert.assertEquals(700,booking.getAmount());
        Assert.assertEquals("0af3a970-8793-49dd-843d-498f7c1602de",booking.getCustomer_id());
        Assert.assertEquals(0,booking.getStatus());
        Assert.assertEquals(2,booking.getType());
        Assert.assertEquals("2018-07-25 14:00:00",booking.getDate());
    }

    @Test
    public void G_getFreeTimeSlotsBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8085/api/v1.0/businesses/"+businesId+"/time-slots/?address_id="+adressId+"&service_id="+serviceId+"&staff_id="+staffId+"&date=2018-07-25").thenReturn().body();
        BookingListResponse bookingListResponse= new Gson().fromJson(response.asString(), BookingListResponse.class);
        Assert.assertEquals(true,response.asString().contains("2018-07-25 14:00:00"));
        Assert.assertEquals(true,response.asString().contains("2018-07-25 13:45:00"));
        Assert.assertEquals(true,response.asString().contains("2018-07-25 14:15:00"));
    }

    @Test
    public void H_createCustomerBooking() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", usertoken)
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
        Assert.assertEquals("6559fafa-0b89-4779-b999-3ab961da149a",booking.getCustomer_id());
        Assert.assertEquals(2,booking.getStatus());
        Assert.assertEquals(1,booking.getType());
        Assert.assertEquals("2018-07-25 17:00:00",booking.getDate());
    }

    @Test
    public void I_approveCustomerBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseUrl+bookingId+"/").thenReturn().body();
    }

    @Test
    public void J_getLockedTimeSlotsBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8085/api/v1.0/businesses/"+businesId+"/time-slots/?address_id="+adressId+"&service_id="+serviceId+"&staff_id="+staffId+"&date=2018-07-25").thenReturn().body();
        BookingListResponse bookingListResponse= new Gson().fromJson(response.asString(), BookingListResponse.class);
        Assert.assertEquals(false,response.asString().contains("2018-07-25 17:00:00"));
        Assert.assertEquals(false,response.asString().contains("2018-07-25 16:45:00"));
        Assert.assertEquals(false,response.asString().contains("2018-07-25 17:15:00"));
    }

    @Test
    public void K_cancelBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseUrl+bookingId+"/cancel").thenReturn().body();
        BookingsResponse bookingsResponse= new Gson().fromJson(response.asString(), BookingsResponse.class);
        Booking booking = bookingsResponse.data;
        Assert.assertEquals(businesId,booking.getBusiness_id());
        Assert.assertEquals(adressId,booking.getAddress_id());
        Assert.assertEquals(staffId,booking.getStaff_id());
        Assert.assertEquals(serviceId,booking.getService_id());
        Assert.assertEquals(20,booking.getDuration());
        Assert.assertEquals(700,booking.getAmount());
        Assert.assertEquals("6559fafa-0b89-4779-b999-3ab961da149a",booking.getCustomer_id());
        Assert.assertEquals(0,booking.getStatus());
        Assert.assertEquals(1,booking.getType());
        Assert.assertEquals("2018-07-25 17:00:00",booking.getDate());
    }

    @Test
    public void L_getFreeTimeSlotsBooking(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8085/api/v1.0/businesses/"+businesId+"/time-slots/?address_id="+adressId+"&service_id="+serviceId+"&staff_id="+staffId+"&date=2018-07-25").thenReturn().body();
        BookingListResponse bookingListResponse= new Gson().fromJson(response.asString(), BookingListResponse.class);
        Assert.assertEquals(true,response.asString().contains("2018-07-25 14:00:00"));
        Assert.assertEquals(true,response.asString().contains("2018-07-25 13:45:00"));
        Assert.assertEquals(true,response.asString().contains("2018-07-25 14:15:00"));
    }

    @Test
    public void M_getCustomerBooking(){
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
        Assert.assertEquals("6559fafa-0b89-4779-b999-3ab961da149a",booking.getCustomer_id());
        Assert.assertEquals(0,booking.getStatus());
        Assert.assertEquals(1,booking.getType());
        Assert.assertEquals("2018-07-25 17:00:00",booking.getDate());
    }

    @AfterClass
    public void deletePreTest(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + businesId + "/").thenReturn().body();
        ResponseBody respons = given().contentType(ContentType.JSON)
            .header("Authorization", token)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when().delete("http://213.136.86.27:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();
         ResponseBody respon = given().contentType(ContentType.JSON)
            .header("Authorization", token)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when().delete("http://213.136.86.27:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
}
}
