package Departments;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class DepartmentTest {
    String token = "earer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjE1NDA5MywibmJmIjoxNTIyMTU0MDkzLCJwcm9tb3Rlcl9pZCI6IjI4IiwiZXhwIjoxNTIyMjQwNDkzLCJidXNpbmVzc2VzIjpbeyJpZCI6MzksImFkZHJlc3NlcyI6WzEyMSwxMzMsMTUzLDE4NCwxODVdfV19.STOf2RHU42XW65jcINc0NL7IPowG0yhU1YXqRznbBrhUGR25t6PF-mD5Ey-3MtjX5OQAxaa_7458BHQu3Iql5SMnRItWwJkIQ7B4YEEtusbE4Q06eCVtNvfU8VKb6gAK492SMYN1Oj7JJ1h8pJF4j2uJLXKeL_uwKG7ddgSyrRSmH2zUzQHlydhUwUCQa8U7ieQC6f4GQuQG_jnVp3FucxSaH05OZgsSJvlWBK5VHIEQqw49DB_djF0oIMQFlYE5M_gkW-Eym2eREtgDXp_DMJ5xs9ie86xE2sJarf6iLytiqnqMZvcEBu5f532SzXHwM3n3eFEypgBJ6YUHQwF5Pg";
    private  String baseURL = "http://staging.eservia.com:8009/api/v0.0/Departments";
    String name = (LocalTime.now()).toString();
    int Ids;
    DepartmentData departmentsData = new DepartmentData();

    @Test
    public int A_GreateDepartment() {
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
        return Ids;

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

    @Test
    public void F_DeleteDepartment(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+"/"+Ids);
        DepartmentErrors departmentResponse = new Gson().fromJson(response.asString(),  DepartmentErrors.class);
        Assert.assertEquals(true,departmentResponse.isSuccess());
        Assert.assertEquals(true,departmentResponse.isData());

        ResponseBody responseBody = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+"/"+Ids);
        DepartmentErrors departmentResponse1 = new Gson().fromJson(responseBody.asString(),DepartmentErrors.class);
        Department department = departmentResponse1.error;
        Assert.assertEquals("Department does not exist",department.getErrorDescription());
        Assert.assertEquals("DepartmentsService.Service",department.getErrorSource());
    }

    @Test
    public void G_CheckSellingDepartment(){
        String CheckSelling = "http://staging.eservia.com:8009/api/v0.0/Departments/Selling?addressId=23";
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(CheckSelling);
        DepartmentListResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentListResponse.class);


        for (Department department : departmentResponse.getData()) {
            Assert.assertEquals(2, department.getTypeId());
            Assert.assertEquals(23, department.getAddressId());
        }
    }

}
