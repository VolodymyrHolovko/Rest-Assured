package BookingREST.Products;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Units.Units;
import BookingREST.Units.UnitsData;
import BookingREST.Units.UnitsRespponse;
import BookingREST.СategoryWarehouses.CategoryWarehouses;
import BookingREST.СategoryWarehouses.CategoryWarehousesData;
import BookingREST.СategoryWarehouses.CategoryWarehousesResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;

import static com.jayway.restassured.RestAssured.given;

public class ReturnProduct {
    int id;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/products/";
    String baseURLCategory = "http://213.136.86.27:8086/api/v1.0/categories/";
    String baseURLUnits = "http://213.136.86.27:8086/api/v1.0/units/";
    String token;
   public int business_id;
    int category_id;
    int sale_unit_id;
    int expense_unit_id;
    String sale_currency = "USD";
    Faker faker = new Faker();
    String nameCategory = faker.name().lastName().toLowerCase();
    String name = faker.food().ingredient();
    String sku = faker.name().firstName().toUpperCase();
    String abbr = faker.app().name().toLowerCase();
    String nameUnit = faker.name().title().toLowerCase();
    int expense_cost = faker.number().randomDigit();
    String expense_currency = "USD";
    int sale_cost = faker.number().randomDigit();
    CategoryWarehousesData categoryWarehousesData = new CategoryWarehousesData();
    UnitsData unitsData = new UnitsData();
    ProductsData productsData = new ProductsData();


    public int ReturnProduct(int business_id) {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

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

        Units addUnitt = unitsData.addUnits(nameUnit, abbr);
        ResponseBody response3 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addUnitt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLUnits).thenReturn().body();
        UnitsRespponse unitsRespponse = new Gson().fromJson(response3.asString(), UnitsRespponse.class);
        Units addUnitss = unitsRespponse.data;
        this.sale_unit_id = addUnitss.getId();
        this.expense_unit_id = addUnitss.getId();

        Products addProductt = productsData.addNewProducts(business_id, name, sku, category_id, sale_unit_id, sale_currency, sale_cost, expense_unit_id, expense_currency, expense_cost);
        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addProductt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response2.asString(), ProductsResponse.class);
        Products addProd = productsResponse.data;
        this.id = addProd.getId();
        return id;
    }
}
