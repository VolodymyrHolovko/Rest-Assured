package Booking.BookingCustomer;

import Auth.GetToken;
import Auth.Users.GetUserToken;
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

public class BookingCustomerTests {
    String baseURI = "https://staging.eservia.com:8005/api/v0.0/Bookings";
    String baseURLTables = "https://staging.eservia.com:8009/api/v0.0/Tables";
    String code =LocalTime.now().toString();
    String tokenCust;
    String token;
    String dateTime;
    String endTime;
    public int id;
    int TableId;
    int DepIds;
    BookingCustomerData bookingCustomerData = new BookingCustomerData();
    TablesData tablesData = new TablesData();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        GetUserToken getUserToken = new  GetUserToken();
        this.tokenCust = getUserToken.GetUserToken();
        DepartmentTest getDepartment = new DepartmentTest();
        getDepartment.token = token;
        this.DepIds = getDepartment.getId();
    }
    @Test
    public void A_createTable() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(tablesData.createTable(DepIds, code))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLTables).thenReturn().body();
        TableResponse tableResponse = new Gson().fromJson(response.asString(), TableResponse.class);
        Tables tables = tableResponse.data;
        this.TableId = tables.getId();
        //for an ability to have a free table for booking at same time
    }
    @Test
    public void B_addBookingCustomer() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", tokenCust)
                .body(bookingCustomerData.addBookingCustomer(TableId, DepIds))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI+ "/" +"Customer").thenReturn().body();
        BookingCustomerResponse bookingResponse = new Gson().fromJson(response.asString(), BookingCustomerResponse.class);
        BookingCustomer bookingCustomer = bookingResponse.data;
        System.out.println(response.asString());
        this.dateTime = bookingCustomer.getBookingDateTime();
        this.endTime = bookingCustomer.getBookingEndTime();
        this.id = bookingCustomer.getId();
        Assert.assertEquals(2, bookingCustomer.getAddressId());
        Assert.assertEquals(DepIds, bookingCustomer.getDepartmentId());
        //Assert.assertEquals(TableId, bookingCustomer.getTables().get(0).intValue());
        Assert.assertEquals(1, bookingCustomer.getPeopleCount());
        Assert.assertEquals(1, bookingCustomer.getStatusId());
        Assert.assertEquals(id, bookingCustomer.getId());
        Assert.assertEquals(dateTime, bookingCustomer.getBookingDateTime());
        Assert.assertEquals(endTime, bookingCustomer.getBookingEndTime());
        Assert.assertEquals("це мій букінг кастомера", bookingCustomer.getRequestDescription());
        Assert.assertEquals(false, bookingCustomer.isArchived());

    }
    @Test
    public void C_updateBookingCustomer() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", tokenCust)
                .body(bookingCustomerData.updateBookingCustomer(TableId, DepIds, id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI+ "/" +"Customer").thenReturn().body();
        BookingCustomerResponse bookingResponse = new Gson().fromJson(response.asString(), BookingCustomerResponse.class);
        BookingCustomer bookingCustomerUpdate = bookingResponse.data;
        System.out.println(response.asString());
        this.dateTime = bookingCustomerUpdate.getBookingDateTime();
        this.endTime = bookingCustomerUpdate.getBookingEndTime();
        this.id = bookingCustomerUpdate.getId();
        Assert.assertEquals(2, bookingCustomerUpdate.getAddressId());
        Assert.assertEquals(DepIds, bookingCustomerUpdate.getDepartmentId());
        //Assert.assertEquals(TableId, bookingCustomer.getTables().get(0).intValue());
        Assert.assertEquals(2, bookingCustomerUpdate.getPeopleCount());
        Assert.assertEquals(1, bookingCustomerUpdate.getStatusId());
        Assert.assertEquals(id, bookingCustomerUpdate.getId());
        Assert.assertEquals(dateTime, bookingCustomerUpdate.getBookingDateTime());
        Assert.assertEquals(endTime, bookingCustomerUpdate.getBookingEndTime());
        Assert.assertEquals("це мій букінг кастомера, який я редагую", bookingCustomerUpdate.getRequestDescription());
        Assert.assertEquals(false, bookingCustomerUpdate.isArchived());
        Assert.assertEquals(false, bookingCustomerUpdate.isPreviousBookingAvailable());
    }
    @Test
    public void D_getBookingIdCustomer() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", tokenCust)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+ "/" + id + "/" +"Customer").thenReturn().body();
        BookingCustomerResponse bookingCustomerResponse = new Gson().fromJson(response.asString(), BookingCustomerResponse.class);
        BookingCustomer getBookingCustomer = bookingCustomerResponse.data;
        System.out.println(response.asString());
        this.dateTime = getBookingCustomer.getBookingDateTime();
        this.endTime = getBookingCustomer.getBookingEndTime();
        this.id = getBookingCustomer.getId();
        Assert.assertEquals(2, getBookingCustomer.getAddressId());
        Assert.assertEquals(DepIds, getBookingCustomer.getDepartmentId());
        //Assert.assertEquals(TableId, bookingCustomer.getTables().get(0).intValue());
        Assert.assertEquals(2, getBookingCustomer.getPeopleCount());
        Assert.assertEquals(1, getBookingCustomer.getStatusId());
        Assert.assertEquals(id, getBookingCustomer.getId());
        Assert.assertEquals(dateTime, getBookingCustomer.getBookingDateTime());
        Assert.assertEquals(endTime, getBookingCustomer.getBookingEndTime());
        Assert.assertEquals("це мій букінг кастомера, який я редагую", getBookingCustomer.getRequestDescription());
        Assert.assertEquals(false, getBookingCustomer.isArchived());
        Assert.assertEquals(false, getBookingCustomer.isPreviousBookingAvailable());
    }
    @Test
    public  void E_bookingCustomerReject() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", tokenCust)
                .body(bookingCustomerData.bookingCustomerReject(id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI+ "/" + "Customer"+ "/" +"Reject").thenReturn().body();
        BookingCustomerResponse bookingCustomerResponse = new Gson().fromJson(response.asString(), BookingCustomerResponse.class);
        BookingCustomer bookingReject = bookingCustomerResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals(3, bookingReject.getStatusId());
        Assert.assertEquals("я передумав", bookingReject.getResponseDescription());
    }
    @AfterClass
    public void deleteTable() {
        TablesTest deleteTable = new TablesTest();
        deleteTable.token = token;
        deleteTable.F_deleteTableId();
    }
}
