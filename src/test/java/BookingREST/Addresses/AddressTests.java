package BookingREST.Addresses;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddressTests {

    private String token;
    private  String baseURL = "https://staging.eservia.com:8083/api/v1.0/addresses/";
    int businessId ;
    int Ids;
    AddressData addressData = new AddressData();

    @BeforeClass
    public void getToken(){
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
        CreateBusiness createBusiness = new CreateBusiness();
        this.businessId = createBusiness.validBusiness();
    }

    @Test
    public void A_CreateAddress(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addressData.CreateAddress(businessId))
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
                .body(addressData.UpdateAddress(businessId))
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
                .when().get("https://staging.eservia.com:8083/api/v1.0/businesses/1/addresses/").thenReturn().body();
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
        RequestSpecification httpsRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpsRequest.get(baseURL);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void H_GetBusinessAddresses(){
        RequestSpecification httpsRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpsRequest.get("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/addresses/");
        Assert.assertEquals(200,response.getStatusCode());
    }

}
