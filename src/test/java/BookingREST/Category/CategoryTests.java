package BookingREST.Category;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessData;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Promoter.Promoter;
import BookingREST.Promoter.PromoterData;
import BookingREST.Promoter.PromoterResponse;
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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class CategoryTests {
String token;
String baseURL = "http://staging.eservia.com:8083/api/v1.0/categories/";
String baseUrlSector = "http://staging.eservia.com:8083/api/v1.0/sectors/";
String baseURIStrategy = "http://staging.eservia.com:8083/api/v1.0/strategies/";
String baseURLPromoter = "http://staging.eservia.com:8083/api/v1.0/promoters/";
String baseURLBusiness = "http://staging.eservia.com:8083/api/v1.0/businesses/";
Faker faker = new Faker();
public int id;
char rc = (char)('A' + new Random().nextInt(26));

String category = (faker.name().nameWithMiddle().toLowerCase()+faker.name().firstName()+rc).toLowerCase();
String sectorName = faker.name().username().toLowerCase()+faker.name().firstName().toLowerCase()+rc;
String name = faker.name().fullName().toLowerCase()+faker.name().firstName().toLowerCase()+rc;
String enName = faker.name().title().toLowerCase()+faker.name().firstName().toLowerCase()+rc;
String enNameUpdate = faker.name().title().toLowerCase()+faker.name().firstName().toLowerCase()+rc;
String alias = faker.name().firstName()+faker.name().firstName().toLowerCase()+rc;
String firstName = faker.name().firstName()+faker.name().firstName().toLowerCase()+rc;
String lastName = faker.name().username().toLowerCase()+faker.name().firstName().toLowerCase()+rc;
String email = faker.name().firstName().hashCode()+faker.name().firstName().toLowerCase()+rc+"@mail.com";
String phone = faker.regexify("+380[0-9]{9}");
int promoterId;
int sector_id;
int strategy_id;
int businessId;
int categoryToBusinessId;
CategoryData categoryData = new CategoryData();
SectorData sectorData = new SectorData();
StrategyData strategyData = new StrategyData();
BusinesessData businesessData = new BusinesessData();
PromoterData promoterData = new PromoterData();

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

        ResponseBody responsePromoter = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.addPromoters(firstName, lastName, email, phone))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLPromoter).thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(responsePromoter.asString(), PromoterResponse.class);
        Promoter addPromoter = promoterResponse.getData();
        this.promoterId = addPromoter.getId();

        ResponseBody responseBusiness = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.createBusinesses(promoterId,strategy_id,sector_id,alias))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLBusiness).thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(responseBusiness.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();
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

    @Test
    public void E_getCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL + "?strategy_id=" + strategy_id).thenReturn().body();
        CategoryBusinessResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryBusinessResponse.class);
        Category getCategory = categoryResponse.data.get(0);
        Assert.assertEquals(category, getCategory.getCategory());
        Assert.assertEquals(sector_id, getCategory.getSector_id());
        Assert.assertEquals(strategy_id, getCategory.getStrategy_id());
        Assert.assertEquals(enNameUpdate, getCategory.getName_en());
        Assert.assertEquals("раша-па*аша", getCategory.getName_ru());
        Assert.assertEquals("категорія оновлена", getCategory.getName_uk());
        Assert.assertEquals(1, getCategory.getStatus());

    }

    @Test
    public void F_addBusinessIdCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(categoryData.addCategoryToBusinessId(id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURLBusiness + businessId + "/" + "categories/").thenReturn().body();
        CategoryBusinessResponse categoryBusinessResponse = new Gson().fromJson(response.asString(), CategoryBusinessResponse.class);
        Category addCategoryToBusiness = categoryBusinessResponse.data.get(0);
        this.categoryToBusinessId = addCategoryToBusiness.getId();
        Assert.assertEquals(categoryToBusinessId, addCategoryToBusiness.getId());
        Assert.assertEquals(businessId, addCategoryToBusiness.getBusiness_id());
        Assert.assertEquals(id, addCategoryToBusiness.getCategory_id());
    }

    @Test
    public void G_getBusinessIdCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLBusiness + businessId + "/" + "categories/").thenReturn().body();
        CategoryBusinessResponse categoryBusinessResponse = new Gson().fromJson(response.asString(), CategoryBusinessResponse.class);
        Category getCategoryToBusiness = categoryBusinessResponse.data.get(0);
        Assert.assertEquals(id, getCategoryToBusiness.getId());
        Assert.assertEquals(category, getCategoryToBusiness.getCategory());
        Assert.assertEquals(sector_id, getCategoryToBusiness.getSector_id());
        Assert.assertEquals(strategy_id, getCategoryToBusiness.getStrategy_id());
        Assert.assertEquals(enNameUpdate, getCategoryToBusiness.getName_en());
        Assert.assertEquals("раша-па*аша", getCategoryToBusiness.getName_ru());
        Assert.assertEquals("категорія оновлена", getCategoryToBusiness.getName_uk());
        Assert.assertEquals(1, getCategoryToBusiness.getStatus());
        Assert.assertEquals(true, getCategoryToBusiness.getCreated_at().startsWith("2018"));
        Assert.assertEquals(true, getCategoryToBusiness.getUpdated_at().startsWith("2018"));
    }

    @Test
    public void H_getBusinessIdCategoryRelations() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLBusiness + businessId + "/" + "categories/relations/").thenReturn().body();
        CategoryBusinessResponse categoryBusinessResponse = new Gson().fromJson(response.asString(), CategoryBusinessResponse.class);
        Category getCategoryToBusinessRelations = categoryBusinessResponse.data.get(0);
        this.categoryToBusinessId = getCategoryToBusinessRelations.getId();
        Assert.assertEquals(categoryToBusinessId, getCategoryToBusinessRelations.getId());
        Assert.assertEquals(businessId, getCategoryToBusinessRelations.getBusiness_id());
        Assert.assertEquals(id, getCategoryToBusinessRelations.getCategory_id());
    }

    @Test
    public void I_deleteBusinessIdCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(categoryData.addCategoryToBusinessId(id))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLBusiness + businessId + "/" + "categories/").thenReturn().body();
        CategoryBusinessResponse categoryBusinessResponse = new Gson().fromJson(response.asString(), CategoryBusinessResponse.class);
        Category deleteCategoryToBusiness = categoryBusinessResponse.data.get(0);
        Assert.assertEquals(categoryToBusinessId, deleteCategoryToBusiness.getId());
        Assert.assertEquals(id, deleteCategoryToBusiness.getCategory_id());

        ResponseBody responseget = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURLBusiness + businessId + "/" + "categories/").thenReturn().body();
        CategoryBusinessResponse categoryResponse = new Gson().fromJson(responseget.asString(), CategoryBusinessResponse.class);
        int getDeleted = categoryResponse.data.size();
        System.out.println(getDeleted);
        Assert.assertEquals(0, getDeleted);
    }

    @Test
    public void J_deactivateCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURL + id + "/" + "deactivate/").thenReturn().body();
        CategoryResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryResponse.class);
        Category deactivateCategory = categoryResponse.data;
        Assert.assertEquals(0, deactivateCategory.getStatus());
    }

    @Test
    public void K_deleteCategory() {
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL + id + "/").thenReturn().body();
        CategoryResponse categoryResponse = new Gson().fromJson(response.asString(), CategoryResponse.class);
        Category deletedCategory = categoryResponse.data;
        Assert.assertEquals(true, deletedCategory.getDeleted_at().startsWith("2018"));
    }

    @AfterClass
    public void deleteAllFromBeforeClass() {
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.updateSector())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrlSector+sector_id+"/").thenReturn().body();
        System.out.println(respons.asString());

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURIStrategy + strategy_id + "/").thenReturn().body();
        System.out.println(response.asString());

        ResponseBody responseprom = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLPromoter + promoterId + "/").thenReturn().body();
        System.out.println(responseprom.asString());

        ResponseBody responseBusin = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURLBusiness + businessId + "/").thenReturn().body();
        System.out.println(responseBusin);
    }
}
