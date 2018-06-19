package BookingREST.СategoryWarehouses;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
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

public class CategoryWarehousesTests {
    int id;
    public String token;
    int business_id;
    String node_id;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/categories/";
    String baseURLBisiness = "http://213.136.86.27:8086/api/v1.0/businesses/";
    Faker faker = new Faker();
    String name = faker.app().name().toLowerCase();
    String name2 = faker.app().name().toLowerCase();
    CategoryWarehousesData categoryWarehousesData = new CategoryWarehousesData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
    }
    @Test
    public void A_createCategory() {
        CategoryWarehouses addCategories = categoryWarehousesData.addNewCategory(business_id, name);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addCategories)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse = new Gson().fromJson(response.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses addCategory = categoryWarehousesResponse.data;
        this.id = addCategory.getId();
        this.node_id = String.valueOf(addCategory.getId());
        Assert.assertEquals(id, addCategory.getId());
        Assert.assertEquals(business_id, addCategory.getBusiness_id());
        Assert.assertEquals(null, addCategory.getNode_id());
        Assert.assertEquals(name, addCategory.getName());
    }
    @Test
    public void B_getCategoryByID() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse = new Gson().fromJson(response.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses getById = categoryWarehousesResponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(business_id, getById.getBusiness_id());
        Assert.assertEquals(null, getById.getNode_id());
        Assert.assertEquals(name, getById.getName());
        Assert.assertEquals(0, getById.getNested_level());
    }
    @Test
    public void C_updateCategory() {
        CategoryWarehouses updateCategories = categoryWarehousesData.updateCategory(node_id, name2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateCategories)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse = new Gson().fromJson(response.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses updateCategory = categoryWarehousesResponse.data;
        Assert.assertEquals(id, updateCategory.getId());
        Assert.assertEquals(node_id, updateCategory.getNode_id());
        Assert.assertEquals(name2, updateCategory.getName());
        Assert.assertEquals(1, updateCategory.getNested_level());
        Assert.assertEquals(true, updateCategory.getUpdated_at().contains("2018"));
    }
    @Test
    public void D_getCategoryByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + "?q=" + name2 + "&sort=-id").thenReturn().body();
        CategoryWarehousesResponseArray categoryWarehousesResponseArray = new Gson().fromJson(response.asString(), CategoryWarehousesResponseArray.class);
        CategoryWarehouses getByQuery = categoryWarehousesResponseArray.data.get(0);
        Assert.assertEquals(id, getByQuery.getId());
        Assert.assertEquals(name2, getByQuery.getName());
        Assert.assertEquals(true, getByQuery.getUpdated_at().contains("2018"));
    }
    @Test
    public void E_getCategoryByBusinessID() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLBisiness + business_id + "/categories/").thenReturn().body();
        CategoryWarehousesResponseArray categoryWarehousesResponseArray = new Gson().fromJson(response.asString(), CategoryWarehousesResponseArray.class);
        CategoryWarehouses getCategoryByBusiness = categoryWarehousesResponseArray.data.get(0);
        Assert.assertEquals(business_id, getCategoryByBusiness.getBusiness_id());
    }
    @Test
    public void F_deleteCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        CategoryWarehousesResponse categoryWarehousesResponse = new Gson().fromJson(response.asString(), CategoryWarehousesResponse.class);
        CategoryWarehouses deleteCategory = categoryWarehousesResponse.data;
        Assert.assertEquals(id, deleteCategory. getId());
        Assert.assertEquals(true, deleteCategory.getDeleted_at().contains("2018"));
    }

    @AfterClass
    public void deleteAfter() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + business_id + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        Assert.assertEquals(business_id, businesses.getId());
        
    }


}
