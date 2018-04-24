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
import java.time.LocalTime;
import static com.jayway.restassured.RestAssured.given;

public class BookingTest {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/Bookings";
    String baseURLTables = "http://staging.eservia.com:8009/api/v0.0/Tables";
    String freeTableURL = "http://staging.eservia.com:8005/api/v0.0/Bookings/Admin/FreeTables?";
    String token;
    String code = LocalTime.now().toString();
    String capacity = "Capacity=10";
    String from = "From=2018-02-23T12:55:33.000";
    String to = "To=2018-04-27T19:55:33.000";
    String adressId = "AddressId=2";
    int TableId;
    int DepIds;
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
    //@Test
    public void B_bookingAdminFreeTables() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(freeTableURL+capacity+"&"+adressId+"&"+from+"&"+to).thenReturn().body();
        TableResponse tableResponse= new Gson().fromJson(response.asString(), TableResponse.class);
        Tables tables = tableResponse.data;
        Assert.assertEquals(1, tables.getStatusId());
        Assert.assertEquals(true, tables.isBookingAvailable());
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
        this.id = booking.getId();
        Assert.assertEquals(DepIds,booking.getDepartmentId());
        Assert.assertEquals(TableId,booking.getTableIds().get(0).intValue());
        Assert.assertEquals(3,booking.getPeopleCount());
        Assert.assertEquals("хочу живої музики без мертвих музикантів", booking.getRequestDescription());
<<<<<<< HEAD
        Assert.assertEquals("2018-06-24T10:55:33.000", booking.getBookingDateTime());
        Assert.assertEquals("2018-06-24T12:40:33.000", booking.getBookingEndTime());
=======
        Assert.assertEquals("2018-04-26T10:55:33.000", booking.getBookingDateTime());
        Assert.assertEquals("2018-04-26T12:40:33.000", booking.getBookingEndTime());
>>>>>>> v_holovko
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
        Assert.assertEquals(id, bookingUpdate.getId());
        Assert.assertEquals(DepIds, bookingUpdate.getDepartmentId());
        Assert.assertEquals(TableId,bookingUpdate.getTableIds().get(0).intValue());
        Assert.assertEquals(2, bookingUpdate.getPeopleCount());
        Assert.assertEquals("а сєводня в завтрашній дєнь", bookingUpdate.getRequestDescription());
<<<<<<< HEAD
        Assert.assertEquals("2018-06-24T11:55:33.000", bookingUpdate.getBookingDateTime());
        Assert.assertEquals("2018-06-24T14:40:33.000", bookingUpdate.getBookingEndTime());
=======
        Assert.assertEquals("2018-04-26T11:55:33.000", bookingUpdate.getBookingDateTime());
        Assert.assertEquals("2018-04-26T14:40:33.000", bookingUpdate.getBookingEndTime());
>>>>>>> v_holovko
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
        Assert.assertEquals(id, bookingIdGet.getId());
        Assert.assertEquals(DepIds, bookingIdGet.getDepartmentId());
        Assert.assertEquals(TableId, bookingIdGet.getTableIds().get(0).intValue());
        Assert.assertEquals(2, bookingIdGet.getPeopleCount());
        Assert.assertEquals("а сєводня в завтрашній дєнь", bookingIdGet.getRequestDescription());
<<<<<<< HEAD
        Assert.assertEquals("2018-06-24T11:55:33.000", bookingIdGet.getBookingDateTime());
        Assert.assertEquals("2018-06-24T14:40:33.000", bookingIdGet.getBookingEndTime());
=======
        Assert.assertEquals("2018-04-26T11:55:33.000", bookingIdGet.getBookingDateTime());
        Assert.assertEquals("2018-04-26T14:40:33.000", bookingIdGet.getBookingEndTime());
>>>>>>> v_holovko
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
        deleteTable.F_deleteTableId(TableId);
    }
}
