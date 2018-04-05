package Nomenclature;

import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import token.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Type;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static java.lang.Integer.parseInt;

public class NomenclatureTests {
    String token = "";
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Nomenclature";
    public String ids;
    public String article;
    NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();
    NomenclaturePortionTestData nomenclaturePortionTestData = new NomenclaturePortionTestData();

    @BeforeClass
    public void getToken() {
        this.token="Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJiN2RhMmI4Mi1jMTlkLTRmN2MtYmRlMy00ODMyNzEyMDNmNmUiLCJwcm9tb3RlcklkIjoiMSIsInBvc2l0aW9uSWQiOiIxIiwiYWNjZXNzUmlnaHRzIjoiZW1wbG95ZWU6cmVhZCxjcmVhdGUsdXBkYXRlfG9yZGVyOnJlYWQsY3JlYXRlLHVwZGF0ZSxhc3NpZ258cG9zaXRpb246cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxwcmVzZW5jZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHNoaWZ0VHlwZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHdvcmtTaGlmdDpyZWFkfGRldmljZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGRpbWVuc2lvbjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG1lcmNoYW50OnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8cHJvdmlkZXI6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXx0YWJsZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRhZzpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRheDpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGRlcGFydG1lbnQ6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxkZXBhcnRtZW50R3JvdXA6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxtYXJrZXRpbmc6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxzdG9yYWdlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8dGVjaENhcmQ6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxjYXRlZ29yeTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG5vbWVuY2xhdHVyZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG5vbWVuY2xhdHVyZUdyb3VwOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8b3B0aW9uOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8b3B0aW9uR3JvdXA6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxzaXplOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8c2NhbGU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXx0aW1lUmFuZ2U6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxkaXJlY3RpdmU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxtZW51OnJlYWQsY3JlYXRlfGNvb2tpbmdUeXBlczpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGJvb2tpbmc6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxib29raW5nU2V0dGluZ3M6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxjdXN0b21lcjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGFkZHJlc3NTZXR0aW5nczpyZWFkLHVwZGF0ZXxwcm9tb3RlclNldHRpbmdzOnJlYWQsdXBkYXRlIiwiYnVzaW5lc3NlcyI6IntcImJ1c2luZXNzZXNcIjpbe1wiaWRcIjoxLFwiYWRkcmVzc2VzXCI6WzFdfSx7XCJpZFwiOjIsXCJhZGRyZXNzZXNcIjpbMiwzXX1dfSIsIm5iZiI6MTUyMjgyNTcxOCwiZXhwIjoxNTIyODc2MTE4LCJpYXQiOjE1MjI4MjU3MTgsImlzcyI6IlBlcnNvbm5lbFNlcnZpY2UiLCJhdWQiOiJQZXJzb25uZWxTZXJ2aWNlIn0.CuqH_x0f4kq7ebbH8UglwEwmhZgalJ1pX9YokFSL2lPkiv-KZJ4U6w23EEYjovx99d8MkM8VV8VPW9Kj3azBSU57U1oqobxasx6gI2-XEyJp0K0LRBHk6dC8tPTzBwwqvE3Z85M5n5afRIP4TKwuSHWMCGiO1DQq-8ovsZtX3GOs0tZ0nPJkAjQKMS14XHYl6ma_T2Tpod2ss81HD6pSWLPMtLFXr786qkLRm1-uFoADoXwv59mlPhDFuoBODKUmdflO-rE7ulsWqFSh_7bL5_kdpbNXQ-9mf1U6Gp0fN13rDXBTx77W5Buy9KQez5V_R2MxOhyH-904xouMRaNGhw";
    }

    @Test
    public void A_createNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.type1SupportSelling())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        System.out.println(response.asString());
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        this.ids = nomenclature.getId();
        this.article =nomenclature.getArticle();
        //Assert.assertEquals(23,nomenclature.getAddressId());
        Assert.assertEquals("kitchenName", nomenclature.getKitchenName());
        Assert.assertEquals("shortName", nomenclature.getShortName());
        Assert.assertEquals("publicName", nomenclature.getPublicName());
        Assert.assertEquals("barCode", nomenclature.getBarCode());
        Assert.assertEquals("recipe", nomenclature.getRecipe());
        Assert.assertEquals("description", nomenclature.getDescription());
        Assert.assertEquals(3, nomenclature.getWeightInKilos());
        Assert.assertEquals(3, nomenclature.getHeatLoss());
        Assert.assertEquals(3, nomenclature.getColdLoss());
        Assert.assertEquals(true, nomenclature.isSupportSelling());
        Assert.assertEquals(true, nomenclature.isSupportExtensioning());
        Assert.assertEquals(true, nomenclature.isPrintOnCheck());
        Assert.assertEquals(4456, nomenclature.getPreparingTime());
        Assert.assertEquals(4456, nomenclature.getRushPreparingTime());
        Assert.assertEquals(1, nomenclature.getMaxExtensions());
        Assert.assertEquals(1, nomenclature.getDebitMethodId());
        Assert.assertEquals(1, nomenclature.getNomenclatureTypeId());
        Assert.assertEquals(1, nomenclature.getCookingPriorityId());
        Assert.assertEquals(1, nomenclature.getSaleMethodId());
        Assert.assertEquals(1, nomenclature.getTasteGroupId());
        Assert.assertEquals(1, nomenclature.getSpecialGroupId());
        Assert.assertEquals(1, nomenclature.getSupportedOrderTypes());
        Assert.assertEquals(1, nomenclature.getDimensionId());
       // Assert.assertEquals(1, nomenclature.getTaxesIds().size());
        //Assert.assertEquals(1, nomenclature.getTaxesIds().get(0).intValue());
    }

    @Test
    public void B_updateNomenclature() {
        String update = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.updateModel(article))
                .when().put(update).thenReturn().body();
        System.out.println(response.asString());
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals("kitchenName1", nomenclature.getKitchenName());
        Assert.assertEquals("shortName1", nomenclature.getShortName());
        Assert.assertEquals("publicName1", nomenclature.getPublicName());
        Assert.assertEquals("barCode1", nomenclature.getBarCode());
        Assert.assertEquals("recipe1", nomenclature.getRecipe());
        Assert.assertEquals("description1", nomenclature.getDescription());
        Assert.assertEquals(4, nomenclature.getWeightInKilos());
        Assert.assertEquals(4, nomenclature.getHeatLoss());
        Assert.assertEquals(4, nomenclature.getColdLoss());
        Assert.assertEquals(false, nomenclature.isSupportSelling());
        Assert.assertEquals(false, nomenclature.isSupportExtensioning());
        Assert.assertEquals(false, nomenclature.isPrintOnCheck());
        Assert.assertEquals(4456, nomenclature.getRushPreparingTime());
        Assert.assertEquals(2, nomenclature.getMaxExtensions());
        Assert.assertEquals(2, nomenclature.getDebitMethodId());
        Assert.assertEquals(2, nomenclature.getNomenclatureTypeId());
        Assert.assertEquals(2, nomenclature.getCookingPriorityId());
        Assert.assertEquals(1, nomenclature.getSaleMethodId());
        //Assert.assertEquals(2, nomenclature.getTasteGroupId());
        //Assert.assertEquals(2, nomenclature.getSpecialGroupId());
        //Assert.assertEquals(2, nomenclature.getSupportedOrderTypes());
        Assert.assertEquals(2, nomenclature.getDimensionId());
    }

    @Test
    public void C_createNomenclstureThirdType() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclatureTestData.createNomenclatureThirdType()).when().post(baseURI).thenReturn().body();
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals(3, nomenclature.getNomenclatureTypeId());
    }

    @Test
    public void D_createNomenclstureFourthType() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclatureTestData.createNomenclatureFourthType()).when().post(baseURI).thenReturn().body();
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals(4, nomenclature.getNomenclatureTypeId());
    }

    @Test
    public void G_deactivateNomenclature(){
        String deactivateUrl = baseURI+"/"+ids+"/Deactivate";
        String getUrl = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().patch(deactivateUrl).thenReturn().body();
        ResponseBody response1 = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().get(getUrl).thenReturn().body();
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response1.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals(false, nomenclature.isActive());
    }

    @Test
    public void H_activateNomenclature(){
        String deactivateUrl = baseURI+"/"+ids+"/Activate";
        String getUrl = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().patch(deactivateUrl).thenReturn().body();
        ResponseBody response1 = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().get(getUrl).thenReturn().body();
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response1.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals(true, nomenclature.isActive());
    }

    @Test
    public void E_addPortion() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclaturePortionTestData.addPortion())
                .when().put(baseURI+"/"+ids+"/"+"Portion").thenReturn().body();

        ResponseBody response1 = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+"/"+ids).thenReturn().body();
        NomenclatureResponse nomenclatureResponse = new Gson().fromJson(response1.asString(),NomenclatureResponse.class);
        NomenclaturePortion nomenclatureResponse1 = nomenclatureResponse.data.getPortion();
        Assert.assertEquals(15,nomenclatureResponse1.getMaximum());
        Assert.assertEquals(3,nomenclatureResponse1.getMinimum());
        Assert.assertEquals(5,nomenclatureResponse1.getStepPrice());
        Assert.assertEquals(3,nomenclatureResponse1.getStep());
        Assert.assertEquals(10,nomenclatureResponse1.getMinimumPrice());
    }
    @Test
    public void E_deletePortion() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclaturePortionTestData.addPortion())
                .when().delete(baseURI+"/"+ids+"/"+"Portion").thenReturn().body();

        ResponseBody response1 = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+"/"+ids).thenReturn().body();
        NomenclatureResponse nomenclatureResponse = new Gson().fromJson(response1.asString(),NomenclatureResponse.class);
        NomenclaturePortion nomenclatureResponse1 = nomenclatureResponse.data.getPortion();
        Assert.assertEquals(null,nomenclatureResponse1);

    }
}
