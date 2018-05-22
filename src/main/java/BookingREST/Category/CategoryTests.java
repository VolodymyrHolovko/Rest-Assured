package BookingREST.Category;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Sector.Sector;
import BookingREST.Sector.SectorData;
import BookingREST.Sector.SectorResponse;
import BookingREST.Strategy.Strategy;
import BookingREST.Strategy.StrategyData;
import BookingREST.Strategy.StrategyResponse;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.baseURI;
import static com.jayway.restassured.RestAssured.given;

public class CategoryTests {
String token;
String baseURL = "http://213.136.86.27:8083/api/v1.0/categories/";
String baseUrlSector = "http://213.136.86.27:8083/api/v1.0/sectors/";
String baseURIStrategy = "http://213.136.86.27:8083/api/v1.0/strategies/";
Faker faker = new Faker();
public int id;
String category = faker.name().firstName().toLowerCase();
String sectorName = faker.name().firstName().toLowerCase();
String name = faker.name().firstName().toLowerCase();
String enName = faker.name().title().toLowerCase();
String enNameUpdate = faker.name().title().toLowerCase();
int sector_id;
int strategy_id;
CategoryData categoryData = new CategoryData();
SectorData sectorData = new SectorData();
StrategyData strategyData = new StrategyData();

    @BeforeClass
    public void getToken() {
    AuthBusinessTest getToken = new AuthBusinessTest();
    this.token = getToken.GetAdminToken();

        ResponseBody responseSector = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.createSector(sectorName))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrlSector).thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(responseSector.asString(), SectorResponse.class);
        Sector sector = sectorResponse.data;
        this.sector_id =sector.getId();

        ResponseBody responseStrategy = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(strategyData.addPromoters(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURIStrategy).thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(responseStrategy.asString(), StrategyResponse.class);
        Strategy addStrategy = strategyResponse.data;
        this.strategy_id = addStrategy.getId();
}
    @Test
    public void A_createCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(categoryData.addCategories(category, sector_id, strategy_id, enName))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        CategoryResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryResponse.class);
        Category addCategory = categoryResponse.data;
        this.id = addCategory.getId();
        System.out.println(response.asString());
        Assert.assertEquals(category, addCategory.getCategory());
        Assert.assertEquals(sector_id, addCategory.getSector_id());
        Assert.assertEquals(strategy_id, addCategory.getStrategy_id());
        Assert.assertEquals(enName, addCategory.getName_en());
        Assert.assertEquals("раша", addCategory.getName_ru());
        Assert.assertEquals("категорія", addCategory.getName_uk());
        Assert.assertEquals(0, addCategory.getStatus());
        Assert.assertEquals(true, addCategory.getCreated_at().startsWith("2018"));
    }
    @Test
    public void B_updateCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(categoryData.updateCategories(enNameUpdate))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL + id +"/").thenReturn().body();
        CategoryResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryResponse.class);
        Category updateCategory = categoryResponse.data;
        Assert.assertEquals(enNameUpdate, updateCategory.getName_en());
        Assert.assertEquals("раша-па*аша", updateCategory.getName_ru());
        Assert.assertEquals("категорія оновлена", updateCategory.getName_uk());
        Assert.assertEquals(0, updateCategory.getStatus());
        Assert.assertEquals(true, updateCategory.getUpdated_at().startsWith("2018"));
    }
    @Test
    public void C_activateCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL + id + "/" + "activate/").thenReturn().body();
        CategoryResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryResponse.class);
        Category activateCategory = categoryResponse.data;
        Assert.assertEquals(1, activateCategory.getStatus());
    }
    @Test
    public void D_getCategoryId() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + id + "/").thenReturn().body();
        CategoryResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryResponse.class);
        Category getCategory = categoryResponse.data;
        Assert.assertEquals(category, getCategory.getCategory());
        Assert.assertEquals(sector_id, getCategory.getSector_id());
        Assert.assertEquals(strategy_id, getCategory.getStrategy_id());
        Assert.assertEquals(enNameUpdate, getCategory.getName_en());
        Assert.assertEquals("раша-па*аша", getCategory.getName_ru());
        Assert.assertEquals("категорія оновлена", getCategory.getName_uk());
        Assert.assertEquals(1, getCategory.getStatus());
        Assert.assertEquals(true, getCategory.getCreated_at().startsWith("2018"));
        Assert.assertEquals(true, getCategory.getUpdated_at().startsWith("2018"));
    }
    
}
