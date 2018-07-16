package BookingREST.Stock;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.Products;
import BookingREST.Products.ProductsResponse;
import BookingREST.Products.ReturnProduct;
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

public class StockTests {
    int id;
    String token;
    String bseURL = "https://staging.eservia.com:8086/api/v1.0/stocks/";
    String baseURLSupplies = "https://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLByBusiness = "https://staging.eservia.com:8086/api/v1.0/businesses/";
    public int business_id;
    int warehouse_id;
    int product_id;
    int address_id;
    int responsible_id;
    int supplier_id;
    Faker faker = new Faker();
    String currency = "UAH";
    String stockQUery = "?warehouse_id=";
    int cost = faker.number().randomDigit();
    int supplyID;
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
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
                .when().post(baseURLSupplies).thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response.asString(), SupplyResponse.class);
        Supply addSupp = supplyResponse.data;
        this.supplyID = addSupp.getId();
    }
    @Test
    public void A_getStockByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(bseURL + stockQUery + warehouse_id + "&sort=-id").thenReturn().body();
        StockResponseArray stockResponse = new Gson().fromJson(response.asString(), StockResponseArray.class);
        Stock getByQuery = stockResponse.data.get(0);
        this.id = getByQuery.getId();
        Assert.assertEquals(id, getByQuery.getId());
        Assert.assertEquals(business_id, getByQuery.getBusiness_id());
        Assert.assertEquals(warehouse_id, getByQuery.getWarehouse_id());
        Assert.assertEquals(product_id, getByQuery.getProduct_id());
        Assert.assertEquals(count, getByQuery.getCount());
    }
    @Test
    public void B_getStockById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(bseURL + id + "/").thenReturn().body();
        StockResponse stockResponse = new Gson().fromJson(response.asString(), StockResponse.class);
        Stock getById = stockResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(warehouse_id, getById.getWarehouse_id());
        Assert.assertEquals(product_id, getById.getProduct_id());
        Assert.assertEquals(count, getById.getCount());
    }
    @Test
    public void C_getStockByBusiness() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLByBusiness + business_id + "/stocks/").thenReturn().body();
        StockResponseArray stockResponseArray = new Gson().fromJson(response.asString(), StockResponseArray.class);
        Stock getByBusiness = stockResponseArray.data.get(0);
        Assert.assertEquals(id, getByBusiness.getId());
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
        Assert.assertEquals(warehouse_id, getByBusiness.getWarehouse_id());
        Assert.assertEquals(product_id, getByBusiness.getProduct_id());
        Assert.assertEquals(count, getByBusiness.getCount());
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
        Assert.assertEquals(business_id, businesses.getId());

        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8086/api/v1.0/suppliers/" + supplier_id + "/").thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response2.asString(), SuppliersResponse.class);
        Suppliers deleteSuppl = suppliersResponse.data;
        Assert.assertEquals(supplier_id, deleteSuppl.getId());

        ResponseBody response3 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8086/api/v1.0/products/" + product_id + "/").thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response3.asString(), ProductsResponse.class);
        Products deleteProd = productsResponse.data;
        Assert.assertEquals(product_id, deleteProd.getId());

        ResponseBody response4 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8086/api/v1.0/warehouses/" + warehouse_id + "/").thenReturn().body();
        WarehouseResponse warehouseResponse = new Gson().fromJson(response4.asString(), WarehouseResponse.class);
        Warehouse deleteWarhoses = warehouseResponse.data;
        Assert.assertEquals(warehouse_id, deleteWarhoses.getId());

        ResponseBody response5 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8086/api/v1.0/supplies/" + supplyID + "/").thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response5.asString(), SupplyResponse.class);
        Supply deleteSupp = supplyResponse.data;
        Assert.assertEquals(supplyID, deleteSupp.getId());
    }
}
