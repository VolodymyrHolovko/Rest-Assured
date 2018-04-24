package Nomenclature.Category;

import Auth.GetToken;
import Departments.DepartmentTest;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
import Nomenclature.Portion.NomenclaturePortionTestData;
import Nomenclature.Sizes.SizeData;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class CategoryTests {
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Categories";
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
    public void A_createNomenclature() {
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
        Assert.assertEquals(2,category.getParentId());
        Assert.assertEquals(1,category.getCategoryTypeId());
        Assert.assertEquals("MaxCreAted",category.getName());
        Assert.assertEquals("This is the perfect Category",category.getDescription());
        Assert.assertEquals("#221133",category.getColorHex());
        Assert.assertEquals("Images/201710/file636449551671201110.png",category.getIconPath());
    }

    @Test
    public void B_updateNomenclature() {
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
        Assert.assertEquals(2,category.getParentId());
        Assert.assertEquals(1,category.getCategoryTypeId());
        Assert.assertEquals("MaxCreAted2",category.getName());
        Assert.assertEquals("This is the perfect Category2",category.getDescription());
        Assert.assertEquals("#221132",category.getColorHex());
        Assert.assertEquals("Images/201710/file636449552169080100.png",category.getIconPath());
    }

    @Test
    public void C_getNomenclature() {
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
    public void D_deleteNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + ids).thenReturn().body();
    }
}
