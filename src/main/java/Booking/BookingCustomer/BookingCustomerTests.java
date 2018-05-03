package Booking.BookingCustomer;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class BookingCustomerTests {
    String baseURI = "http://staging.eservia.com:8005/api/v0.0/Bookings";
    String baseURLTables = "http://staging.eservia.com:8009/api/v0.0/Tables";
    String code = LocalTime.now().toString();
    String tokenCust;
    public String token;
    int TableId;
    int DepIds;
    BookingCustomerData bookingCustomerData = new BookingCustomerData();
    TablesData tablesData = new TablesData();

    @BeforeClass
    public void getToken() {
        GetUserToken getUserToken = new GetUserToken();
        this.tokenCust = getUserToken.GetUserToken();
        DepartmentTest getDepartment = new DepartmentTest();
        getDepartment.token = token;
        this.DepIds = getDepartment.getId();
        TablesTest tablesTest = new TablesTest();
        tablesTest.token = token;
        this.TableId = tablesTest.D_getTableId(TableId);
    }
   // @Test
    public void addBookingCustomer() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(bookingCustomerData.addBookingCustomer(TableId, DepIds))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI+ "/" +"Customer").thenReturn().body();
        BookingCustomerResponse bookingResponse = new Gson().fromJson(response.asString(), BookingCustomerResponse.class);
        BookingCustomer bookingCustomer =bookingResponse.data;
        System.out.println(response);
    }


    }
