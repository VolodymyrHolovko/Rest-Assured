package BookingREST.Supply;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.ReturnProduct;
import BookingREST.Suppliers.ReturnSupplier;
import BookingREST.Warehouse.ReturnWarehouse;
import BookingREST.Warehouse.Warehouse;
import BookingREST.Warehouse.WarehouseData;
import BookingREST.Warehouse.WarehouseResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

public class SupplyTests {
    int id;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/supplies/";
    String token;
    public int business_id;
    int supplier_id;
    int address_id;
    int responsible_id;
    int warehouse_id;
    int product_id;
    Faker faker = new Faker();
    String title = faker.name().firstName().toLowerCase();
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
}
