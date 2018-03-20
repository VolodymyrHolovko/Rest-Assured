package Nomenclature;

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
    String baseURI = "http://cluster.test.eservia.com/api/v0.0/Nomenclature";
    public String ids;
    public String article;
    NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();
    NomenclaturePortionTestData nomenclaturePortionTestData = new NomenclaturePortionTestData();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token=getToken.GetToken();
    }

    @Test
    public void A_createNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.type1SupportSelling())
                .when().post(baseURI).thenReturn().body();
        System.out.println(response.asString());
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        this.ids = nomenclature.getId();
        this.article =nomenclature.getArticle();
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
        Assert.assertEquals(1, nomenclature.getTaxesIds().size());
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
