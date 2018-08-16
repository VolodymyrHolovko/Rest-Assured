package BookingREST.Products;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Units.Units;
import BookingREST.Units.UnitsData;
import BookingREST.Units.UnitsRespponse;
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
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class ProductsTests {
    int id;
    String baseURL = "https://staging.eservia.com:8086/api/v1.0/products/";
    String baseURLCategory = "https://staging.eservia.com:8086/api/v1.0/categories/";
    String baseURLUnits = "https://staging.eservia.com:8086/api/v1.0/units/";
    String baseURLByBusiness = "https://staging.eservia.com:8086/api/v1.0/businesses/";
    String token;
    int promoterId;
    int planId;
    int business_id;
    int category_id;
    int category_id2;
    Faker faker = new Faker();
    String nameCategory = faker.name().lastName().toLowerCase();
    String name = faker.food().ingredient();
    String sku = faker.name().firstName().toUpperCase();
    String abbr = faker.app().name().toLowerCase();
    String nameUnit = faker.name().title().toLowerCase();
    String name2 = faker.food().ingredient();
    String sku2 = faker.name().firstName().toUpperCase();
    int unit_id;
    int unit_id2;
    int expense_unit_id;
    int expense_unit_id2;
    int expense_cost = faker.number().randomDigit();
    int expense_cost2 = faker.number().randomDigit();
    String expense_currency = "USD";
    String expense_currency2 = "UAH";
    int sale_cost = faker.number().randomDigit();
    int sale_cost2= faker.number().randomDigit();
    String sale_currency = "USD";
    String sale_currency2 = "UAH";
    CategoryWarehousesData categoryWarehousesData = new CategoryWarehousesData();
    UnitsData unitsData = new UnitsData();
    ProductsData productsData = new ProductsData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
        CreateBusiness getBusiness = new CreateBusiness();
        this.planId = getBusiness.returnPlan();
        this.promoterId = getBusiness.returnPromoter();
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

        CategoryWarehouses addCategories2 = categoryWarehousesData.addNewCategory(business_id, nameCategory);
        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addCategories2)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLCategory).thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse2 = new Gson().fromJson(response2.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses addCategory2 = categoryWarehousesResponse2.data;
        this.category_id2 = addCategory2.getId();

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
        this.unit_id = addUnitss.getId();
        this.expense_unit_id = addUnitss.getId();

        Units addUnitt2 = unitsData.addUnits(nameUnit, abbr);
        ResponseBody response4 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addUnitt2)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLUnits).thenReturn().body();
        UnitsRespponse unitsRespponse2 = new Gson().fromJson(response4.asString(), UnitsRespponse.class);
        Units addUnitss2 = unitsRespponse2.data;
        this.unit_id2 = addUnitss2.getId();
        this.expense_unit_id2 = addUnitss2.getId();
    }
    @Test
    public void A_addProducts() {
    Products addProductt = productsData.addNewProducts(business_id, name, sku, category_id, unit_id);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addProductt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response.asString(), ProductsResponse.class);
        Products addProd = productsResponse.data;
        this.id = addProd.getId();
        Assert.assertEquals(id, addProd.getId());
        Assert.assertEquals(business_id, addProd.getBusiness_id());
        Assert.assertEquals(name, addProd.getName());
        Assert.assertEquals(sku, addProd.getSku());
        Assert.assertEquals(category_id, addProd.getCategory_id());
        Assert.assertEquals(unit_id, addProd.getUnit_id());
    }
    @Test
    public void B_getProductByid() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response.asString(), ProductsResponse.class);
        Products getById = productsResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(name, getById.getName());
        Assert.assertEquals(sku, getById.getSku());
        Assert.assertEquals(category_id, getById.getCategory_id());
        Assert.assertEquals(unit_id, getById.getUnit_id());
    }
    @Test
    public void C_updateProducts() {
        Products updateProductt = productsData.updateProducts(name2, sku2, category_id2, unit_id2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateProductt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response.asString(), ProductsResponse.class);
        Products updateProd = productsResponse.data;
        Assert.assertEquals(id, updateProd.getId());
        Assert.assertEquals(business_id, updateProd.getBusiness_id());
        Assert.assertEquals(name2, updateProd.getName());
        Assert.assertEquals(sku2, updateProd.getSku());
        Assert.assertEquals(category_id2, updateProd.getCategory_id());
        Assert.assertEquals(unit_id2, updateProd.getUnit_id());
        Assert.assertEquals(true, updateProd.getUpdated_at().contains("2018"));
    }
    @Test
    public void D_getProductsByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + "?q=" + name2 + "&sort=-id").thenReturn().body();
        ProductsResponseArray productsResponseArray = new Gson().fromJson(response.asString(), ProductsResponseArray.class);
        Products getByQuery = productsResponseArray.data.get(0);
        Assert.assertEquals(name2, getByQuery.getName());
    }
    @Test
    public void E_getProductByBusinessID() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLByBusiness + business_id + "/products/").thenReturn().body();
        ProductsResponseArray productsResponseArray = new Gson().fromJson(response.asString(), ProductsResponseArray.class);
        Products getByBusiness = productsResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());

    }
    @Test
    public void F_deleteProducts() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        ProductsResponse productsResponse = new Gson().fromJson(response.asString(), ProductsResponse.class);
        Products deleteProd = productsResponse.data;
        Assert.assertEquals(id, deleteProd.getId());
        Assert.assertEquals(true, deleteProd.getDeleted_at().contains("2018"));
    }
    @AfterClass
    public void deleteBEfore() {
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
                .when().delete(baseURLCategory + category_id + "/").thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse = new Gson().fromJson(response2.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses deleteCategory = categoryWarehousesResponse.data;
        Assert.assertEquals(category_id, deleteCategory. getId());

        ResponseBody response3 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLCategory + category_id2 + "/").thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse2 = new Gson().fromJson(response3.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses deleteCategory2 = categoryWarehousesResponse2.data;
        Assert.assertEquals(category_id2, deleteCategory2. getId());

        ResponseBody response4 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLUnits + unit_id + "/").thenReturn().body();
        UnitsRespponse unitsRespponse =  new Gson().fromJson(response4.asString(), UnitsRespponse.class);
        Units deleteUnitss = unitsRespponse.data;
        Assert.assertEquals(unit_id, deleteUnitss.getId());

        ResponseBody response5 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLUnits + unit_id2 + "/").thenReturn().body();
        UnitsRespponse unitsRespponse2 =  new Gson().fromJson(response5.asString(), UnitsRespponse.class);
        Units deleteUnitss2 = unitsRespponse2.data;
        Assert.assertEquals(unit_id2, deleteUnitss2.getId());

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();
        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }


}
