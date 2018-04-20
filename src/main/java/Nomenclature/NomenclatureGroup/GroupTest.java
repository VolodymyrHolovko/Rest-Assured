package Nomenclature.NomenclatureGroup;

import Auth.GetToken;
import Nomenclature.Nomenclatures.Nomenclature;
import Nomenclature.Nomenclatures.NomenclatureResponse;
import Nomenclature.Nomenclatures.NomenclatureTestData;
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

public class GroupTest {
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Nomenclature/Groups";
    GroupData groupData = new GroupData();
    String token;
    int ids;

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
                .body(groupData.createGroup())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        Group group  = new Gson().fromJson(response.asString(),  Group.class);
        GroupResponse groupResponse = group.data;
        this.ids = groupResponse.getId();

        Assert.assertEquals(1, groupResponse.getParentId());
        Assert.assertEquals(1, groupResponse.getDefaultDimensionId());
        Assert.assertEquals(1, groupResponse.getDefaultDebitingMethodId());
        Assert.assertEquals(1, groupResponse.getDefaultSellingMethodId());
        Assert.assertEquals("TestGroup", groupResponse.getName());
        Assert.assertEquals("My tests", groupResponse.getDescription());
        Assert.assertEquals("#443322", groupResponse.getColorHex());
        Assert.assertEquals("Images/201710/file636449551149284980.png", groupResponse.getIconPath());

    }

    @Test
    public void B_updateNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(groupData.updateGroup())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURI+"/"+ids).thenReturn().body();
        Group group  = new Gson().fromJson(response.asString(),  Group.class);
        GroupResponse groupResponse = group.data;

        Assert.assertEquals(1, groupResponse.getParentId());
        Assert.assertEquals(2, groupResponse.getDefaultDimensionId());
        Assert.assertEquals(2, groupResponse.getDefaultDebitingMethodId());
        Assert.assertEquals(2, groupResponse.getDefaultSellingMethodId());
        Assert.assertEquals("TestGroup2", groupResponse.getName());
        Assert.assertEquals("My tests2", groupResponse.getDescription());
        Assert.assertEquals("#443323", groupResponse.getColorHex());
        Assert.assertEquals("Images/201710/file636449551671201110.png", groupResponse.getIconPath());
    }

    @Test
    public void C_deactivateNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI+"/"+ids+"/Deactivate").thenReturn().body();


        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(groupData.updateGroup())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+"/"+ids+"?addressId=2").thenReturn().body();
        Group group  = new Gson().fromJson(respons.asString(),  Group.class);
        GroupResponse groupResponse = group.data;

        Assert.assertEquals(false,groupResponse.isActive());
        Assert.assertEquals(1, groupResponse.getParentId());
        Assert.assertEquals(2, groupResponse.getDefaultDimensionId());
        Assert.assertEquals(2, groupResponse.getDefaultDebitingMethodId());
        Assert.assertEquals(2, groupResponse.getDefaultSellingMethodId());
        Assert.assertEquals("TestGroup2", groupResponse.getName());
        Assert.assertEquals("My tests2", groupResponse.getDescription());
        Assert.assertEquals("#443323", groupResponse.getColorHex());
        Assert.assertEquals("Images/201710/file636449551671201110.png", groupResponse.getIconPath());
    }

    @Test
    public void D_ActivateNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch(baseURI+"/"+ids+"/Activate").thenReturn().body();


        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(groupData.updateGroup())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+"/"+ids+"?addressId=2").thenReturn().body();
        Group group  = new Gson().fromJson(respons.asString(),  Group.class);
        GroupResponse groupResponse = group.data;

        Assert.assertEquals(true,groupResponse.isActive());
        Assert.assertEquals(1, groupResponse.getParentId());
        Assert.assertEquals(2, groupResponse.getDefaultDimensionId());
        Assert.assertEquals(2, groupResponse.getDefaultDebitingMethodId());
        Assert.assertEquals(2, groupResponse.getDefaultSellingMethodId());
        Assert.assertEquals("TestGroup2", groupResponse.getName());
        Assert.assertEquals("My tests2", groupResponse.getDescription());
        Assert.assertEquals("#443323", groupResponse.getColorHex());
        Assert.assertEquals("Images/201710/file636449551671201110.png", groupResponse.getIconPath());
    }
    @Test
    public void F_DeleteNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURI+"/"+ids).thenReturn().body();


        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(groupData.updateGroup())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURI+"/"+ids+"?addressId=2").thenReturn().body();
        Errors error  = new Gson().fromJson(respons.asString(),  Errors.class);
        Errors errors = error.error;
        Assert.assertEquals("NomenclatureGroupDoesNotExist",errors.getErrorDescription());
    }
}
