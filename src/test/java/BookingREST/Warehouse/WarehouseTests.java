package BookingREST.Warehouse;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import BookingREST.Staffs.Staff;
import BookingREST.Staffs.StaffData;
import BookingREST.Staffs.StaffResponse;
import BookingREST.Staffs.StaffTests;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class WarehouseTests {
    String token;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/warehouses/";
    String baseURLStaff = "http://213.136.86.27:8084/api/v1.0/staffs/";
    String baseUrlByBusiness = "http://213.136.86.27:8086/api/v1.0/businesses/";
    public int id;
    int business_id;
    int address_id;
    int responsible_id;
    int responsible_id2;
    String warehouseQuery = "?address_id=";
    Faker faker = new Faker();
    int planId;
    int promoterId;
    String title = faker.name().firstName().toLowerCase();
    String title2 = faker.name().firstName().toLowerCase();
    String email = faker.name().firstName()+"@mail.com"+"a";
    String phone = faker.regexify("+380[0-9]{9}");
    WarehouseData warehouseData = new WarehouseData();
    StaffData staffData = new StaffData();

    @org.testng.annotations.BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();
        this.planId = getBusiness.returnPlan();
        this.promoterId = getBusiness.returnPromoter();
        ResponseBody staffsees = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(staffData.createStaff(business_id,address_id,phone,email))
                .when().post(baseURLStaff).thenReturn().body();
        StaffResponse staffResponse = new Gson().fromJson(staffsees.asString(), StaffResponse.class);
        Staff staff = staffResponse.getData();
        this.responsible_id2 = staff.getId();
    }

    @Test
     public void A_createWarehouses() {
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
        Assert.assertEquals(id, addWarhoses.getId());
        Assert.assertEquals(business_id, addWarhoses.getBusiness_id());
        Assert.assertEquals(address_id, addWarhoses.getAddress_id());
        Assert.assertEquals(responsible_id, addWarhoses.getResponsible_id());
        Assert.assertEquals(warehouseAdd.getTitle(),warehouseAdd.getTitle());
        Assert.assertEquals(true, addWarhoses.getCreated_at().startsWith("2018"));
        Assert.assertEquals(true, addWarhoses.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void B_getWarehouseById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        WarehouseResponse warehouseResponse = new Gson().fromJson(response.asString(), WarehouseResponse.class);
        Warehouse getWarhoses = warehouseResponse.data;
        Assert.assertEquals(id, getWarhoses.getId());
        Assert.assertEquals(business_id, getWarhoses.getBusiness_id());
        Assert.assertEquals(address_id, getWarhoses.getAddress_id());
        Assert.assertEquals(responsible_id, getWarhoses.getResponsible_id());
        Assert.assertEquals(title,getWarhoses.getTitle());
        Assert.assertEquals(true, getWarhoses.getCreated_at().startsWith("2018"));
        Assert.assertEquals(true, getWarhoses.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void C_updateWarehouse() {
        Warehouse warehouseUpdate = warehouseData.updateWarehouses(responsible_id2, title2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(warehouseUpdate)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        WarehouseResponse warehouseResponse = new Gson().fromJson(response.asString(), WarehouseResponse.class);
        Warehouse updateWarhoses = warehouseResponse.data;
        Assert.assertEquals(responsible_id2, updateWarhoses.getResponsible_id());
        Assert.assertEquals(title2, updateWarhoses.getTitle());
        Assert.assertEquals(true, updateWarhoses.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void D_getWarehouseByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + warehouseQuery + address_id).thenReturn().body();
        WarehouseResponseArray warehouseResponseArray = new Gson().fromJson(response.asString(), WarehouseResponseArray.class);
        Warehouse getByQuery = warehouseResponseArray.data.get(0);
        Assert.assertEquals(address_id, getByQuery.getAddress_id());
    }
    @Test
    public void E_getWarehouseByBusinessId() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrlByBusiness + business_id + "/warehouses/").thenReturn().body();
        WarehouseResponseArray warehouseResponseArray = new Gson().fromJson(response.asString(), WarehouseResponseArray.class);
        Warehouse getByBusiness = warehouseResponseArray.data.get(0);
        Assert.assertEquals(business_id, getByBusiness.getBusiness_id());
    }
    @Test
    public void F_deleteWarehouses() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        WarehouseResponse warehouseResponse = new Gson().fromJson(response.asString(), WarehouseResponse.class);
        Warehouse deleteWarhoses = warehouseResponse.data;
        Assert.assertEquals(id, deleteWarhoses.getId());
        Assert.assertEquals(true, deleteWarhoses.getDeleted_at().startsWith("2018"));
    }
    @AfterClass
    public void deleteBefore(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + business_id + "/").thenReturn().body();
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();
        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }

}
