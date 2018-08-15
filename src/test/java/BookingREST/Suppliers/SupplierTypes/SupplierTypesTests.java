package BookingREST.Suppliers.SupplierTypes;

import BookingREST.AuthBusiness.AuthBusinessTest;
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

public class SupplierTypesTests {
    String token;
    String baseURL = "https://staging.eservia.com:8086/api/v1.0/supplier-types/";
    public int id;
    Faker faker = new Faker();
    String title = faker.name().lastName().toLowerCase()+faker.name().firstName();
    String title2 = faker.name().title().toLowerCase()+faker.name().firstName();
    SupplierTypesData supplierTypesData = new SupplierTypesData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
    }

    @Test
    public void A_addSupplierTypes() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(supplierTypesData.addSupplierType(title))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes addSupplierType = supplierTypesResponse.data;
        this.id = addSupplierType.getId();
        Assert.assertEquals(id, addSupplierType.getId());
        Assert.assertEquals(title, addSupplierType.getTitle());
        Assert.assertEquals(true, addSupplierType.getCreated_at().startsWith("2018"));
    }
    @Test
    public void B_getSupplierTypeById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes getTypeById = supplierTypesResponse.data;
        Assert.assertEquals(id, getTypeById.getId());
        Assert.assertEquals(title, getTypeById.getTitle());
        Assert.assertEquals(true, getTypeById.getCreated_at().startsWith("2018"));
    }
    @Test
    public void C_updateSupplierTypes() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(supplierTypesData.updateSupplierType(title2))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes updateSupplierType = supplierTypesResponse.data;
        Assert.assertEquals(id, updateSupplierType.getId());
        Assert.assertEquals(title2,updateSupplierType.getTitle());
    }
    @Test
    public void D_getSupplierTypeByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + "?q=" + title2+"&sort=-id").thenReturn().body();
        SupplierTypesResponseArray supplierTypesResponseArray = new Gson().fromJson(response.asString(), SupplierTypesResponseArray.class);
        SupplierTypes getByQuery = supplierTypesResponseArray.data.get(0);
        Assert.assertEquals(title2, getByQuery.getTitle());
    }
    @Test
    public void E_deleteSupplierTypes() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes deleteSupplierType = supplierTypesResponse.data;
        Assert.assertEquals(id, deleteSupplierType.getId());
        Assert.assertEquals(true,deleteSupplierType.getDeleted_at().startsWith("2018"));
    }
}
