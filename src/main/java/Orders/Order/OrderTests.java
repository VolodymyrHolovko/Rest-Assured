package Orders.Order;

import Auth.GetToken;
import Departments.Department;
import Departments.DepartmentData;
import Departments.DepartmentResponse;
import Departments.Tables.Tables;
import Departments.Tables.TablesData;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.given;

public class OrderTests {
    public class DepartmentTest {
        private String baseURL = "http://staging.eservia.com:8009/api/v0.0/Departments";
        String name = (LocalTime.now()).toString();
        int Ids;
        DepartmentData departmentsData = new DepartmentData();
        TablesData tablesData = new TablesData();
        public String token;

        @BeforeClass
        public void setup() {
            GetToken getToken = new GetToken();
            this.token = getToken.GetFinallyToken();

            ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).body(departmentsData.CreatePreparingDepartments(name)).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().post(baseURL).thenReturn().body();
            DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(), DepartmentResponse.class);
            Department department = departmentResponse.data;
            this.Ids = department.getId();


        }
    }
}
