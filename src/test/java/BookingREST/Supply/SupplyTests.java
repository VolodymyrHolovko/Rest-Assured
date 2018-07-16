package BookingREST.Supply;

import Auth.GetToken;
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

public class SupplyTests {
    int id;
    String baseURL = "https://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLBUsiness = "https://staging.eservia.com:8086/api/v1.0/businesses/";
    String baseURLSupplier = "https://staging.eservia.com:8086/api/v1.0/suppliers/";
    String baseURLPRoduct = "https://staging.eservia.com:8086/api/v1.0/products/";
    String baseURLWarehouse = "https://staging.eservia.com:8086/api/v1.0/warehouses/";
    String token;
    public int business_id;
    int supplier_id;
    int address_id;
    int responsible_id;
    int warehouse_id;
    int product_id;
    Faker faker = new Faker();
    String currency = "UAH";
    int cost = faker.number().randomDigit();
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
    String comment2 = faker.lordOfTheRings().character();
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
    }
    @Test
    public void A_addNewSupply() {
        Supply addSupply = supplyData.addNewSupply(business_id, supplier_id, warehouse_id, product_id, count, currency, cost, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSupply)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response.asString(), SupplyResponse.class);
        Supply addSupp = supplyResponse.data;
        this.id = addSupp.getId();
        Assert.assertEquals(id, addSupp.getId());
        Assert.assertEquals(business_id, addSupp.getBusiness_id());
        Assert.assertEquals(supplier_id, addSupp.getSupplier_id());
        Assert.assertEquals(warehouse_id, addSupp.getWarehouse_id());
        Assert.assertEquals(product_id, addSupp.getProduct_id());
        Assert.assertEquals(count, addSupp.getCount());
        Assert.assertEquals(currency, addSupp.getCurrency());
        Assert.assertEquals(cost, addSupp.getCost());
        Assert.assertEquals(comment, addSupp.getComment());
    }
    @Test
    public void B_getSupplyByID() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response.asString(), SupplyResponse.class);
        Supply getById = supplyResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(supplier_id, getById.getSupplier_id());
        Assert.assertEquals(warehouse_id, getById.getWarehouse_id());
        Assert.assertEquals(product_id, getById.getProduct_id());
        Assert.assertEquals(count, getById.getCount());
        Assert.assertEquals(currency, getById.getCurrency());
        Assert.assertEquals(cost, getById.getCost());
        Assert.assertEquals(comment, getById.getComment());
    }
    @Test
    public void C_updateSupply() {
        Supply updateSupply = supplyData.updateSupply(comment2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateSupply)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response.asString(), SupplyResponse.class);
        Supply updateSupp = supplyResponse.data;
        Assert.assertEquals(id, updateSupp.getId());
        Assert.assertEquals(comment2, updateSupp.getComment());
        Assert.assertEquals(true, updateSupp.getUpdated_at().contains("2018"));
    }
    @Test
    public  void D_getSupplyByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + "?product_id=" + product_id).thenReturn().body();
        SupplyResponseArray supplyResponseArray = new Gson().fromJson(response.asString(), SupplyResponseArray.class);
        Supply getByQUery = supplyResponseArray.data.get(0);
        Assert.assertEquals(product_id, getByQUery.getProduct_id());
        Assert.assertEquals(comment2, getByQUery.getComment());
    }
    @Test
    public void E_getSupplyByBusiness() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLBUsiness + business_id + "/supplies/").thenReturn().body();
        SupplyResponseArray supplyResponseArray = new Gson().fromJson(response.asString(), SupplyResponseArray.class);
        Supply getByBusiness = supplyResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
    }
    @Test
    public void F_deleteSupply() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response.asString(), SupplyResponse.class);
        Supply deleteSupp = supplyResponse.data;
        Assert.assertEquals(id, deleteSupp.getId());
        Assert.assertEquals(true, deleteSupp.getDeleted_at().contains("2018"));
    }
    @AfterClass
    public void DeleteBefore() {
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
    }
}
