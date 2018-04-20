package Booking;

import Auth.GetToken;
import Departments.DepartmentTest;
import Departments.Tables.TableResponse;
import Departments.Tables.Tables;
import Departments.Tables.TablesData;
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
    String baseURLTables = "http://staging.eservia.com:8009/api/v0.0/Tables";
    String token;
    String code = LocalTime.now().toString();
    int TableId;
    int DEpIds;
    public int id;
    BookingData bookingData = new BookingData();
    TablesData tablesData = new TablesData();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        DepartmentTest getDepartment = new DepartmentTest();
        getDepartment.token = token;
        this.DEpIds = getDepartment.getId();
    }
    @Test
    public void A_createTable(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(tablesData.createTable(DEpIds, code))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLTables).thenReturn().body();
        TableResponse tableResponse= new Gson().fromJson(response.asString(),  TableResponse.class);
        Tables tables = tableResponse.data;
        this.TableId = tables.getId();
        //for an ability to have a free table for booking at same time
    }
    @Test
    public void B_addBookingAdmin() {
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
        Assert.assertEquals(DEpIds,booking.getDepartmentId());
        Assert.assertEquals(TableId,booking.getTableIds().get(0).intValue());
        Assert.assertEquals(3,booking.getPeopleCount());
        Assert.assertEquals("хочу живої музики без мертвих музикантів", booking.getRequestDescription());
        Assert.assertEquals("2018-04-24T10:55:33.000", booking.getBookingDateTime());
        Assert.assertEquals("2018-04-24T12:40:33.000", booking.getBookingEndTime());
        Assert.assertEquals(2, booking.getAddressId());
    }
    @Test
    public void C_updateBookingAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingData.updateBookingAdmin(TableId, DEpIds, id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI+ "/" + "Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking bookingUpdate = bookingResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals(id, bookingUpdate.getId());
        Assert.assertEquals(DEpIds, bookingUpdate.getDepartmentId());
        Assert.assertEquals(TableId,bookingUpdate.getTableIds().get(0).intValue());
        Assert.assertEquals(2, bookingUpdate.getPeopleCount());
        Assert.assertEquals("а сєводня в завтрашній дєнь", bookingUpdate.getRequestDescription());
        Assert.assertEquals("2018-04-25T11:55:33.000", bookingUpdate.getBookingDateTime());
        Assert.assertEquals("2018-04-25T14:40:33.000", bookingUpdate.getBookingEndTime());
        Assert.assertEquals(2, bookingUpdate.getAddressId());
        Assert.assertEquals(false, bookingUpdate.isPreviousBookingAvailable());
    }
    @Test
    public void D_getBookingIdAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+ "/" +id + "/" + "Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking bookingIdGet = bookingResponse.data;
        System.out.println(response);
        Assert.assertEquals(id, bookingIdGet.getId());
        Assert.assertEquals(DEpIds, bookingIdGet.getDepartmentId());
        Assert.assertEquals(TableId, bookingIdGet.getTableIds().get(0).intValue());
        Assert.assertEquals(2, bookingIdGet.getPeopleCount());
        Assert.assertEquals("а сєводня в завтрашній дєнь", bookingIdGet.getRequestDescription());
        Assert.assertEquals("2018-04-25T11:55:33.000", bookingIdGet.getBookingDateTime());
        Assert.assertEquals("2018-04-25T14:40:33.000", bookingIdGet.getBookingEndTime());
        Assert.assertEquals(2, bookingIdGet.getAddressId());
        Assert.assertEquals(false, bookingIdGet.isPreviousBookingAvailable());
    }
}
