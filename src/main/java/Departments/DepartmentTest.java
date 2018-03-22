package Departments;

import token.GetToken;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.given;

public class DepartmentResponseTest {

    String token = "";
    private final String baseURL = "http://cluster.test.eservia.com/api/v0.0";
    GetToken auth = new GetToken();
    DepartmentData departmentsData = new DepartmentData();

    @BeforeClass
    public void GetToken() {
        this.token=auth.GetToken();
    }
    @Test
    public void GetDepartments() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", 1).body(departmentsData.allDepartments()).when().post(baseURL+"/Departments").thenReturn().body();
    }
}
