package Nomenclature.Category;

import Auth.GetToken;
import Departments.DepartmentTest;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CategoryTests {
    String baseURI = "https://staging.eservia.com:8008/api/v0.0/Categories";
    public int ids;
    CategoryData categoryData= new CategoryData();


    String token;
    int DepartmentId;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        DepartmentTest getDepartment = new DepartmentTest();
        getDepartment.token = token;
        this.DepartmentId = getDepartment.getId();
    }

    @Test
    public void A_createCategory() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(categoryData.createCategoty())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        CategoryResponse categoryResponse  = new Gson().fromJson(response.asString(),  CategoryResponse.class);
        Category category = categoryResponse.data;
        this.ids = category.getId();

        Assert.assertEquals(null, category.getDepartmentId());
        Assert.assertEquals(2,category.getAddressId());
        Assert.assertEquals(1,category.getParentId());
        Assert.assertEquals(1,category.getCategoryTypeId());
        Assert.assertEquals("MaxCreAted",category.getName());
        Assert.assertEquals("This is the perfect Category",category.getDescription());
        Assert.assertEquals("#221133",category.getColorHex());
        Assert.assertEquals("Images/201710/file636449551671201110.png",category.getIconPath());
    }

    @Test
    public void B_updateCategory() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(categoryData.updateCategoty())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI+"/"+ids).thenReturn().body();
        CategoryResponse categoryResponse  = new Gson().fromJson(response.asString(),  CategoryResponse.class);
        Category category = categoryResponse.data;
        this.ids = category.getId();

        Assert.assertEquals(null, category.getDepartmentId());
        Assert.assertEquals(2,category.getAddressId());
        Assert.assertEquals(1,category.getParentId());
        Assert.assertEquals(1,category.getCategoryTypeId());
        Assert.assertEquals("MaxCreAted2",category.getName());
        Assert.assertEquals("This is the perfect Category2",category.getDescription());
        Assert.assertEquals("#221132",category.getColorHex());
        Assert.assertEquals("Images/201710/file636449552169080100.png",category.getIconPath());
    }

    @Test
    public void C_getCategory() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+"/"+ids).thenReturn().body();
        CategoryResponse categoryResponse  = new Gson().fromJson(response.asString(),  CategoryResponse.class);
        Category category = categoryResponse.data;
        this.ids = category.getId();

        Assert.assertEquals(null, category.getDepartmentId());
        Assert.assertEquals(2,category.getAddressId());
        Assert.assertEquals(1,category.getParentId());
        Assert.assertEquals(1,category.getCategoryTypeId());
        Assert.assertEquals("MaxCreAted2",category.getName());
        Assert.assertEquals("This is the perfect Category2",category.getDescription());
        Assert.assertEquals("#221132",category.getColorHex());
        Assert.assertEquals("Images/201710/file636449552169080100.png",category.getIconPath());
    }

    @Test
    public void getAllCategory(){
        RequestSpecification httpsRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpsRequest.get("https://staging.eservia.com:8008/api/v0.0/Categories/Tree");
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void D_deleteCategory() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + ids).thenReturn().body();
    }
}
