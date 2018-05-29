package BookingREST.Contact;

import BookingREST.AuthBusiness.AuthBusinessTest;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.ResponseBodyData;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;


public class ContactTests {
    private String token;
    private String baseURL = "http://213.136.86.27:8083/api/v1.0/contacts/";
    ContactData contactData = new ContactData();


    @BeforeClass
    public void getToken() {
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
    }

    @Test
    public void A_SendContact(){
        RequestSpecification httpRequest = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body(contactData.ContactLetter())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.post(baseURL);
        Assert.assertEquals(200,response.getStatusCode());
    }









}
