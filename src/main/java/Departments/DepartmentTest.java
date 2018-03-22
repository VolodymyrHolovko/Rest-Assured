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

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMTcwNDc5MywibmJmIjoxNTIxNzA0NzkzLCJwcm9tb3Rlcl9pZCI6IjI4IiwiZXhwIjoxNTIxNzkxMTkzLCJidXNpbmVzc2VzIjpbeyJpZCI6MzksImFkZHJlc3NlcyI6WzEyMSwxMzMsMTUzLDE4NCwxODUsMTg2LDE4N119XX0.BfzkLkrn9ZoGcfWRNgu6cTrBiYCwN0o_Yxn8KfqYsNXwghKTqUMmegeSpcvrWlxB97JvEgL33qT9NlRSLjVBg9wxhWpPRHig9Cr9gSS46Ehta8E9zgs7JRRzBmabv-t9v3UpJqJcJyk1q5LppL8Z7OKUFjbMHI_2w6-tP1Xfy0_biL-iq7qOrNx9Mz1-ONBOOmvE2HKe2D69zvW0XWUWG2NQk_V8iEpU5FC8HiZo1ySolf1KIa6lAOkbBPbJY0-GJjzx6ENGgznoljLZu1THiPY1Ai6221FPHlX288FN_gYF6Kgz7tqR3rurpH28cwctcxVWz_zF5dd3CHlDWT9y1g";
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
        Assert.assertEquals(true,department.isMain());
        Assert.assertEquals(true,department.isActive());
        Assert.assertEquals(121,department.getAddressId());
        Assert.assertEquals(name,department.getName());
    }
}
