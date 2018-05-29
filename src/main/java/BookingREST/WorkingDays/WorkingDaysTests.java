package BookingREST.WorkingDays;

import BookingREST.AuthBusiness.AuthBusinessTest;
import org.testng.annotations.BeforeClass;

public class WorkingDaysTests {
    String token;
    String baseURL = "http://213.136.86.27:8083/api/v1.0/working-days/";
    public int id;
    int business_id;
    int object_id;

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
    }
}
