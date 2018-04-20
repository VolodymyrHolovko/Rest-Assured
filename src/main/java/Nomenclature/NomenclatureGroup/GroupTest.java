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
        System.out.println(response.asString());
        Group group  = new Gson().fromJson(response.asString(),  Group.class);
        GroupResponse groupResponse = group.data;
        this.ids = groupResponse.getId();

        Assert.assertEquals(1, groupResponse.getParentId());
        Assert.assertEquals(1, groupResponse.getDefaultDimensionId());
        Assert.assertEquals(1, groupResponse.getDefaultDebitingMethodId());
        Assert.assertEquals(1, groupResponse.getDefaultSellingMethodId());
        Assert.assertEquals("TestGroup", groupResponse.getName());
        Assert.assertEquals("My tests", groupResponse.getDescription());
        Assert.assertEquals("My tests", groupResponse.getDescription());
        Assert.assertEquals("#443322", groupResponse.getColorHex());
        Assert.assertEquals("Images/201710/file636449551149284980.png", groupResponse.getIconPath());

    }
}
