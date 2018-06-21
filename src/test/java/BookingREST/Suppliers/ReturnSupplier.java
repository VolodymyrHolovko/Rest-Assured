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

import static com.jayway.restassured.RestAssured.given;

public class ReturnSupplier {
    public String token;
    String baqseURL = "http://213.136.86.27:8086/api/v1.0/suppliers/";
    String baseURLType = "http://213.136.86.27:8086/api/v1.0/supplier-types/";
    public int id;
    public int business_id;
    int supplier_type_id;
    Faker faker = new Faker();
    String titleType = faker.name().firstName().toLowerCase();
    String title = faker.name().firstName().toLowerCase();
    String phone = faker.regexify("+380[0-9]{9}");
    String fax = faker.regexify("+0[0-9]{9}");
    String email = faker.name().lastName()+"@mail.com";
    String comment = faker.gameOfThrones().dragon();
    String address = faker.address().streetAddress();
    int inn = faker.hashCode();
    SuppliersData suppliersData = new SuppliersData();
    SupplierTypesData supplierTypesData = new SupplierTypesData();



    public int ReturnSupplier(int business_id) {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();


        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(supplierTypesData.addSupplierType(titleType))
                .when().post(baseURLType).thenReturn().body();
        SupplierTypesResponse supplierTypesResponse = new Gson().fromJson(response.asString(), SupplierTypesResponse.class);
        SupplierTypes addSupplierType = supplierTypesResponse.data;
        this.supplier_type_id = addSupplierType.getId();

        Suppliers addSupplier = suppliersData.addSuppliers(business_id, supplier_type_id, title, phone, fax, email, inn, address, comment);
        ResponseBody response2 = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addSupplier)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baqseURL).thenReturn().body();
        SuppliersResponse suppliersResponse = new Gson().fromJson(response2.asString(), SuppliersResponse.class);
        Suppliers addSuppl = suppliersResponse.data;
        this.id =addSuppl.getId();
        return id;
    }
}
