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
    public int ids;
    public String article;
    NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();
    SizeData sizeData = new SizeData();
    String token;
    int sizeId;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.type1SupportSelling())
                .when().post("http://staging.eservia.com:8008/api/v0.0/Nomenclature").thenReturn().body();
        System.out.println(response.asString());
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(response.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        this.ids =  Integer.parseInt(nomenclature.getId());

    }
    @Test
    public void A_createSize(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(sizeData.createSize(ids))
                .when().post(baseURI).thenReturn().body();
        SizeResponse sizeResponse  = new Gson().fromJson(response.asString(),  SizeResponse.class);
        Size size = sizeResponse.data;
        this.sizeId=size.getId();
        Assert.assertEquals(10, size.getSize());
        Assert.assertEquals(21, size.getPrice());
        Assert.assertEquals(1, size.getSizeTypeId());
        Assert.assertEquals(2,size.getWriteOffIndex());
        Assert.assertEquals("XL", size.getPresentationName());
    }

    @Test
    public void B_updateSize() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .body(sizeData.updateSize())
                .when().put(baseURI+"/"+sizeId).thenReturn().body();
        SizeResponse sizeResponse = new Gson().fromJson(response.asString(), SizeResponse.class);
        Size size = sizeResponse.data;
        Assert.assertEquals(12, size.getSize());
        Assert.assertEquals(22, size.getPrice());
        Assert.assertEquals(3, size.getWriteOffIndex());
        Assert.assertEquals("L", size.getPresentationName());
    }

    @Test
    public void C_ActivateSize() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI+"/"+sizeId+"/Activate").thenReturn().body();
        SizeBoolean sizeBoolean = new Gson().fromJson(response.asString(), SizeBoolean.class);
        Assert.assertEquals("success", sizeBoolean.getDescription());
    }

    @Test
    public void D_DeactivateSize() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI+"/"+sizeId+"/Deactivate").thenReturn().body();
        SizeBoolean sizeBoolean = new Gson().fromJson(response.asString(), SizeBoolean.class);
        Assert.assertEquals("success", sizeBoolean.getDescription());
    }

    @Test
    public void E_deleteSize() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI+"/"+sizeId).thenReturn().body();
        SizeBoolean sizeBoolean = new Gson().fromJson(response.asString(), SizeBoolean.class);
        Assert.assertEquals("success", sizeBoolean.getDescription());
    }

}
