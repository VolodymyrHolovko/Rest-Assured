package BookingREST.Expense;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.Products;
import BookingREST.Products.ProductsResponse;
import BookingREST.Products.ReturnProduct;
import BookingREST.Stock.Stock;
import BookingREST.Stock.StockResponseArray;
import BookingREST.Suppliers.ReturnSupplier;
import BookingREST.Suppliers.Suppliers;
import BookingREST.Suppliers.SuppliersResponse;
import BookingREST.Supply.Supply;
import BookingREST.Supply.SupplyData;
import BookingREST.Supply.SupplyResponse;
import BookingREST.Warehouse.ReturnWarehouse;
import BookingREST.Warehouse.Warehouse;
import BookingREST.Warehouse.WarehouseResponse;
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

public class ExpenseTests {
    String token;
    int id;
    String baseURL = "https://staging.eservia.com:8086/api/v1.0/expenses/";
    String baseURLStock = "https://staging.eservia.com:8086/api/v1.0/stocks/";
    String baseURLSupply = "https://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLSupplier = "https://staging.eservia.com:8086/api/v1.0/suppliers/";
    String baseURLPRoduct = "https://staging.eservia.com:8086/api/v1.0/products/";
    String baseURLWarehouse = "https://staging.eservia.com:8086/api/v1.0/warehouses/";
    String stockQUery = "?warehouse_id=";
    String productQuery = "?product_id=";
    String currency = "UAH";
    Faker faker = new Faker();
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
    String comment2 = faker.gameOfThrones().city();
    public int business_id;
    int cost = faker.number().randomDigit();
    int warehouse_id;
    int product_id;
    int address_id;
    int responsible_id;
    int supplier_id;
    int stock_id;
    int supply_id;
    ExpenseData expenseData = new ExpenseData();
    SupplyData supplyData = new SupplyData();

    @BeforeClass
    public void beforeActions() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();

        ReturnSupplier getSupplier = new ReturnSupplier();
        this.supplier_id = getSupplier.ReturnSupplier(business_id);

        ReturnProduct getProduct = new ReturnProduct();
        this.product_id = getProduct.ReturnProduct(business_id);

        ReturnWarehouse getWarehouse = new ReturnWarehouse();
        this.warehouse_id = getWarehouse.ReturnWarehouse(business_id, address_id, responsible_id);

        Supply addSupply = supplyData.addNewSupply(business_id, supplier_id, warehouse_id, product_id, count, currency, cost, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSupply)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLSupply).thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response.asString(), SupplyResponse.class);
        Supply addSupp = supplyResponse.data;
        this.supply_id = addSupp.getId();

        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLStock + stockQUery + warehouse_id + "&sort=-id").thenReturn().body();
        StockResponseArray stockResponse = new Gson().fromJson(response2.asString(), StockResponseArray.class);
        Stock getByQuery = stockResponse.data.get(0);
        this.stock_id = getByQuery.getId();
    }

    @Test
    public void A_addExpense() {
        Expense addExpense = expenseData.addNewExpense(business_id, warehouse_id, stock_id, product_id, count, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addExpense)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ExpenseResponse expenseResponse = new Gson().fromJson(response.asString(), ExpenseResponse.class);
        Expense addExp = expenseResponse.data;
        this.id = addExp.getId();
        Assert.assertEquals(id, addExp.getId());
        Assert.assertEquals(business_id, addExp.getBusiness_id());
        Assert.assertEquals(warehouse_id, addExp.getWarehouse_id());
        Assert.assertEquals(stock_id, addExp.getStock_id());
        Assert.assertEquals(product_id, addExp.getProduct_id());
        Assert.assertEquals(count, addExp.getCount());
        Assert.assertEquals(comment,addExp.getComment());
    }

    @Test
    public void B_getExpenseById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id +"/").thenReturn().body();
        ExpenseResponse expenseResponse = new Gson().fromJson(response.asString(), ExpenseResponse.class);
        Expense getByID = expenseResponse.data;
        Assert.assertEquals(id ,getByID.getId());
        Assert.assertEquals(business_id, getByID.getBusiness_id());
        Assert.assertEquals(warehouse_id, getByID.getWarehouse_id());
        Assert.assertEquals(stock_id, getByID.getStock_id());
        Assert.assertEquals(product_id, getByID.getProduct_id());
        Assert.assertEquals(count, getByID.getCount());
        Assert.assertEquals(comment, getByID.getComment());
    }

    @Test
    public void C_updateExpense() {
        Expense updateExpense = expenseData.updateExpense(comment2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateExpense)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        ExpenseResponse expenseResponse = new Gson().fromJson(response.asString(), ExpenseResponse.class);
        Expense updateExpenses = expenseResponse.data;
        Assert.assertEquals(id, updateExpenses.getId());
        Assert.assertEquals(comment2, updateExpenses.getComment());
        Assert.assertEquals(true, updateExpenses.getUpdated_at().contains("2018"));
    }

    @Test
    public void D_getExpenseByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+productQuery+product_id+"&sort=-id").thenReturn().body();
        ExpenseResponseArray expenseResponseArray = new Gson().fromJson(response.asString(), ExpenseResponseArray.class);
        Expense getByQuery = expenseResponseArray.data.get(0);
        Assert.assertEquals(id, getByQuery.getId());
        Assert.assertEquals(product_id, getByQuery.getProduct_id());
        Assert.assertEquals(comment2, getByQuery.getComment());
        Assert.assertEquals(true, getByQuery.getUpdated_at().contains("2018"));
    }

    @Test
    public void E_getExpenseByBusiness(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8086/api/v1.0/businesses/"+business_id+"/expenses/").thenReturn().body();
        ExpenseResponseArray expenseResponseArray = new Gson().fromJson(response.asString(), ExpenseResponseArray.class);
        Expense getByBusiness = expenseResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
    }

    @Test
    public void F_deleteExpense() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+id+"/").thenReturn().body();
        ExpenseResponse expenseResponse = new Gson().fromJson(response.asString(), ExpenseResponse.class);
        Expense deleteExpense = expenseResponse.data;
        Assert.assertEquals(id, deleteExpense.getId());
        Assert.assertEquals(true, deleteExpense.getDeleted_at().contains("2018"));
    }
    @AfterClass
    public void deleteBefore() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/businesses/" + business_id + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        this.business_id = businesses.getId();

        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLSupplier + supplier_id + "/").thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response2.asString(), SuppliersResponse.class);
        Suppliers deleteSuppl = suppliersResponse.data;
        Assert.assertEquals(supplier_id, deleteSuppl.getId());

        ResponseBody response3 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLPRoduct + product_id + "/").thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response3.asString(), ProductsResponse.class);
        Products deleteProd = productsResponse.data;
        Assert.assertEquals(product_id, deleteProd.getId());

        ResponseBody response4 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLWarehouse + warehouse_id + "/").thenReturn().body();
        WarehouseResponse warehouseResponse = new Gson().fromJson(response4.asString(), WarehouseResponse.class);
        Warehouse deleteWarhoses = warehouseResponse.data;
        Assert.assertEquals(warehouse_id, deleteWarhoses.getId());

        ResponseBody response5 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLSupply + supply_id + "/").thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response5.asString(), SupplyResponse.class);
        Supply deleteSupp = supplyResponse.data;
        Assert.assertEquals(supply_id, deleteSupp.getId());
    }
}
