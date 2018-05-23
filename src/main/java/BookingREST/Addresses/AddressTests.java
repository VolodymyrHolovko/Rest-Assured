package BookingREST.Addresses;

import BookingREST.AuthBusiness.AuthBusinessTest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.response.ResponseBodyData;
import com.jayway.restassured.specification.RequestSpecification;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddressTests {

    private String token;
    private  String baseURL = "http://213.136.86.27:8083/api/v1.0/addresses/";
    int businessId = 1;
    int Ids;
    AddressData addressData = new AddressData();

    @BeforeClass
    public void getToken(){
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
    }


    @Test
    public void A_CreateAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addressData.CreateAddress(1))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        AddressResponse addressResponse = new Gson().fromJson(response.asString(), AddressResponse.class);
        Address address = addressResponse.data;
        this.Ids = address.getId();
        Assert.assertEquals(businessId,address.getBusiness_id());
        Assert.assertEquals("Київ",address.getCity());
        Assert.assertEquals("вулиця Миколи Грінченка",address.getStreet());
        Assert.assertEquals("28",address.getNumber());
        Assert.assertEquals("50.410133",address.getLat());
        Assert.assertEquals("30.521291700000006",address.getLng());
        Assert.assertEquals("+380971234567", address.getPhone());
        Assert.assertEquals("Біля чогосьтам",address.getMark());
        Assert.assertEquals("EkrQstGD0LvQuNGG0Y8g0JzQuNC60L7Qu9C4INCT0YDRltC90YfQtdC90LrQsCwgMjgsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA",address.getPlace_hash());
        Assert.assertEquals(1,address.getStatus());
        }


    @Test
    public void B_UpdateAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .body(addressData.UpdateAddress(1))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+Ids+"/").thenReturn().body();
        AddressResponse addressResponse = new Gson().fromJson(response.asString(),AddressResponse.class);
        Address address = addressResponse.data;
        this.Ids = address.getId();
        Assert.assertEquals(businessId,address.getBusiness_id());
        Assert.assertEquals("Київ",address.getCity());
        Assert.assertEquals("вулиця Вадима Гетьмана",address.getStreet());
        Assert.assertEquals("7",address.getNumber());
        Assert.assertEquals("50.4516007",address.getLat());
        Assert.assertEquals("30.444661699999983",address.getLng());
        Assert.assertEquals("+380977777777", address.getPhone());
        Assert.assertEquals("Біля чогосьіншого",address.getMark());
        Assert.assertEquals("EkfQstGD0LvQuNGG0Y8g0JLQsNC00LjQvNCwINCT0LXRgtGM0LzQsNC90LAsIDcsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA",address.getPlace_hash());
        Assert.assertEquals(1,address.getStatus());
        }

    @Test
    public void  C_CheckUpdatedAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+Ids+"/").thenReturn().body();
        AddressResponse addressResponse = new Gson().fromJson(response.asString(),AddressResponse.class);
        Address address = addressResponse.data;
        this.Ids = address.getId();
        Assert.assertEquals(businessId,address.getBusiness_id());
        Assert.assertEquals("Київ",address.getCity());
        Assert.assertEquals("вулиця Вадима Гетьмана",address.getStreet());
        Assert.assertEquals("7",address.getNumber());
        Assert.assertEquals("50.4516007",address.getLat());
        Assert.assertEquals("30.444661699999983",address.getLng());
        Assert.assertEquals("+380977777777", address.getPhone());
        Assert.assertEquals("Біля чогосьіншого",address.getMark());
        Assert.assertEquals("EkfQstGD0LvQuNGG0Y8g0JLQsNC00LjQvNCwINCT0LXRgtGM0LzQsNC90LAsIDcsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA",address.getPlace_hash());
        Assert.assertEquals(1,address.getStatus());
        }


    @Test
    public void D_DeactivateAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+Ids+"/deactivate/").thenReturn().body();
        AddressResponse addressResponse = new Gson().fromJson(response.asString(),AddressResponse.class);
        Address address = addressResponse.data;
        this.Ids = address.getId();
        Assert.assertEquals(0,address.getStatus());
        Assert.assertEquals("Київ",address.getCity());
        Assert.assertEquals("вулиця Вадима Гетьмана",address.getStreet());
        Assert.assertEquals("7",address.getNumber());
        Assert.assertEquals("50.4516007",address.getLat());
        Assert.assertEquals("30.444661699999983",address.getLng());
        Assert.assertEquals("+380977777777", address.getPhone());
        Assert.assertEquals("Біля чогосьіншого",address.getMark());
        Assert.assertEquals("EkfQstGD0LvQuNGG0Y8g0JLQsNC00LjQvNCwINCT0LXRgtGM0LzQsNC90LAsIDcsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA",address.getPlace_hash());
    }


    @Test
    public void E_ActivateAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+Ids+"/activate/").thenReturn().body();
        AddressResponse addressResponse = new Gson().fromJson(response.asString(),AddressResponse.class);
        Address address = addressResponse.data;
        this.Ids = address.getId();
        Assert.assertEquals(1,address.getStatus());
        Assert.assertEquals("Київ",address.getCity());
        Assert.assertEquals("вулиця Вадима Гетьмана",address.getStreet());
        Assert.assertEquals("7",address.getNumber());
        Assert.assertEquals("50.4516007",address.getLat());
        Assert.assertEquals("30.444661699999983",address.getLng());
        Assert.assertEquals("+380977777777", address.getPhone());
        Assert.assertEquals("Біля чогосьіншого",address.getMark());
        Assert.assertEquals("EkfQstGD0LvQuNGG0Y8g0JLQsNC00LjQvNCwINCT0LXRgtGM0LzQsNC90LAsIDcsINCa0LjRl9CyLCDQo9C60YDQsNGX0L3QsA",address.getPlace_hash());
        }

    @Test
    public void F_ActivateAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://213.136.86.27:8083/api/v1.0/businesses/1/addresses/").thenReturn().body();
    }


    @Test
    public void G_DeleteAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+Ids+"/").thenReturn().body();
                AddressResponse addressResponse = new Gson().fromJson(response.asString(),AddressResponse.class);
                Address address = addressResponse.data;

                ResponseBody responseGet = given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", token)
                        .filter(new RequestLoggingFilter())
                        .filter(new ResponseLoggingFilter())
                        .when().get(baseURL+Ids+"/").thenReturn().body();
                AddressResponse addressGetResponse = new Gson().fromJson(response.asString(),AddressResponse.class);
                Address addressGet = addressGetResponse.data;
                Assert.assertEquals(businessId, address.getBusiness_id());
                Assert.assertEquals(true, address.getDeleted_at().startsWith("2018"));
                }


    @Test
    public void G_GetAllAddresses(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void H_GetBusinessAddresses(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/addresses/");
        Assert.assertEquals(200,response.getStatusCode());
    }




}
