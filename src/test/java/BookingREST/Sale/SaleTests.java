package BookingREST.Sale;

import Auth.GetToken;
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
import BookingREST.Supply.ReturnSupply;
import BookingREST.Supply.Supply;
import BookingREST.Supply.SupplyResponse;
import BookingREST.Warehouse.ReturnWarehouse;
import BookingREST.Warehouse.Warehouse;
import BookingREST.Warehouse.WarehouseResponse;
import Customers.RetutnCustomer;
import com.github.javafaker.Faker;
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

public class SaleTests {
    int id;
    String token;
    String tokenResto;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/sales/";
    String baseURLStock = "http://213.136.86.27:8086/api/v1.0/stocks/";
    String baseURLByBusiness = "http://213.136.86.27:8086/api/v1.0/businesses/";
    String baseURLSupply = "http://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLSupplier = "http://staging.eservia.com:8086/api/v1.0/suppliers/";
    String baseURLPRoduct = "http://staging.eservia.com:8086/api/v1.0/products/";
    String baseURLWarehouse = "http://staging.eservia.com:8086/api/v1.0/warehouses/";
    String baseUrlStaff = "http://staging.eservia.com:8084/api/v1.0/staffs/";
    String stockQUery = "?warehouse_id=";
    public int business_id;
    int supplier_id;
    int address_id;
    int responsible_id;
    int warehouse_id;
    int product_id;
    int supply_id;
    int seller_id;
    int stock_id;
    String customer_id;
    Faker faker = new Faker();
    String currency = "UAH";
    String salesQuery = "?customer_id=";
    int cost = faker.number().randomDigitNotZero();
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
    String comment2 = faker.lordOfTheRings().character();
    SaleData saleData = new SaleData();

    @BeforeClass
    public void beforeActions() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        GetToken getTokenResto = new GetToken();
        this.tokenResto = getTokenResto.GetFinallyToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();
        this.seller_id = getBusiness.B_returnStaff();

        ReturnSupplier getSupplier = new ReturnSupplier();
        this.supplier_id = getSupplier.ReturnSupplier(business_id);

        ReturnProduct getProduct = new ReturnProduct();
        this.product_id = getProduct.ReturnProduct(business_id);

        ReturnWarehouse getWarehouse = new ReturnWarehouse();
        this.warehouse_id = getWarehouse.ReturnWarehouse(business_id, address_id, responsible_id);

        ReturnSupply getSupply = new ReturnSupply();
        getSupply.token = token;
        this.supply_id = getSupply.ReturnSupply(business_id, supplier_id, warehouse_id, product_id, count);


        RetutnCustomer getCustomer = new RetutnCustomer();
        getCustomer.tokenResto = tokenResto;
        this.customer_id = getCustomer.ReturnCustomer(business_id);

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
    public void A_addNewSales() {
        Sale addSales = saleData.addNewSales(business_id, customer_id, seller_id, warehouse_id, stock_id, product_id, count, currency, cost, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSales)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        SaleResponse saleResponse = new Gson().fromJson(response.asString(), SaleResponse.class);
        Sale addSale = saleResponse.data;
        this.id = addSale.getId();
        Assert.assertEquals(id, addSale.getId());
        Assert.assertEquals(business_id, addSale.getBusiness_id());
        Assert.assertEquals(customer_id, addSale.getCustomer_id());
        Assert.assertEquals(seller_id, addSale.getSeller_id());
        Assert.assertEquals(warehouse_id, addSale.getWarehouse_id());
        Assert.assertEquals(stock_id, addSale.getStock_id());
        Assert.assertEquals(product_id, addSale.getProduct_id());
        Assert.assertEquals(count, addSale.getCount());
        Assert.assertEquals(currency, addSale.getCurrency());
        Assert.assertEquals(cost, addSale.getCost());
    }
    @Test
    public void B_getSaleById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+id+"/").thenReturn().body();
        SaleResponse saleResponse = new Gson().fromJson(response.asString(), SaleResponse.class);
        Sale getById = saleResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(customer_id, getById.getCustomer_id());
        Assert.assertEquals(seller_id, getById.getSeller_id());
        Assert.assertEquals(warehouse_id, getById.getWarehouse_id());
        Assert.assertEquals(stock_id, getById.getStock_id());
        Assert.assertEquals(product_id, getById.getProduct_id());
        Assert.assertEquals(count, getById.getCount());
        Assert.assertEquals(currency, getById.getCurrency());
        Assert.assertEquals(cost, getById.getCost());
    }
    @Test
    public void C_updateSale() {
        Sale updateSales = saleData.updateSales(comment2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateSales)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+id+"/").thenReturn().body();
        SaleResponse saleResponse = new Gson().fromJson(response.asString(), SaleResponse.class);
        Sale updateSale = saleResponse.data;
        Assert.assertEquals(id, updateSale.getId());
        Assert.assertEquals(comment2, updateSale.getComment());
        Assert.assertEquals(true, updateSale.getUpdated_at().contains("2018"));
    }
    @Test
    public void D_getSaleByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+salesQuery+customer_id+"&sort=-id").thenReturn().body();
        SaleResponseArray saleResponseArray = new Gson().fromJson(response.asString(), SaleResponseArray.class);
        Sale getByQuery = saleResponseArray.data.get(0);
        Assert.assertEquals(customer_id, getByQuery.getCustomer_id());
    }
    @Test
    public void E_getSaleByBusiness() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLByBusiness+business_id+"/sales/").thenReturn().body();
        SaleResponseArray saleResponseArray = new Gson().fromJson(response.asString(), SaleResponseArray.class);
        Sale geByBusiness = saleResponseArray.data.get(0);
        Assert.assertEquals(business_id, geByBusiness.getBusiness_id());
    }
    @Test
    public void F_deleteSale() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+id+"/").thenReturn().body();
        SaleResponse saleResponse = new Gson().fromJson(response.asString(), SaleResponse.class);
        Sale deleteSale = saleResponse.data;
        Assert.assertEquals(id, deleteSale.getId());
        Assert.assertEquals(true, deleteSale.getDeleted_at().contains("2018"));
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

        RequestSpecification httpRequest = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response6 = httpRequest.delete(baseUrlStaff + seller_id + "/");
        Assert.assertEquals(200, response6.getStatusCode());

    }
}
