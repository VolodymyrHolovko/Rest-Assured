package Booking;

import Auth.GetToken;
import Departments.DepartmentTest;
import Departments.Tables.TableResponse;
import Departments.Tables.Tables;
import Departments.Tables.TablesData;
import Departments.Tables.TablesTest;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.jayway.restassured.RestAssured.given;

public class BookingTest {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/Bookings";
    String baseURLTables = "http://staging.eservia.com:8009/api/v0.0/Tables";
    String token;
    String code = LocalTime.now().toString();
    int TableId;
    int DepIds;
    String dateTime;
    String endTime;
    public int id;
    BookingData bookingData = new BookingData();
    TablesData tablesData = new TablesData();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        DepartmentTest getDepartment = new DepartmentTest();
        getDepartment.token = token;
        this.DepIds = getDepartment.getId();
    }
    @Test
    public void A_createTable(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(tablesData.createTable(DepIds, code))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLTables).thenReturn().body();
        TableResponse tableResponse= new Gson().fromJson(response.asString(),  TableResponse.class);
        Tables tables = tableResponse.data;
        this.TableId = tables.getId();
        //for an ability to have a free table for booking at same time
    }
    @Test
    public void C_addBookingAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(bookingData.addBookingAdmin(TableId, DepIds))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI+ "/" + "Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking booking = bookingResponse.data;
        System.out.println(response.asString());
        this.dateTime = booking.getBookingDateTime();
        this.endTime = booking.getBookingEndTime();
        this.id = booking.getId();
        Assert.assertEquals(DepIds,booking.getDepartmentId());
        Assert.assertEquals(TableId,booking.getTableIds().get(0).intValue());
        Assert.assertEquals(2,booking.getPeopleCount());
        Assert.assertEquals("хочу живої музики без мертвих музикантів", booking.getRequestDescription());
        Assert.assertEquals(dateTime, booking.getBookingDateTime());
        Assert.assertEquals(endTime, booking.getBookingEndTime());
        Assert.assertEquals(2, booking.getAddressId());
    }
    @Test
    public void D_updateBookingAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingData.updateBookingAdmin(TableId, DepIds, id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI+ "/" + "Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking bookingUpdate = bookingResponse.data;
        System.out.println(response.asString());
        this.dateTime = bookingUpdate.getBookingDateTime();
        this.endTime = bookingUpdate.getBookingEndTime();
        Assert.assertEquals(id, bookingUpdate.getId());
        Assert.assertEquals(DepIds, bookingUpdate.getDepartmentId());
        Assert.assertEquals(TableId,bookingUpdate.getTableIds().get(0).intValue());
        Assert.assertEquals(2, bookingUpdate.getPeopleCount());
        Assert.assertEquals("а сєводня в завтрашній дєнь", bookingUpdate.getRequestDescription());
        Assert.assertEquals(dateTime, bookingUpdate.getBookingDateTime());
        Assert.assertEquals(endTime, bookingUpdate.getBookingEndTime());

        Assert.assertEquals(2, bookingUpdate.getAddressId());
        Assert.assertEquals(false, bookingUpdate.isPreviousBookingAvailable());
    }
    @Test
    public void E_getBookingIdAdmin() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+ "/" +id + "/" + "Admin").thenReturn().body();
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking bookingIdGet = bookingResponse.data;
        System.out.println(response);
        this.dateTime = bookingIdGet.getBookingDateTime();
        this.endTime = bookingIdGet.getBookingEndTime();
        Assert.assertEquals(id, bookingIdGet.getId());
        Assert.assertEquals(DepIds, bookingIdGet.getDepartmentId());
        Assert.assertEquals(TableId, bookingIdGet.getTableIds().get(0).intValue());
        Assert.assertEquals(2, bookingIdGet.getPeopleCount());
        Assert.assertEquals("а сєводня в завтрашній дєнь", bookingIdGet.getRequestDescription());
        Assert.assertEquals(dateTime, bookingIdGet.getBookingDateTime());
        Assert.assertEquals(endTime, bookingIdGet.getBookingEndTime());
        Assert.assertEquals(2, bookingIdGet.getAddressId());
        Assert.assertEquals(false, bookingIdGet.isPreviousBookingAvailable());
    }
    @Test
    public  void F_bookingAdminReject() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingData.bookingAdminReject(id, DepIds))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI+ "/"+ "Admin" + "/" +"Reject");
        BookingResponse bookingResponse = new Gson().fromJson(response.asString(), BookingResponse.class);
        Booking bookingReject = bookingResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals(3, bookingReject.getStatusId());
        Assert.assertEquals("Ваше замовлення було відхилено адміністратором", bookingReject.getResponseDescription());
    }
    @AfterClass
    public void deleteTable() {
        TablesTest deleteTable = new TablesTest();
        deleteTable.token = token;
        deleteTable.F_deleteTableId();
    }
}
