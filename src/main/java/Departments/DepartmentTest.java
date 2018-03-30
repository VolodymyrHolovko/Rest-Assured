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
    String token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIyZGQ1MTY2ZS0xMGY2LTQ2N2YtYWFlMy0wZjEyYjBhNzZhODgiLCJwcm9tb3RlcklkIjoiMTAiLCJwb3NpdGlvbklkIjoiMSIsImFjY2Vzc1JpZ2h0cyI6ImVtcGxveWVlOnJlYWQsY3JlYXRlLHVwZGF0ZXxvcmRlcjpyZWFkLGNyZWF0ZSx1cGRhdGV8cG9zaXRpb246cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxwcmVzZW5jZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHNoaWZ0VHlwZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHdvcmtTaGlmdDpyZWFkfGRldmljZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGRpbWVuc2lvbjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG1lcmNoYW50OnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8cHJvdmlkZXI6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXx0YWJsZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRhZzpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRheGU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxkZXBhcnRtZW50OnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8ZGVwYXJ0bWVudEdyb3VwOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8bWFya2V0aW5nOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8c3RvcmFnZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRlY2hDYXJkOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8Y2F0ZWdvcnk6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxub21lbmNsYXR1cmU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxub21lbmNsYXR1cmVHcm91cDpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG9wdGlvbjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG9wdGlvbkdyb3VwOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8c2l6ZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHNjYWxlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8dGltZVJhbmdlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8ZGlyZWN0aXZlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8bWVudTpyZWFkLGNyZWF0ZXxjb29raW5nVHlwZXM6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxib29raW5nOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8Ym9va2luZ1NldHRpbmdzOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8Y3VzdG9tZXI6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZSIsImJ1c2luZXNzZXMiOiJ7XCJidXNpbmVzc2VzXCI6W3tcImlkXCI6MTcsXCJhZGRyZXNzZXNcIjpbMjMsMjRdfSx7XCJpZFwiOjU4LFwiYWRkcmVzc2VzXCI6WzE1Ml19XX0iLCJuYmYiOjE1MjIzOTIzMzAsImV4cCI6MTUyMjQ0MjczMCwiaWF0IjoxNTIyMzkyMzMwLCJpc3MiOiJQZXJzb25uZWxTZXJ2aWNlIiwiYXVkIjoiUGVyc29ubmVsU2VydmljZSJ9.MvSOjitQJ8F__XyFvJXs0o-X69vuoJ3_-fblGPziR3-GT4pRsFHxQ21wwk1hFArlMP7tfICiDxIcxtk6OhDlbctET1B_w-GUD1rxDmqsOuZVsH-WLRW8zlozTHMRdIKFXX9sCUuWBWZnESfCQ6GV7zE7TC47qoR54dnDnH1I_VyVvFAKgqY_iVvPoXo-JkcaiUup2eXQGlnNrMj89AU3zFvZtl2D951SBIg9nLRooDrwq-qotix0P73TapWIER_RWl9-p-wrHkELZ8PWNiVKREh8_6FzLhafdRgDFSC4JLLNVT6fVnoPfUWocl6cVsdN4p77SHZmkZTlcNIHvOzB2A";
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
        Assert.assertEquals(23,department.getAddressId());
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
        Assert.assertEquals(23,department.getAddressId());
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
    public int getId(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(departmentsData.CreatePreparingDepartments(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        DepartmentResponse departmentResponse = new Gson().fromJson(response.asString(),  DepartmentResponse.class);
        Department department = departmentResponse.data;
        int id = department.getId();
        return  id;
    }

}
