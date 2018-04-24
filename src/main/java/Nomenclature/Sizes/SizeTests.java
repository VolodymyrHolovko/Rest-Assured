package Nomenclature.Sizes;

import Auth.GetToken;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
import Nomenclature.Portion.NomenclaturePortionTestData;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class SizeTests {
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Sizes";
    public String ids;
    public String article;
    SizeData sizeData = new SizeData();
    String token;
    int sizeId;
    int idd;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }

    @Test
    public void A_createSize() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(sizeData.createSize())
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
        Assert.assertEquals(true, nomenclature.isPrintOnCheck());
        Assert.assertEquals(true, nomenclature.isSupportExtensioning());
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
}
