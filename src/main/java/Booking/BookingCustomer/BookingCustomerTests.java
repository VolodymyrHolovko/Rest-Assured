package Booking.BookingCustomer;

import Auth.GetToken;
import Auth.Users.GetUserToken;
import Booking.BookingTest;
import Departments.DepartmentTest;
import Departments.Tables.TableResponse;
import Departments.Tables.Tables;
import Departments.Tables.TablesData;
import Departments.Tables.TablesTest;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class BookingCustomerTests {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/Bookings";
    String baseURLTables = "http://staging.eservia.com:8009/api/v0.0/Tables";
    String code =LocalTime.now().toString();
    String tokenCust = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJiODA5OTNkZi0zMmZhLTRhYTAtYmVlZS0xMmYzZGExYmVjMjgiLCJ1bmlxdWVfbmFtZSI6IiszODA5MzU3MzI2NzMiLCJpYXQiOjE1MjUzNTQ0NjEsImdpdmVuX25hbWUiOiJWb2xvZHlteXIiLCJmYW1pbHlfbmFtZSI6IkhvbG92a28iLCJlbWFpbCI6InZvbG9keW15ci5ob2xvdmtvQG1hZ25pc2UuY29tIiwiZ2VuZGVyIjoibWFsZSIsIm5iZiI6MTUyNTM1NDQ2MSwiZXhwIjoxNTI1MzU2MjYxLCJpc3MiOiJlc2VydmlhIiwiYXVkIjoiZXNlcnZpYSJ9.XL1DoS6fCc9hu-MQlkQeq6Ei-MN9c3HAskJzTluSt8xenfCzf5qXsQwII6upIFc-YV2hzWBws0Td8aiBlb4Uw34WTOJ0CefljDOZV4det6fZnfyH-fdwzStjHiNu5B__pL781qxBUHWkkN3tED9xlmJL2mTie0uVP0madLNvWshVgSc0Hmt9XftwwoJuGmk4cpR_17xoYOnYkU9nbpwcfkhlmvNRtJfzPo-XT4Tx5ALCAScfeU5U3w15_xzDjvTyXe1hKgJA_r3U4asi12rVVcIL3tWHg2hgIjPJrv0M-wdIpL7ZVXi_oLzepDKdvbYPx9Fd-gl-v1NAthDKAObkYg";
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
        BookingCustomer bookingCustomer =bookingResponse.data;
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
    @AfterClass
    public void deleteTable() {
        TablesTest deleteTable = new TablesTest();
        deleteTable.token = token;
        deleteTable.F_deleteTableId();
    }


}
