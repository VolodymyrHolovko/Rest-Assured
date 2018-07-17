package BookingREST.Movement;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.ReturnProduct;
import BookingREST.Stock.Stock;
import BookingREST.Stock.StockResponseArray;
import BookingREST.Suppliers.ReturnSupplier;
import BookingREST.Supply.Supply;
import BookingREST.Supply.SupplyData;
import BookingREST.Supply.SupplyResponse;
import BookingREST.Warehouse.ReturnWarehouse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
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
    Faker faker = new Faker();
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
    int cost = faker.number().randomDigitNotZero();
    String currency = "UAH";
    String baseURL = "https://staging.eservia.com:8086/api/v1.0/movements/";
    String baseURLSupply = "https://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLStock = "https://staging.eservia.com:8086/api/v1.0/stocks/";
    String stockQUery = "?warehouse_id=";
    SupplyData supplyData = new SupplyData();
    MovementData movementData = new MovementData();

    @BeforeClass
    public void beforeActions() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();
        this.sender_responsible_id = getBusiness.B_returnStaff();

        ReturnSupplier getSupplier = new ReturnSupplier();
        this.supplier_id = getSupplier.ReturnSupplier(business_id);

        ReturnProduct getProduct = new ReturnProduct();
        this.product_id = getProduct.ReturnProduct(business_id);

        ReturnWarehouse getWarehouse = new ReturnWarehouse();
        this.warehouse_id = getWarehouse.ReturnWarehouse(business_id, address_id, responsible_id);
        this.sender_warehouse_id = warehouse_id;

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
    }
    @Test
    public void addNewMovement() {
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
        System.out.println(response.asString());
    }
}
