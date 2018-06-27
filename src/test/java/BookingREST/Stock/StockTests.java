package BookingREST.Stock;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.ReturnProduct;
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

public class StockTests {
    int id;
    String token;
    String bseURL = "http://213.136.86.27:8086/api/v1.0/stocks/";
    String baseURLSupplies = "http://staging.eservia.com:8086/api/v1.0/supplies/";
    public int business_id;
    int warehouse_id;
    int product_id;
    int address_id;
    int responsible_id;
    int supplier_id;
    Faker faker = new Faker();
    String currency = "UAH";
    int cost = faker.number().randomDigit();
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
        this.id = addSupp.getId();
    }
    @Test
    public void A_getStockByID() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(bseURL + id + "/").thenReturn().body();
        StockResponse stockResponse = new Gson().fromJson(response.asString(), StockResponse.class);
        Stock getByID = stockResponse.data;
        System.out.println(response.asString());
    }
}
