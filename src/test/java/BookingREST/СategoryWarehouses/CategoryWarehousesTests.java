package BookingREST.СategoryWarehouses;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
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

public class CategoryWarehousesTests {
    int id;
    String token;
    int business_id;
    String node_id;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/categories/";
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
    public void B_updateCategory() {
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
}
