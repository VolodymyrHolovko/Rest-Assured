package BookingREST.СategoryWarehouses;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CategoryWarehousesTests {
    int id;
    String token;
    int business_id;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/categories/";
    Faker faker = new Faker();
    String name = faker.app().name().toLowerCase();
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
        System.out.println(response.asString());
    }
}
