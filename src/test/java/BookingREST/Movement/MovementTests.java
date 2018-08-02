package BookingREST.Movement;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.Products;
import BookingREST.Products.ProductsResponse;
import BookingREST.Products.ReturnProduct;
import BookingREST.Staffs.Staff;
import BookingREST.Staffs.StaffData;
import BookingREST.Staffs.StaffResponse;
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

public class MovementTests {
    String token;
    int id;
    int business_id;
    int address_id;
    int responsible_id;
    int warehouse_id;
    int sender_warehouse_id;
    int sender_responsible_id;
    int sender_stock_id;
    int product_id;
    int supplier_id;
    int stock_id;
    int supply_id;
    int receiver_warehouse_id;
    int receiver_responsible_id;
    int planId;
    Faker faker = new Faker();
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
    String comment2 = faker.lordOfTheRings().location();
    int cost = faker.number().randomDigitNotZero();
    String currency = "UAH";
    String phone = faker.regexify("+380[0-9]{9}");
    String email = faker.name().lastName()+faker.name().firstName()+"@mail.com";
    String baseURL = "https://staging.eservia.com:8086/api/v1.0/movements/";
    String baseURLByBusiness = "https://staging.eservia.com:8086/api/v1.0/businesses/";
    String baseURLSupply = "https://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLStock = "https://staging.eservia.com:8086/api/v1.0/stocks/";
    String baseURLStaff = "https://staging.eservia.com:8084/api/v1.0/staffs/";
    String baseURLSupplier = "https://staging.eservia.com:8086/api/v1.0/suppliers/";
    String baseURLPRoduct = "https://staging.eservia.com:8086/api/v1.0/products/";
    String baseURLWarehouse = "https://staging.eservia.com:8086/api/v1.0/warehouses/";
    String stockQUery = "?warehouse_id=";
    String movementQuery = "?product_id=";
    SupplyData supplyData = new SupplyData();
    MovementData movementData = new MovementData();
    StaffData staffData = new StaffData();

    @BeforeClass
    public void beforeActions() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();
        this.sender_responsible_id = getBusiness.B_returnStaff();
        this.planId = getBusiness.returnPlan();

        ReturnSupplier getSupplier = new ReturnSupplier();
        this.supplier_id = getSupplier.ReturnSupplier(business_id);

        ReturnProduct getProduct = new ReturnProduct();
        this.product_id = getProduct.ReturnProduct(business_id);

        ReturnWarehouse getWarehouse = new ReturnWarehouse();
        this.warehouse_id = getWarehouse.ReturnWarehouse(business_id, address_id, responsible_id);
        this.sender_warehouse_id = warehouse_id;
        this.receiver_warehouse_id = getWarehouse.ReturnWarehouse(business_id, address_id, responsible_id);

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
        this.sender_stock_id = getByQuery.getId();

        ResponseBody staffsees = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.createStaff(business_id,address_id,phone,email))
                .when().post(baseURLStaff).thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(staffsees.asString(), StaffResponse.class);
        Staff staff = staffResponse.getData();
        this.receiver_responsible_id = staff.getId();
    }
    @Test
    public void A_addNewMovement() {
        Movement addMovements = movementData.addNewMovement(business_id, sender_warehouse_id, receiver_warehouse_id, sender_responsible_id, receiver_responsible_id, sender_stock_id, product_id, count, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addMovements)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        MovementResponse movementResponse = new Gson().fromJson(response.asString(), MovementResponse.class);
        Movement addMovement = movementResponse.data;
        this.id = addMovement.getId();
        Assert.assertEquals(business_id, addMovement.getBusiness_id());
        Assert.assertEquals(sender_warehouse_id, addMovement.getSender_warehouse_id());
        Assert.assertEquals(receiver_warehouse_id, addMovement.getReceiver_warehouse_id());
        Assert.assertEquals(sender_responsible_id, addMovement.getSender_responsible_id());
        Assert.assertEquals(receiver_responsible_id, addMovement.getReceiver_responsible_id());
        Assert.assertEquals(sender_stock_id, addMovement.getSender_stock_id());
        Assert.assertEquals(product_id, addMovement.getProduct_id());
        Assert.assertEquals(count, addMovement.getCount());
        Assert.assertEquals(comment, addMovement.getComment());
    }
    @Test
    public void B_getMovementsById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+id).thenReturn().body();
        MovementResponse movementResponse = new Gson().fromJson(response.asString(), MovementResponse.class);
        Movement getById = movementResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(sender_warehouse_id, getById.getSender_warehouse_id());
        Assert.assertEquals(receiver_warehouse_id, getById.getReceiver_warehouse_id());
        Assert.assertEquals(sender_responsible_id, getById.getSender_responsible_id());
        Assert.assertEquals(receiver_responsible_id, getById.getReceiver_responsible_id());
        Assert.assertEquals(sender_stock_id, getById.getSender_stock_id());
        Assert.assertEquals(product_id, getById.getProduct_id());
        Assert.assertEquals(count, getById.getCount());
        Assert.assertEquals(comment, getById.getComment());
    }
    @Test
    public void C_updateMovements() {
        Movement updateMovements = movementData.updateMovements(comment2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateMovements)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+id).thenReturn().body();
        MovementResponse movementResponse = new Gson().fromJson(response.asString(), MovementResponse.class);
        Movement updateMovement = movementResponse.data;
        Assert.assertEquals(id, updateMovement.getId());
        Assert.assertEquals(comment2, updateMovement.getComment());
        Assert.assertEquals(true, updateMovement.getUpdated_at().contains("2018"));
    }
    @Test
    public void D_getMovementByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+movementQuery+product_id+"&sort=-id").thenReturn().body();
        MovementResponseArray movementResponseArray = new Gson().fromJson(response.asString(), MovementResponseArray.class);
        Movement getByQuery = movementResponseArray.data.get(0);
        Assert.assertEquals(product_id, getByQuery.getProduct_id());
    }
    @Test
    public void E_getMovementByBusinessId() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLByBusiness+business_id+"/movements/").thenReturn().body();
        MovementResponseArray movementResponseArray = new Gson().fromJson(response.asString(), MovementResponseArray.class);
        Movement getByBusiness = movementResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
    }
    @Test
    public void F_deleteMovements() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+id).thenReturn().body();
        MovementResponse movementResponse = new Gson().fromJson(response.asString(), MovementResponse.class);
        Movement deleteMovement = movementResponse.data;
        Assert.assertEquals(id, deleteMovement.getId());
        Assert.assertEquals(true, deleteMovement.getDeleted_at().contains("2018"));
    }


    @AfterClass
    public void deleteBefore() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
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

        ResponseBody response41 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLWarehouse + receiver_warehouse_id + "/").thenReturn().body();
        WarehouseResponse warehouseResponse2 = new Gson().fromJson(response41.asString(), WarehouseResponse.class);
        Warehouse deleteWarhoses2 = warehouseResponse.data;

        ResponseBody response5 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLSupply + supply_id + "/").thenReturn().body();
        SupplyResponse supplyResponse = new Gson().fromJson(response5.asString(), SupplyResponse.class);
        Supply deleteSupp = supplyResponse.data;
        Assert.assertEquals(supply_id, deleteSupp.getId());

        RequestSpecification httpsRequest = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response6 = httpsRequest.delete(baseURLStaff + responsible_id + "/");
        Assert.assertEquals(200, response6.getStatusCode());

        RequestSpecification httpsRequest2 = RestAssured.given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response61 = httpsRequest.delete(baseURLStaff + sender_responsible_id + "/");

        RequestSpecification httpsRequest3 = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response62 = httpsRequest.delete(baseURLStaff + receiver_responsible_id + "/");

        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }
}
