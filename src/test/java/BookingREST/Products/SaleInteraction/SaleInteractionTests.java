package BookingREST.Products.SaleInteraction;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.Products;
import BookingREST.Products.ProductsResponse;
import BookingREST.Products.ReturnProduct;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class SaleInteractionTests {
    String token;
    int id;
    int business_id;
    int product_id;
    Faker faker = new Faker();
    String currency = "UAH";
    String currency2 = "USD";
    String saleQuery = "?product_id=";
    int cost = faker.number().randomDigitNotZero();
    int cost2 = faker.number().randomDigitNotZero();
    String baseURL = "http://staging.eservia.com:8086/api/v1.0/sale-interaction-strategies/";
    String baseURLByBusiness = "http://staging.eservia.com:8086/api/v1.0/businesses/";
    String baseURLBusiness = "http://staging.eservia.com:8086/api/v1.0/businesses/";
    String baseURLPRoduct = "http://staging.eservia.com:8086/api/v1.0/products/";
    SaleInteractionData saleInteractionData = new SaleInteractionData();

    @BeforeClass
    public void beforeActions() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();

        ReturnProduct getProduct = new ReturnProduct();
        this.product_id = getProduct.ReturnInactiveProduct(business_id);
    }
    @Test
    public void A_addSaleInteraction() {
        SaleInteraction addSales = saleInteractionData.addNewSaleInteraction(business_id, product_id, currency, cost);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSales)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        SaleInteractionResponse saleInteractionResponse = new Gson().fromJson(response.asString(), SaleInteractionResponse.class);
        SaleInteraction addNewSaleInter = saleInteractionResponse.data;
        this.id = addNewSaleInter.getId();
        Assert.assertEquals(id, addNewSaleInter.getId());
        Assert.assertEquals(business_id, addNewSaleInter.getBusiness_id());
        Assert.assertEquals(product_id, addNewSaleInter.getProduct_id());
        Assert.assertEquals(currency, addNewSaleInter.getCurrency());
        Assert.assertEquals(cost, addNewSaleInter.getCost());
    }
    @Test
    public void B_getSaleInteractionById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+id+"/").thenReturn().body();
        SaleInteractionResponse saleInteractionResponse = new Gson().fromJson(response.asString(), SaleInteractionResponse.class);
        SaleInteraction getById = saleInteractionResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(product_id, getById.getProduct_id());
        Assert.assertEquals(currency, getById.getCurrency());
        Assert.assertEquals(cost, getById.getCost());
    }
    @Test
    public void C_updateSaleInteraction() {
        SaleInteraction updateSales = saleInteractionData.updateSaleInteraction(currency2, cost2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateSales)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+id+"/").thenReturn().body();
        SaleInteractionResponse saleInteractionResponse = new Gson().fromJson(response.asString(), SaleInteractionResponse.class);
        SaleInteraction updateSaleInter = saleInteractionResponse.data;
        Assert.assertEquals(id, updateSaleInter.getId());
        Assert.assertEquals(currency2, updateSaleInter.getCurrency());
        Assert.assertEquals(cost2, updateSaleInter.getCost());
        Assert.assertEquals(true, updateSaleInter.getUpdated_at().contains("2018"));
    }
    @Test
    public void D_getSaleInteractionByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+saleQuery+product_id+"&sort=-id").thenReturn().body();
        SaleInteractionResponseArray saleInteractionResponseArray = new Gson().fromJson(response.asString(), SaleInteractionResponseArray.class);
        SaleInteraction getByQuery = saleInteractionResponseArray.data.get(0);
        Assert.assertEquals(product_id, getByQuery.getProduct_id());
    }
    @Test
    public void E_getSaleInteractionByBusiness() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLByBusiness+business_id+"/sale-interaction-strategies/").thenReturn().body();
        SaleInteractionResponseArray saleInteractionResponseArray = new Gson().fromJson(response.asString(), SaleInteractionResponseArray.class);
        SaleInteraction getByBusiness = saleInteractionResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
    }
    @Test
    public void F_deleteSaleInteraction() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+id).thenReturn().body();
        SaleInteractionResponse saleInteractionResponse = new Gson().fromJson(response.asString(), SaleInteractionResponse.class);
        SaleInteraction deleteSaleInteraction = saleInteractionResponse.data;
        Assert.assertEquals(id, deleteSaleInteraction.getId());
        Assert.assertEquals(true, deleteSaleInteraction.getDeleted_at().contains("2018"));
    }
    @AfterClass
    public void deleteBefore() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + business_id + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        this.business_id = businesses.getId();

        ResponseBody response3 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLPRoduct + product_id + "/").thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response3.asString(), ProductsResponse.class);
        Products deleteProd = productsResponse.data;
        Assert.assertEquals(product_id, deleteProd.getId());
    }
}