package BookingREST.Suppliers;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Suppliers.SupplierTypes.SupplierTypes;
import BookingREST.Suppliers.SupplierTypes.SupplierTypesData;
import BookingREST.Suppliers.SupplierTypes.SupplierTypesResponse;
import BookingREST.Suppliers.SupplierTypes.SupplierTypesResponseArray;
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

public class SuppliersTests {
    String token;
    String baqseURL = "http://staging.eservia.com:8086/api/v1.0/suppliers/";
    String baseURLType = "http://staging.eservia.com:8086/api/v1.0/supplier-types/";
    String baseURLByBusiness = "http://staging.eservia.com:8086/api/v1.0/businesses/";
    public int id;
    int business_id ;
    int supplier_type_id;
    int supplier_type_id2;
    Faker faker = new Faker();
    String titleType = faker.name().firstName().toLowerCase();
    String title = faker.name().firstName().toLowerCase();
    String phone = faker.regexify("+380[0-9]{9}");
    String fax = faker.regexify("+0[0-9]{9}");
    String email = faker.name().lastName()+"@mail.com";
    String title2 = faker.name().firstName().toLowerCase();
    int inn = faker.hashCode();
    int planId;
    int promoterId;
    String address = faker.address().streetAddress();
    String comment = faker.gameOfThrones().dragon();
    String fax2 = faker.regexify("+0[0-9]{9}");
    String phone2 = faker.regexify("+380[0-9]{9}");
    String email2 = faker.name().lastName()+"@mail.com";
    int inn2 = faker.hashCode();
    String address2 = faker.address().streetAddress();
    String comment2 = faker.witcher().monster();
    SuppliersData suppliersData = new SuppliersData();
    SupplierTypesData supplierTypesData = new SupplierTypesData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.planId = getBusiness.returnPlan();
        this.promoterId = getBusiness.returnPromoter();

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(supplierTypesData.addSupplierType(titleType))
                .when().post(baseURLType).thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes addSupplierType = supplierTypesResponse.data;
        this.supplier_type_id = addSupplierType.getId();

        ResponseBody respons2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(supplierTypesData.addSupplierType(titleType))
                .when().post(baseURLType).thenReturn().body();
        SupplierTypesResponse supplierTypesResponse2 = new Gson().fromJson(respons2.asString(), SupplierTypesResponse.class);
        SupplierTypes addSupplierType2 = supplierTypesResponse2.data;
        this.supplier_type_id2 = addSupplierType2.getId();
    }
    @Test
    public void A_addSuppliers() {
        Suppliers addSupplier = suppliersData.addSuppliers(business_id, supplier_type_id, title, phone, fax, email, inn, address, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSupplier)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baqseURL).thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response.asString(), SuppliersResponse.class);
        Suppliers addSuppl = suppliersResponse.data;
        this.id =addSuppl.getId();
        Assert.assertEquals(id, addSuppl.getId());
        Assert.assertEquals(business_id, addSuppl.getBusiness_id());
        Assert.assertEquals(supplier_type_id, addSuppl.getSupplier_type_id());
        Assert.assertEquals(title, addSuppl.getTitle());
        Assert.assertEquals(phone, addSuppl.getPhone());
        Assert.assertEquals(fax, addSuppl.getFax());
        Assert.assertEquals(email, addSuppl.getEmail());
        Assert.assertEquals(inn, addSuppl.getInn());
        Assert.assertEquals(address, addSuppl.getAddress());
        Assert.assertEquals(comment, addSuppl.getComment());
        Assert.assertEquals(true, addSuppl.getCreated_at().startsWith("2018"));
    }
    @Test
    public void B_getSuppliersById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baqseURL + id + "/").thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response.asString(), SuppliersResponse.class);
        Suppliers getSuppl = suppliersResponse.data;
        Assert.assertEquals(id, getSuppl.getId());
        Assert.assertEquals(business_id, getSuppl.getBusiness_id());
        Assert.assertEquals(supplier_type_id, getSuppl.getSupplier_type_id());
        Assert.assertEquals(title, getSuppl.getTitle());
        Assert.assertEquals(phone, getSuppl.getPhone());
        Assert.assertEquals(fax, getSuppl.getFax());
        Assert.assertEquals(email, getSuppl.getEmail());
        Assert.assertEquals(inn, getSuppl.getInn());
        Assert.assertEquals(address, getSuppl.getAddress());
        Assert.assertEquals(comment, getSuppl.getComment());
        Assert.assertEquals(true, getSuppl.getCreated_at().startsWith("2018"));
    }
    @Test
    public void C_updateSuppliers() {
        Suppliers updateSupplier = suppliersData.updateSuppliers(supplier_type_id2, title2, phone2, fax2, email2, inn2, address2, comment2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateSupplier)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baqseURL + id + "/").thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response.asString(), SuppliersResponse.class);
        Suppliers updateSuppl = suppliersResponse.data;
        Assert.assertEquals(supplier_type_id2, updateSuppl.getSupplier_type_id());
        Assert.assertEquals(title2, updateSuppl.getTitle());
        Assert.assertEquals(phone2, updateSuppl.getPhone());
        Assert.assertEquals(fax2, updateSuppl.getFax());
        Assert.assertEquals(email2, updateSuppl.getEmail());
        Assert.assertEquals(inn2, updateSuppl.getInn());
        Assert.assertEquals(address2, updateSuppl.getAddress());
        Assert.assertEquals(comment2, updateSuppl.getComment());
        Assert.assertEquals(true, updateSuppl.getUpdated_at().contains("2018"));
    }
    @Test
    public void E_getSuppliersByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baqseURL +"?supplier_type_id="+supplier_type_id2 + "&sort=-id").thenReturn().body();
        SuppliersResponseArray suppliersResponseArray = new Gson().fromJson(response.asString(), SuppliersResponseArray.class);
        Suppliers suppliersQuery =  suppliersResponseArray.data.get(0);
        Assert.assertEquals(supplier_type_id2, suppliersQuery.getSupplier_type_id());
    }
    @Test
    public void F_getSuppliersByBusiness() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLByBusiness + business_id + "/suppliers/").thenReturn().body();
        SuppliersResponseArray suppliersResponseArray = new Gson().fromJson(response.asString(), SuppliersResponseArray.class);
        Suppliers suppliersBusiness =  suppliersResponseArray.data.get(0);
        Assert.assertEquals(business_id, suppliersBusiness.getBusiness_id());
    }
    @Test
    public void G_deleteSuppliers() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baqseURL + id + "/").thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response.asString(), SuppliersResponse.class);
        Suppliers deleteSuppl = suppliersResponse.data;
        Assert.assertEquals(id, deleteSuppl.getId());
        Assert.assertEquals(true, deleteSuppl.getDeleted_at().contains("2018"));
    }
    @AfterClass
    public void deleteBefore() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + business_id + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        this.business_id = businesses.getId();

        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLType + supplier_type_id + "/").thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response2.asString(), SupplierTypesResponse.class);
        SupplierTypes deleteSupplierType = supplierTypesResponse.data;
        Assert.assertEquals(supplier_type_id, deleteSupplierType.getId());

        ResponseBody response3 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLType + supplier_type_id2 + "/").thenReturn().body();
        SupplierTypesResponse supplierTypesResponse2 = new Gson().fromJson(response3.asString(), SupplierTypesResponse.class);
        SupplierTypes deleteSupplierType2 = supplierTypesResponse2.data;
        Assert.assertEquals(supplier_type_id2, deleteSupplierType2.getId());
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter()).when()
                .delete("http://213.136.86.27:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();
        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }
}
