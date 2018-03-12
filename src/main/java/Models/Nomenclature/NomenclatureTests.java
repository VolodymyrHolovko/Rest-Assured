package Models.Nomenclature;

import Models.Token.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.invoke.empty.Empty;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class NomenclatureTests {
    String token = "";
    String baseURI = "http://cluster.test.eservia.com/api/v0.0/Nomenclature";
    public String ids;
    public int article;
    Nomenclature nomenclature = new Nomenclature();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token=getToken.GetToken();
    }

    @Test
    public void createNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclature.type1SupportSelling()).when().post(baseURI).thenReturn().body();
        NomenclatureResponse nomenclatureResponse = new Gson().fromJson(response.asString(), NomenclatureResponse.class);
        this.ids = nomenclatureResponse.data.getId();
        this.article = nomenclatureResponse.data.getArticle();
        Assert.assertEquals("kitchenName", nomenclatureResponse.data.getKitchenName());
        Assert.assertEquals("shortName", nomenclatureResponse.data.getShortName());
        Assert.assertEquals("publicName", nomenclatureResponse.data.getPublicName());
        Assert.assertEquals("barCode", nomenclatureResponse.data.getBarCode());
        Assert.assertEquals("recipe", nomenclatureResponse.data.getRecipe());
        Assert.assertEquals("description", nomenclatureResponse.data.getDescription());
        Assert.assertEquals(3, nomenclatureResponse.data.getWeightInKilos());
        Assert.assertEquals(3, nomenclatureResponse.data.getHeatLoss());
        Assert.assertEquals(3, nomenclatureResponse.data.getColdLoss());
        Assert.assertEquals(true, nomenclatureResponse.data.isSupportSelling());
        Assert.assertEquals(true, nomenclatureResponse.data.isSupportExtensioning());
        Assert.assertEquals(true, nomenclatureResponse.data.isPrintOnCheck());
        Assert.assertEquals(4456, nomenclatureResponse.data.getPreparingTime());
        Assert.assertEquals(4456, nomenclatureResponse.data.getRushPreparingTime());
        Assert.assertEquals(1, nomenclatureResponse.data.getMaxExtensions());
        Assert.assertEquals(1, nomenclatureResponse.data.getDebitMethodId());
        Assert.assertEquals(1, nomenclatureResponse.data.getNomenclatureTypeId());
        Assert.assertEquals(1, nomenclatureResponse.data.getCookingPriorityId());
        Assert.assertEquals(1, nomenclatureResponse.data.getSaleMethodId());
        Assert.assertEquals(1, nomenclatureResponse.data.getTasteGroupId());
        Assert.assertEquals(1, nomenclatureResponse.data.getSpecialGroupId());
        Assert.assertEquals(1, nomenclatureResponse.data.getSupportedOrderTypes());
        Assert.assertEquals(1, nomenclatureResponse.data.getDimensionId());
        Assert.assertEquals(1,nomenclatureResponse.data.getTaxesIds().size());
        System.out.println(nomenclatureResponse);
        Assert.assertEquals(1,nomenclatureResponse.data.getTaxesIds().get(0).intValue());
    }

    @Test
    public void updateNomenclature() {
        String update = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclature.updateModel(article)).when().put(update).thenReturn().body();
        NomenclatureResponse nomenclatureResponse = new Gson().fromJson(response.asString(), NomenclatureResponse.class);
        Assert.assertEquals("kitchenName1", nomenclatureResponse.data.getKitchenName());
        Assert.assertEquals("shortName1", nomenclatureResponse.data.getShortName());
        Assert.assertEquals("publicName1", nomenclatureResponse.data.getPublicName());
        Assert.assertEquals("barCode1", nomenclatureResponse.data.getBarCode());
        Assert.assertEquals("recipe1", nomenclatureResponse.data.getRecipe());
        Assert.assertEquals("description1", nomenclatureResponse.data.getDescription());
        Assert.assertEquals(4, nomenclatureResponse.data.getWeightInKilos());
        Assert.assertEquals(4, nomenclatureResponse.data.getHeatLoss());
        Assert.assertEquals(4, nomenclatureResponse.data.getColdLoss());
        Assert.assertEquals(false, nomenclatureResponse.data.isSupportSelling());
        Assert.assertEquals(false, nomenclatureResponse.data.isSupportExtensioning());
        Assert.assertEquals(false, nomenclatureResponse.data.isPrintOnCheck());
        Assert.assertEquals(article, nomenclatureResponse.data.getArticle());
        Assert.assertEquals(23, nomenclatureResponse.data.getRushPreparingTime());
        Assert.assertEquals(2, nomenclatureResponse.data.getMaxExtensions());
        Assert.assertEquals(2, nomenclatureResponse.data.getDebitMethodId());
        Assert.assertEquals(2, nomenclatureResponse.data.getNomenclatureTypeId());
        Assert.assertEquals(2, nomenclatureResponse.data.getCookingPriorityId());
        Assert.assertEquals(2, nomenclatureResponse.data.getSaleMethodId());
        Assert.assertEquals(2, nomenclatureResponse.data.getTasteGroupId());
        Assert.assertEquals(2, nomenclatureResponse.data.getSpecialGroupId());
        Assert.assertEquals(2, nomenclatureResponse.data.getSupportedOrderTypes());
        Assert.assertEquals(2, nomenclatureResponse.data.getDimensionId());
    }

    @Test
    public void createNomenclstureThirdType() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclature.createNomenclatureThirdType()).when().post(baseURI).thenReturn().body();
        NomenclatureResponse nomenclatureResponse = new Gson().fromJson(response.asString(), NomenclatureResponse.class);
        Assert.assertEquals(3, nomenclatureResponse.data.getNomenclatureTypeId());
    }

    @Test
    public void createNomenclstureFourthType() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclature.createNomenclatureFourthType()).when().post(baseURI).thenReturn().body();
        NomenclatureResponse nomenclatureResponse = new Gson().fromJson(response.asString(), NomenclatureResponse.class);
        Assert.assertEquals(4, nomenclatureResponse.data.getNomenclatureTypeId());
    }

    @Test
    public void deactivateNomenclature(){
        String deactivateUrl = baseURI+"/"+ids+"/Deactivate";
        String getUrl = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().patch(deactivateUrl).thenReturn().body();
        ResponseBody response1 = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().get(getUrl).thenReturn().body();
        NomenclatureResponse nomenclatureResponse1 = new Gson().fromJson(response1.asString(), NomenclatureResponse.class);
        Assert.assertEquals(false, nomenclatureResponse1.data.isActive());
    }
}
