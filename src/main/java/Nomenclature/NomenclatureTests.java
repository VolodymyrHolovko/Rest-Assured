package Nomenclature;

import token.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;

public class NomenclatureTests {
    String token = "";
    String baseURI = "http://cluster.test.eservia.com/api/v0.0/NomenclatureData";
    public String ids;
    public int article;
    NomenclatureData nomenclatureData = new NomenclatureData();

    @BeforeClass
    public void getToken() {
        GetToken getToken = new GetToken();
        this.token=getToken.GetToken();
    }

    @Test
    public void createNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclatureData.type1SupportSelling()).when().post(baseURI).thenReturn().body();
        Nomenclature nomenclature = new Gson().fromJson(response.asString(), Nomenclature.class);
        this.ids = nomenclature.data.getId();
        this.article = nomenclature.data.getArticle();
        Assert.assertEquals("kitchenName", nomenclature.data.getKitchenName());
        Assert.assertEquals("shortName", nomenclature.data.getShortName());
        Assert.assertEquals("publicName", nomenclature.data.getPublicName());
        Assert.assertEquals("barCode", nomenclature.data.getBarCode());
        Assert.assertEquals("recipe", nomenclature.data.getRecipe());
        Assert.assertEquals("description", nomenclature.data.getDescription());
        Assert.assertEquals(3, nomenclature.data.getWeightInKilos());
        Assert.assertEquals(3, nomenclature.data.getHeatLoss());
        Assert.assertEquals(3, nomenclature.data.getColdLoss());
        Assert.assertEquals(true, nomenclature.data.isSupportSelling());
        Assert.assertEquals(true, nomenclature.data.isSupportExtensioning());
        Assert.assertEquals(true, nomenclature.data.isPrintOnCheck());
        Assert.assertEquals(4456, nomenclature.data.getPreparingTime());
        Assert.assertEquals(4456, nomenclature.data.getRushPreparingTime());
        Assert.assertEquals(1, nomenclature.data.getMaxExtensions());
        Assert.assertEquals(1, nomenclature.data.getDebitMethodId());
        Assert.assertEquals(1, nomenclature.data.getNomenclatureTypeId());
        Assert.assertEquals(1, nomenclature.data.getCookingPriorityId());
        Assert.assertEquals(1, nomenclature.data.getSaleMethodId());
        Assert.assertEquals(1, nomenclature.data.getTasteGroupId());
        Assert.assertEquals(1, nomenclature.data.getSpecialGroupId());
        Assert.assertEquals(1, nomenclature.data.getSupportedOrderTypes());
        Assert.assertEquals(1, nomenclature.data.getDimensionId());
        Assert.assertEquals(1, nomenclature.data.getTaxesIds().size());
        System.out.println(nomenclature);
        Assert.assertEquals(1, nomenclature.data.getTaxesIds().get(0).intValue());
    }

    @Test
    public void updateNomenclature() {
        String update = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclatureData.updateModel(article)).when().put(update).thenReturn().body();
        Nomenclature nomenclature = new Gson().fromJson(response.asString(), Nomenclature.class);
        Assert.assertEquals("kitchenName1", nomenclature.data.getKitchenName());
        Assert.assertEquals("shortName1", nomenclature.data.getShortName());
        Assert.assertEquals("publicName1", nomenclature.data.getPublicName());
        Assert.assertEquals("barCode1", nomenclature.data.getBarCode());
        Assert.assertEquals("recipe1", nomenclature.data.getRecipe());
        Assert.assertEquals("description1", nomenclature.data.getDescription());
        Assert.assertEquals(4, nomenclature.data.getWeightInKilos());
        Assert.assertEquals(4, nomenclature.data.getHeatLoss());
        Assert.assertEquals(4, nomenclature.data.getColdLoss());
        Assert.assertEquals(false, nomenclature.data.isSupportSelling());
        Assert.assertEquals(false, nomenclature.data.isSupportExtensioning());
        Assert.assertEquals(false, nomenclature.data.isPrintOnCheck());
        Assert.assertEquals(article, nomenclature.data.getArticle());
        Assert.assertEquals(23, nomenclature.data.getRushPreparingTime());
        Assert.assertEquals(2, nomenclature.data.getMaxExtensions());
        Assert.assertEquals(2, nomenclature.data.getDebitMethodId());
        Assert.assertEquals(2, nomenclature.data.getNomenclatureTypeId());
        Assert.assertEquals(2, nomenclature.data.getCookingPriorityId());
        Assert.assertEquals(2, nomenclature.data.getSaleMethodId());
        Assert.assertEquals(2, nomenclature.data.getTasteGroupId());
        Assert.assertEquals(2, nomenclature.data.getSpecialGroupId());
        Assert.assertEquals(2, nomenclature.data.getSupportedOrderTypes());
        Assert.assertEquals(2, nomenclature.data.getDimensionId());
    }

    @Test
    public void createNomenclstureThirdType() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclatureData.createNomenclatureThirdType()).when().post(baseURI).thenReturn().body();
        Nomenclature nomenclature = new Gson().fromJson(response.asString(), Nomenclature.class);
        Assert.assertEquals(3, nomenclature.data.getNomenclatureTypeId());
    }

    @Test
    public void createNomenclstureFourthType() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").body(nomenclatureData.createNomenclatureFourthType()).when().post(baseURI).thenReturn().body();
        Nomenclature nomenclature = new Gson().fromJson(response.asString(), Nomenclature.class);
        Assert.assertEquals(4, nomenclature.data.getNomenclatureTypeId());
    }

    @Test
    public void deactivateNomenclature(){
        String deactivateUrl = baseURI+"/"+ids+"/Deactivate";
        String getUrl = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().patch(deactivateUrl).thenReturn().body();
        ResponseBody response1 = given().contentType(ContentType.JSON).header("Authorization", token).header("EstablishmentContextId", "1").when().get(getUrl).thenReturn().body();
        Nomenclature nomenclature1 = new Gson().fromJson(response1.asString(), Nomenclature.class);
        Assert.assertEquals(false, nomenclature1.data.isActive());
    }
}
