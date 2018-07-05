package BookingREST.Warehouse;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Staffs.Staff;
import BookingREST.Staffs.StaffResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class ReturnWarehouse {
    String token;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/warehouses/";
    Faker faker = new Faker();
    String title = faker.name().firstName().toLowerCase();
    public int id;
    public int business_id;
    public int address_id;
    public int responsible_id;
    WarehouseData warehouseData = new WarehouseData();

    public int ReturnWarehouse(int business_id, int address_id, int responsible_id) {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();
        Warehouse warehouseAdd = warehouseData.addWarehouses(business_id, address_id, responsible_id, title);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(warehouseAdd)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        WarehouseResponse warehouseResponse = new Gson().fromJson(response.asString(), WarehouseResponse.class);
        Warehouse addWarhoses = warehouseResponse.data;
        this.id = addWarhoses.getId();
        return id;
    }
}
