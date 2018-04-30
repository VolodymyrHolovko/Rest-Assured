package Nomenclature.Option.Option;

import Auth.GetToken;
import Marketing.Marketing;
import Marketing.MarketingResponse;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
import Nomenclature.Nomenclatures.NomenclatureTests;
import Nomenclature.Option.Group.OptionGroupTests;
import Nomenclature.Portion.NomenclaturePortionTestData;
import Nomenclature.Sizes.SizeData;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class OptionTests {
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Options";
    public int parentId;
    public String nomenclatureId;
    OptionData optionData= new OptionData();
    NomenclatureTests nomenclatureTest= new NomenclatureTests();

    OptionGroupTests optionGroupTests= new OptionGroupTests();

    String token;
    int optionId;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
        NomenclatureTests nomenclatureTest= new NomenclatureTests();
        this.nomenclatureId = nomenclatureTest.L_returnId();
        OptionGroupTests optionGroupTests= new OptionGroupTests();
        this.parentId = optionGroupTests.J_returnId();

    }

    @Test
    public void A_createNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(optionData.createOption(parentId,nomenclatureId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();

        OptionResponse optionResponse= new Gson().fromJson(response.asString(), OptionResponse.class);
        Option option = optionResponse.data;
        Assert.assertEquals(1,option.getAddressId());
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

    }

}
