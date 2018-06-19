package BookingREST.Products;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.СategoryWarehouses.CategoryWarehouses;
import BookingREST.СategoryWarehouses.CategoryWarehousesData;
import BookingREST.СategoryWarehouses.CategoryWarehousesResponse;
import BookingREST.СategoryWarehouses.CategoryWarehousesTests;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class ProductsTests {
    int id;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/products/";
    String baseURLCategory = "http://213.136.86.27:8086/api/v1.0/categories/";
    String token;
    int business_id;
    int category_id;
    Faker faker = new Faker();
    String nameCategory = faker.name().lastName().toLowerCase();
    String name = faker.food().ingredient();
    String sku = faker.code().isbnGroup();
    int sale_unit_id;
    int expense_unit_id;
    int expense_cost = faker.number().randomDigit();
    String expense_currency = faker.currency().code();
    int sale_cost = faker.number().randomDigit();
    String sale_currency = faker.currency().code();
    CategoryWarehousesData categoryWarehousesData = new CategoryWarehousesData();
    ProductsData productsData = new ProductsData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();

        CategoryWarehouses addCategories = categoryWarehousesData.addNewCategory(business_id, nameCategory);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addCategories)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLCategory).thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse = new Gson().fromJson(response.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses addCategory = categoryWarehousesResponse.data;
        this.category_id = addCategory.getId();
    }
    @Test
    public void addProducts() {
    Products addProductt = productsData.addNewProducts(business_id, name, sku, category_id, sale_unit_id, sale_currency, sale_cost, expense_unit_id, expense_currency, expense_cost);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addProductt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response.asString(), ProductsResponse.class);
        Products addProd = productsResponse.data;
        System.out.println(response.asString());
    }
}
