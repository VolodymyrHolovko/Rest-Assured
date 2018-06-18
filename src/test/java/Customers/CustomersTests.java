package Customers;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class CustomersTests {
    String baseURL="http://staging.eservia.com:8010/api/v0.0/PromoterCustomers";
    String token;
    CustomersData customersData = new CustomersData();
    String id;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }

    @Test
    public void A_CreateCustomer() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(customersData.createCustomer(1))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        CustomersResponse customersResponse = new Gson().fromJson(response.asString(),  CustomersResponse.class);
        Customers customers = customersResponse.data;
        this.id=customers.getId();
        Assert.assertEquals("Max",customers.getFirstName());
        Assert.assertEquals("Lutkovec",customers.getLastName());
        Assert.assertEquals("Vasylovych",customers.getMiddleName());
        Assert.assertEquals("media/201804/lxCWRpLL1Vm5Uz5y.jpg",customers.getPhotoPath());
        Assert.assertEquals("+380679296215",customers.getPhoneNumber());
    }

    @Test
    public void B_UpdateCustomer() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(customersData.updateCustomer(id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL).thenReturn().body();
        CustomersResponse customersResponse = new Gson().fromJson(response.asString(),  CustomersResponse.class);
        Customers customers = customersResponse.data;
        Assert.assertEquals("Max1",customers.getFirstName());
        Assert.assertEquals("Lutkovec1",customers.getLastName());
        Assert.assertEquals("Vasylovych1",customers.getMiddleName());
        Assert.assertEquals("lutkovec1@gmail.com",customers.getEmail());

    }

    @Test
    public void C_GetCustomer() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/ByIds?ids="+id+"&businessId=1").thenReturn().body();
        CustomerObjectResponse customersResponse = new Gson().fromJson(response.asString(),  CustomerObjectResponse.class);
        CustomersObject customersObject = customersResponse.getData().get(0);
        Assert.assertEquals("Lutkovec1",customersObject.getLastName());
        Assert.assertEquals("Vasylovych1",customersObject.getMiddleName());
        Assert.assertEquals("+380679296215",customersObject.getPhoneNumber());
        Assert.assertEquals("lutkovec1@gmail.com",customersObject.getEmail());
    }

    @Test
    public void D_GetCustomers() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"?pageIndex=0&pageSize=10").thenReturn().body();
        CustomerResponseArray customersResponse = new Gson().fromJson(response.asString(),  CustomerResponseArray.class);
        Assert.assertEquals(true,customersResponse.isSuccess());
    }

    @Test
    public void E_blockCustomer() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(customersData.blockCustomer(id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL).thenReturn().body();

        ResponseBody response1 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+id).thenReturn().body();
    }


    @Test
    public void G_DeleteCustomer() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+"/"+id).thenReturn().body();

        ResponseBody response1 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/ByIds?ids="+id+"&businessId=1").thenReturn().body();
        CustomerObjectResponse customerObjectResponse= new Gson().fromJson(response1.asString(),  CustomerObjectResponse.class);
        Assert.assertEquals(true, customerObjectResponse.getData().isEmpty());


    }
}
