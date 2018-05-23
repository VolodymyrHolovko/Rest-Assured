package Nomenclature.Nomenclatures;

import Auth.GetToken;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
import Nomenclature.Portion.NomenclaturePortion;
import Nomenclature.Portion.NomenclaturePortionTestData;
import Nomenclature.Sizes.*;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static java.lang.Integer.parseInt;

public class NomenclatureTests {

    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Nomenclature";
    public String ids;
    public String article;
    NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();
    NomenclaturePortionTestData nomenclaturePortionTestData = new NomenclaturePortionTestData();
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
        Assert.assertEquals(false, nomenclature.isSupportExtensioning());
        Assert.assertEquals(false, nomenclature.isPrintOnCheck());
        Assert.assertEquals(23, nomenclature.getRushPreparingTime());
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
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.createNomenclatureThirdType())
                .when().post(baseURI)
                .thenReturn().body();

        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        int idd = parseInt(nomenclature.getId());
        Assert.assertEquals(3, nomenclature.getNomenclatureTypeId());

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + idd).thenReturn().body();
    }

    @Test
    public void D_createNomenclstureFourthType() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.createNomenclatureFourthType())
                .when().post(baseURI)
                .thenReturn().body();

        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        int idd = parseInt(nomenclature.getId());
        Assert.assertEquals(4, nomenclature.getNomenclatureTypeId());

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + idd).thenReturn().body();
    }

    @Test
    public void G_deactivateNomenclature(){
        String deactivateUrl = baseURI+"/"+ids+"/Deactivate";
        String getUrl = baseURI+"/"+ids;

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().patch(deactivateUrl)
                .thenReturn().body();

        ResponseBody response1 = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(getUrl)
                .thenReturn().body();

        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response1.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals(false, nomenclature.isActive());
    }

    @Test
    public void H_activateNomenclature(){
        String deactivateUrl = baseURI+"/"+ids+"/Activate";
        String getUrl = baseURI+"/"+ids;
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().patch(deactivateUrl)
                .thenReturn().body();

        ResponseBody response1 = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(getUrl)
                .thenReturn().body();

        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response1.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        Assert.assertEquals(true, nomenclature.isActive());
    }

    @Test
    public void E_addPortion() {
        String update = baseURI+"/"+ids;
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.updateModel(article))
                .when().put(update).thenReturn().body();

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
    @Test
    public void F_createSize(){
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.type1SupportSelling())
                .when().post(baseURI).thenReturn().body();
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(respons.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
         this.idd = parseInt(nomenclature.getId());

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(sizeData.createSize(idd))
                .when().post("http://staging.eservia.com:8008/api/v0.0/Sizes").thenReturn().body();
        SizeResponse sizeResponse = new Gson().fromJson(response.asString(),  SizeResponse.class);
        Size size = sizeResponse.getData();
        this.sizeId = size.getId();
        Assert.assertEquals(10, size.getSize());
        Assert.assertEquals(21, size.getPrice());
        Assert.assertEquals(1, size.getSizeTypeId());
        Assert.assertEquals(2, size.getWriteOffIndex());
        Assert.assertEquals("XL", size.getPresentationName());

    }

    @Test
    public void  G_updateSize(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(sizeData.updateSize())
                .when().put("http://staging.eservia.com:8008/api/v0.0/Sizes/"+sizeId).thenReturn().body();
        SizeResponse sizeResponse = new Gson().fromJson(response.asString(),  SizeResponse.class);
        Size size = sizeResponse.getData();
        Assert.assertEquals(12, size.getSize());
        Assert.assertEquals(22, size.getPrice());
        Assert.assertEquals(3, size.getWriteOffIndex());
        Assert.assertEquals("L", size.getPresentationName());
    }


    @Test
    public void H_deactivateSize(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8008/api/v0.0/Sizes/"+sizeId+"/Deactivate").thenReturn().body();
        SizeBoolean sizeResponse = new Gson().fromJson(response.asString(),  SizeBoolean.class);
        Assert.assertEquals("success", sizeResponse.getDescription());
    }

    @Test
    public void I_activateSize(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("http://staging.eservia.com:8008/api/v0.0/Sizes/"+sizeId+"/Activate").thenReturn().body();
        SizeBoolean sizeResponse = new Gson().fromJson(response.asString(),  SizeBoolean.class);
        Assert.assertEquals("success", sizeResponse.getDescription());
    }
    @Test
    public void J_deleteSize() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://staging.eservia.com:8008/api/v0.0/Sizes/" + sizeId).thenReturn().body();

        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + idd).thenReturn().body();

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(sizeData.updateSize())
                .when().put("http://staging.eservia.com:8008/api/v0.0/Sizes/"+sizeId).thenReturn().body();
        SizeResponse sizeResponse = new Gson().fromJson(respons.asString(),  SizeResponse.class);
        SizeError sizeError = sizeResponse.getError();
        Assert.assertEquals("NomenclatureSizeDoesNotExist", sizeError.getErrorDescription());
    }
    @Test
    public void K_deleteNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + ids).thenReturn().body();
    }

    @Test
    public void getAllNomenclature(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://staging.eservia.com:8008/api/v0.0/Nomenclature?AddressId=2");
        Assert.assertEquals(200,response.getStatusCode());
    }

    public String L_returnId(){
        return ids;
    }
    }
