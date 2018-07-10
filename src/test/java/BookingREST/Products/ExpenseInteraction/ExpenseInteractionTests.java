package BookingREST.Products.ExpenseInteraction;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.Products;
import BookingREST.Products.ProductsResponse;
import BookingREST.Products.ReturnProduct;
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

public class ExpenseInteractionTests {
    String token;
    int id;
    int business_id;
    int product_id;
    String productQuery = "?product_id=";
    String baseURL = "http://staging.eservia.com:8086/api/v1.0/expense-interaction-strategies/";
    String baseURLBusiness = "http://staging.eservia.com:8086/api/v1.0/businesses/";
    String baseURLPRoduct = "http://staging.eservia.com:8086/api/v1.0/products/";
    ExpenseInteractionData expenseInteractionData = new ExpenseInteractionData();

    @BeforeClass
    public void beforeActions() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();

        ReturnProduct getProduct = new ReturnProduct();
        this.product_id = getProduct.ReturnProduct(business_id);
    }
    @Test
    public void A_addExpenseInteraction() {
        ExpenseInteraction addExpenses = expenseInteractionData.addExpenseInteraction(business_id, product_id);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addExpenses)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ExpenseInteractionResponse expenseInteractionResponse = new Gson().fromJson(response.asString(), ExpenseInteractionResponse.class);
        ExpenseInteraction addExpense = expenseInteractionResponse.data;
        this.id = addExpense.getId();
        Assert.assertEquals(id, addExpense.getId());
        Assert.assertEquals(business_id, addExpense.getBusiness_id());
        Assert.assertEquals(product_id, addExpense.getProduct_id());
    }
    @Test
    public void B_getExpenseInteractionById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+id+"/").thenReturn().body();
        ExpenseInteractionResponse expenseInteractionResponse = new Gson().fromJson(response.asString(),ExpenseInteractionResponse.class);
        ExpenseInteraction getById = expenseInteractionResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(product_id, getById.getProduct_id());
    }
    @Test
    public void C_getExpenseInteractionByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+productQuery+product_id+"&sort=-id").thenReturn().body();
        ExpenseInteractionResponseArray expenseInteractionResponseArray = new Gson().fromJson(response.asString(),ExpenseInteractionResponseArray.class);
        ExpenseInteraction getByQUery = expenseInteractionResponseArray.data.get(0);
        Assert.assertEquals(product_id,getByQUery.getProduct_id());
    }
    @Test
    public void D_getExpenseInteractionByBusiness() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLBusiness+business_id+"/expense-interaction-strategies/").thenReturn().body();
        ExpenseInteractionResponseArray expenseInteractionResponseArray = new Gson().fromJson(response.asString(),ExpenseInteractionResponseArray.class);
        ExpenseInteraction getByBusiness = expenseInteractionResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
    }
    @Test
    public void E_deleteExpenseInteraction() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+id+"/").thenReturn().body();
        ExpenseInteractionResponse expenseInteractionResponse = new Gson().fromJson(response.asString(), ExpenseInteractionResponse.class);
        ExpenseInteraction deleteExpenseInteraction = expenseInteractionResponse.data;
        Assert.assertEquals(id, deleteExpenseInteraction.getId());
        Assert.assertEquals(true, deleteExpenseInteraction.getDeleted_at().contains("2018"));
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

