package BookingREST.Sale;

import Auth.GetToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Products.ReturnProduct;
import BookingREST.Stock.Stock;
import BookingREST.Stock.StockResponseArray;
import BookingREST.Suppliers.ReturnSupplier;
import BookingREST.Supply.ReturnSupply;
import BookingREST.Warehouse.ReturnWarehouse;
import Customers.RetutnCustomer;
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

public class SaleTests {
    int id;
    String token;
    String tokenResto;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/sales/";
    String baseURLStock = "http://213.136.86.27:8086/api/v1.0/stocks/";
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
    int cost = faker.number().randomDigitNotZero();
    int count = faker.number().randomDigitNotZero();
    String comment = faker.chuckNorris().fact();
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
}
