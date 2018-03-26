package Departments;

import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static com.jayway.restassured.RestAssured.given;

public class DepartmentTest {

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjA1MDMxMSwibmJmIjoxNTIyMDUwMzExLCJwcm9tb3Rlcl9pZCI6IjI4IiwiZXhwIjoxNTIyMTM2NzExLCJidXNpbmVzc2VzIjpbeyJpZCI6MzksImFkZHJlc3NlcyI6WzEyMSwxMzMsMTUzLDE4NCwxODUsMTg2LDE4N119XX0.C5Jf4mhV-eXyXMlUkIlVmMCqlGWLX3nppBVDSQUyBcYB6hU3zrSDboTdtXdonBzUaMLgcQXCm5ITGJEizf8V_56JpKYOdVoVdQ4RKmhpUhWYQiyBqLED0FreG9MClZuJM4sEmjn9gu4PBSbWq6h6jQuKR0j11VPCE73r8j-QymkE_pneFUuPn6lkxU7us3Aor2oDxg5Fwif7nyArHEmMmD2-gXRqpSCw-zl2bN9d9y3rbJUEpFrZLnZX5S4kXXOWGMxNBcnE5cvekLphkWRrf-Zd0Qfa-5YFL0vzcirvnV02J9KwasWt91ZOnv5Er6jE70awM61nFIsp1akRn51nLg";
    private  String baseURL = "http://staging.eservia.com:8009/api/v0.0/Departments";
    String name = (LocalTime.now()).toString();
    int Ids;
    DepartmentData departmentsData = new DepartmentData();

    @Test
    public void A_GreateDepartment() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(departmentsData.CreatePreparingDepartments(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentResponse.class);
        Department department = departmentResponse.data;
        this.Ids = department.getId();
        Assert.assertEquals(1,department.getTypeId());
        Assert.assertEquals(false,department.isMain());
        Assert.assertEquals(true,department.isActive());
        Assert.assertEquals(121,department.getAddressId());
        Assert.assertEquals(name,department.getName());
    }
    @Test
    public void B_UpdateDepartment() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(departmentsData.UpdateDepartment(name,Ids))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL).thenReturn().body();
        DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentResponse.class);
        Department department = departmentResponse.data;
        this.Ids = department.getId();
        Assert.assertEquals(2,department.getTypeId());
        Assert.assertEquals(false,department.isMain());
        Assert.assertEquals(true,department.isActive());
        Assert.assertEquals(121,department.getAddressId());
        Assert.assertEquals(name,department.getName());
    }
    @Test
    public void C_deactivateDepartment(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+"/"+Ids+"/Deactivate");
        DepartmentErrors departmentResponse = new Gson().fromJson(response.asString(),  DepartmentErrors.class);
        Assert.assertEquals(true,departmentResponse.isSuccess());
        Assert.assertEquals(true,departmentResponse.isData());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        DepartmentResponse departmentResponse1 = new Gson().fromJson(responseBody.asString(),DepartmentResponse.class);
        Department department = departmentResponse1.data;
        Assert.assertEquals(false,department.isActive());
    }
    @Test
    public void D_activateDepartment(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL+"/"+Ids+"/Activate");
        DepartmentErrors departmentResponse = new Gson().fromJson(response.asString(),  DepartmentErrors.class);
        Assert.assertEquals(true,departmentResponse.isSuccess());
        Assert.assertEquals(true,departmentResponse.isData());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        DepartmentResponse departmentResponse1 = new Gson().fromJson(responseBody.asString(),DepartmentResponse.class);
        Department department = departmentResponse1.data;
        Assert.assertEquals(true,department.isActive());
    }

}
