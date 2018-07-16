package Customers;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RetutnCustomer {
    String baseURL="https://staging.eservia.com:8010/api/v0.0/PromoterCustomers";
    public String tokenResto;
    CustomersData customersData = new CustomersData();
    String id;
    public int business_id;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.tokenResto = getToken.GetFinallyToken();
    }


    public String ReturnCustomer(int business_id) {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", tokenResto)
                .body(customersData.createCustomer(business_id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        CustomersResponse customersResponse = new Gson().fromJson(response.asString(), CustomersResponse.class);
        Customers customers = customersResponse.data;
        this.id = customers.getId();
        return id;
    }
}
