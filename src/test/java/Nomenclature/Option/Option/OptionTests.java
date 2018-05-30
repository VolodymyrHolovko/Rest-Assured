package Nomenclature.Option.Option;

import Auth.GetToken;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
import Nomenclature.Option.Group.*;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class OptionTests {
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Options";
    public int nomenclatureId;
    OptionGroupData optionGroupData = new OptionGroupData();
    OptionData optionData= new OptionData();
    private  int optionGroupId;
    NomenclatureTestData nomenclatureTestData = new NomenclatureTestData();

    String token;
    int optionId;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(optionGroupData.createOptionGroup())
                .when().post("http://staging.eservia.com:8008/api/v0.0/Options/Groups").thenReturn().body();
        OptionGroupResponse optionGroupResponse= new Gson().fromJson(response.asString(),  OptionGroupResponse.class);
        OptionGroup optionGroup= optionGroupResponse.getData();
        this.optionGroupId =  optionGroup.getId();

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(nomenclatureTestData.type1SupportSelling())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("http://staging.eservia.com:8008/api/v0.0/Nomenclature").thenReturn().body();
        System.out.println(response.asString());
        NomenclatureResponse nomenclatureResponse  = new Gson().fromJson(respons.asString(),  NomenclatureResponse.class);
        Nomenclature nomenclature = nomenclatureResponse.data;
        this.nomenclatureId = Integer.parseInt(nomenclature.getId());

    }

    @Test
    public void A_createOption() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(optionData.createOption(optionGroupId,nomenclatureId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();

        OptionResponse optionResponse= new Gson().fromJson(response.asString(), OptionResponse.class);
        Option option = optionResponse.data;
        this.optionId = option.getId();
        Assert.assertEquals(1,option.getMinQuantity());
        Assert.assertEquals(5,option.getMaxQuantity());
        Assert.assertEquals("option Name", option.getName());
        Assert.assertEquals(true,option.isFree());
        Assert.assertEquals(nomenclatureId,option.getNomenclatureOptions().get(0).getNomenclatureId());
        Assert.assertEquals(1,option.getNomenclatureOptions().get(0).getMinQuantity());
        Assert.assertEquals(5,option.getNomenclatureOptions().get(0).getMaxQuantity());
        Assert.assertEquals(1,option.getNomenclatureOptions().get(0).getDefaultQuantity());
        Assert.assertEquals(true,option.getNomenclatureOptions().get(0).isPrintIfDefaultQuantity());
        Assert.assertEquals(true,option.getNomenclatureOptions().get(0).isConstant());

        ResponseBody responsee = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("http://staging.eservia.com:8008/api/v0.0/Options/Groups/"+optionGroupId).thenReturn().body();
        OptionLeafs optionLeafs= new Gson().fromJson(responsee.asString(), OptionLeafs.class);
        OptionLeafs optionLeafs1=optionLeafs.data;
        Assert.assertEquals(1,optionLeafs1.getLeafs().size());
    }

    @Test
    public void B_updateOption() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(optionData.updateOption(optionGroupId,nomenclatureId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI+"/"+optionId).thenReturn().body();

        OptionResponse optionResponse= new Gson().fromJson(response.asString(), OptionResponse.class);
        Option option = optionResponse.data;
        Assert.assertEquals(2,option.getMinQuantity());
        Assert.assertEquals(6,option.getMaxQuantity());
        Assert.assertEquals("option Name2", option.getName());
        Assert.assertEquals(false,option.isFree());
        Assert.assertEquals(nomenclatureId,option.getNomenclatureOptions().get(0).getNomenclatureId());
        Assert.assertEquals(2,option.getNomenclatureOptions().get(0).getMinQuantity());
        Assert.assertEquals(6,option.getNomenclatureOptions().get(0).getMaxQuantity());
        Assert.assertEquals(2,option.getNomenclatureOptions().get(0).getDefaultQuantity());
        Assert.assertEquals(false,option.getNomenclatureOptions().get(0).isPrintIfDefaultQuantity());
        Assert.assertEquals(false,option.getNomenclatureOptions().get(0).isConstant());
    }

    @Test
    public void C_deactivateOption() {
        ResponseBody responsee = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI + "/" + optionId + "/Deactivate").thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+"/"+optionId).thenReturn().body();

        OptionResponse optionResponse = new Gson().fromJson(response.asString(), OptionResponse.class);
        Option option = optionResponse.data;
        Assert.assertEquals(2, option.getMinQuantity());
        Assert.assertEquals(6, option.getMaxQuantity());
        Assert.assertEquals("option Name2", option.getName());
        Assert.assertEquals(false, option.isFree());
        Assert.assertEquals(false,option.isActive());
        Assert.assertEquals(nomenclatureId, option.getNomenclatureOptions().get(0).getNomenclatureId());
        Assert.assertEquals(2, option.getNomenclatureOptions().get(0).getMinQuantity());
        Assert.assertEquals(6, option.getNomenclatureOptions().get(0).getMaxQuantity());
        Assert.assertEquals(2, option.getNomenclatureOptions().get(0).getDefaultQuantity());
        Assert.assertEquals(false, option.getNomenclatureOptions().get(0).isPrintIfDefaultQuantity());
        Assert.assertEquals(false, option.getNomenclatureOptions().get(0).isConstant());
    }

    @Test
    public void D_ActivateOption() {
        ResponseBody responsee = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI + "/" + optionId+"/Activate").thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+"/"+optionId).thenReturn().body();

        OptionResponse optionResponse = new Gson().fromJson(response.asString(), OptionResponse.class);
        Option option = optionResponse.data;
        Assert.assertEquals(2, option.getMinQuantity());
        Assert.assertEquals(6, option.getMaxQuantity());
        Assert.assertEquals("option Name2", option.getName());
        Assert.assertEquals(false, option.isFree());
        Assert.assertEquals(true,option.isActive());
        Assert.assertEquals(nomenclatureId, option.getNomenclatureOptions().get(0).getNomenclatureId());
        Assert.assertEquals(2, option.getNomenclatureOptions().get(0).getMinQuantity());
        Assert.assertEquals(6, option.getNomenclatureOptions().get(0).getMaxQuantity());
        Assert.assertEquals(2, option.getNomenclatureOptions().get(0).getDefaultQuantity());
        Assert.assertEquals(false, option.getNomenclatureOptions().get(0).isPrintIfDefaultQuantity());
        Assert.assertEquals(false, option.getNomenclatureOptions().get(0).isConstant());
    }

    @Test
    public void getAllOption(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://staging.eservia.com:8008/api/v0.0/Options?AddressId=2");
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void E_deleteOption() {
        ResponseBody responsee = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI + "/" + optionId)
                .thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI + "/" + optionId).thenReturn().body();
         OptionError optionError  = new Gson().fromJson(response.asString(), OptionError.class);
         OptionError error = optionError.error;
         Assert.assertEquals("OptionDoesNotExist",error.getErrorDescription());
    }



    @AfterClass
    public void deleteTestItem(){
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://staging.eservia.com:8008/api/v0.0/Nomenclature" + "/" + nomenclatureId).thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().delete("http://staging.eservia.com:8008/api/v0.0/Options/Groups"+"/"+optionGroupId).thenReturn().body();
        OptionGroupBoolean optionGroupBoolean= new Gson().fromJson(response.asString(),  OptionGroupBoolean.class);
        Assert.assertEquals("success",optionGroupBoolean.getDescription());
    }

}
