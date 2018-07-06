package BookingREST.Supply;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.ReturnProduct;
import BookingREST.Suppliers.ReturnSupplier;
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

public class ReturnSupply {
    int id;
    String baseURL = "http://staging.eservia.com:8086/api/v1.0/supplies/";
    String baseURLBUsiness = "http://staging.eservia.com:8086/api/v1.0/businesses/";
    String baseURLSupplier = "http://staging.eservia.com:8086/api/v1.0/suppliers/";
    String baseURLPRoduct = "http://staging.eservia.com:8086/api/v1.0/products/";
    String baseURLWarehouse = "http://staging.eservia.com:8086/api/v1.0/warehouses/";
    public String token;
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
    SupplyData supplyData = new SupplyData();


    public int ReturnSupply(int business_id, int supplier_id, int warehouse_id, int product_id, int count) {
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
        return id;
    }
}
