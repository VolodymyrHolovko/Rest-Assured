package BookingREST.Units;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.CreateBusiness;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class UnitsTests {
    int id;
    String token;
    Faker faker = new Faker();
    String name = faker.name().title().toLowerCase();
    String abbr = faker.book().author().toLowerCase();
    String name2 = faker.name().title().toLowerCase();
    String abbr2 = faker.book().author().toLowerCase();
    String baseURL = "http://213.136.86.27:8086/api/v1.0/units/";
    UnitsData unitsData = new UnitsData();

    @BeforeClass
    public void getToken() {
        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();
    }
    @Test
    public void A_addUnits() {
        Units addUnitt = unitsData.addUnits(name, abbr);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(addUnitt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        UnitsRespponse unitsRespponse = new Gson().fromJson(response.asString(), UnitsRespponse.class);
        Units addUnitss = unitsRespponse.data;
        this.id = addUnitss.getId();
        Assert.assertEquals(name, addUnitss.getName());
        Assert.assertEquals(abbr, addUnitss.getAbbr());
    }
    @Test
    public void B_getUnitsById() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        UnitsRespponse unitsRespponse = new Gson().fromJson(response.asString(), UnitsRespponse.class);
        Units getById = unitsRespponse.data;
        Assert.assertEquals(id, getById.getId());
        Assert.assertEquals(name, getById.getName());
        Assert.assertEquals(abbr, getById.getAbbr());
    }
    @Test
    public void C_updateUnits() {
        Units updateUnitt = unitsData.updateUnits(name2, abbr2);
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateUnitt)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id + "/").thenReturn().body();
        UnitsRespponse unitsRespponse =  new Gson().fromJson(response.asString(), UnitsRespponse.class);
        Units updateUnitss = unitsRespponse.data;
        Assert.assertEquals(id, updateUnitss.getId());
        Assert.assertEquals(name2, updateUnitss.getName());
        Assert.assertEquals(abbr2, updateUnitss.getAbbr());
        Assert.assertEquals(true, updateUnitss.getUpdated_at().contains("2018"));
    }
    @Test
    public void D_getUnitsByQuery() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+ "?q=" + name2 + "&sort=-id").thenReturn().body();
        UnitsResponseArray unitsResponseArray = new Gson().fromJson(response.asString(), UnitsResponseArray.class);
        Units getByQuery = unitsResponseArray.data.get(0);
        Assert.assertEquals(name2, getByQuery.getName());
    }
    @Test
    public void E_deleteUnits() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        UnitsRespponse unitsRespponse =  new Gson().fromJson(response.asString(), UnitsRespponse.class);
        Units deleteUnitss = unitsRespponse.data;
        Assert.assertEquals(id, deleteUnitss.getId());
        Assert.assertEquals(true, deleteUnitss.getDeleted_at().contains("2018"));
    }

}
