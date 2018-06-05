package BookingREST.Warehouse;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import org.junit.BeforeClass;

public class WarehouseTests {
    String token;
    String baseURL = "http://213.136.86.27:8086/api/v1.0/warehouses/";
    public int id;
    int business_id;
    int address_id;
    int responsible_id;
    WarehouseData warehouseData = new WarehouseData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        CreateBusiness getBusiness = new CreateBusiness();
        this.business_id = getBusiness.validBusiness();
        this.address_id = getBusiness.A_returnAdressId();
        this.responsible_id = getBusiness.B_returnStaff();
    }
}
