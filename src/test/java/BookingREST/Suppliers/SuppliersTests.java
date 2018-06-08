package BookingREST.Suppliers;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Suppliers.SupplierTypes.SupplierTypes;
import BookingREST.Suppliers.SupplierTypes.SupplierTypesData;
import BookingREST.Suppliers.SupplierTypes.SupplierTypesResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class SuppliersTests {
    String token;
    String baqseURL = "http://213.136.86.27:8086/api/v1.0/suppliers/";
    String baseURLType = "http://213.136.86.27:8086/api/v1.0/supplier-types/";
    public int id;
    int business_id;
    int supplier_type_id;
    Faker faker = new Faker();
    String titleType = faker.name().firstName().toLowerCase();
    String title = faker.name().firstName().toLowerCase();
    String phone = faker.regexify("+380[0-9]{9}");
    String fax = faker.regexify("+380[0-9]{9}");
    String email = faker.name().lastName()+"@mail.com";
    String address = faker.address().streetAddress();
    String comment = faker.gameOfThrones().dragon();
    SuppliersData suppliersData = new SuppliersData();
    SupplierTypesData supplierTypesData = new SupplierTypesData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(supplierTypesData.addSupplierType(titleType))
                .when().post(baseURLType).thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes addSupplierType = supplierTypesResponse.data;
        this.supplier_type_id = addSupplierType.getId();
    }
    @Test
    public void A_addSupplierTypes() {
        Suppliers addSupplier = suppliersData.addSuppliers(business_id, supplier_type_id, title, phone, fax, email, address, comment);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSupplier)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baqseURL).thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response.asString(), SuppliersResponse.class);
        Suppliers addSuppl = suppliersResponse.data;
        System.out.println(response.asString());
    }
}
