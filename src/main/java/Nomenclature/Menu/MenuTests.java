package Nomenclature.Menu;

import Auth.GetToken;
import Customers.Customers;
import Customers.CustomersData;
import Customers.CustomersResponse;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;


    public class MenuTests {
        String token;
        MenuData menu = new MenuData();

        @BeforeClass
        public void getToken() {
            GetToken getToken = new GetToken();
            this.token = getToken.GetFinallyToken();
        }

        @Test
        public void A_ReloadMenu() {
            ResponseBody response = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().get("http://staging.eservia.com:8006/api/v0.0/Menu?AddressId=2").thenReturn().body();
            MenuResponse menuResponse = new Gson().fromJson(response.asString(),  MenuResponse.class);
            Assert.assertEquals("success",menuResponse.isDescription());
        }

        @Test
        public void B_GetNextMenu() {
            ResponseBody response = given().contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(menu.reloadAddressMenu())
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("http://staging.eservia.com:8008/api/v0.0/Menu/Reload").thenReturn().body();
            MenuResponse menuResponse = new Gson().fromJson(response.asString(),  MenuResponse.class);
            Assert.assertEquals("success",menuResponse.isDescription());
        }

    }
