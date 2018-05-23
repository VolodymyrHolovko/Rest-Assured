package Nomenclature.Option.Group;

import Auth.GetToken;
import Nomenclature.Option.Group.OptionGroup;
import Nomenclature.Option.Group.OptionGroupBoolean;
import Nomenclature.Option.Group.OptionGroupData;
import Nomenclature.Option.Group.OptionGroupResponse;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class OptionGroupTests {
    String baseURI = "http://staging.eservia.com:8008/api/v0.0/Options/Groups";
    OptionGroupData optionGroupData= new OptionGroupData();
    String token;
    public int optionGroupId;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }

    @Test
    public void A_createOptionGroup(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(optionGroupData.createOptionGroup())
                .when().post(baseURI).thenReturn().body();
        OptionGroupResponse optionGroupResponse= new Gson().fromJson(response.asString(),  OptionGroupResponse.class);
        OptionGroup optionGroup= optionGroupResponse.data;
        this.optionGroupId =  optionGroup.getId();
        Assert.assertEquals("Test Group",optionGroup.getName());
        Assert.assertEquals("#223344",optionGroup.getColorHex());
        Assert.assertEquals(1,optionGroup.getParentId());
    }

    @Test
    public void B_updateOptionGroup(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(optionGroupData.updateOptionGroup())
                .when().put(baseURI+"/"+optionGroupId).thenReturn().body();
        OptionGroupResponse optionGroupResponse= new Gson().fromJson(response.asString(),  OptionGroupResponse.class);
        OptionGroup optionGroup= optionGroupResponse.data;
        this.optionGroupId =  optionGroup.getId();
        Assert.assertEquals("Test Group1",optionGroup.getName());
        Assert.assertEquals("#223345",optionGroup.getColorHex());
        Assert.assertEquals(1,optionGroup.getParentId());
    }

    @Test
    public void C_deactivateOptionGroup(){
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().patch(baseURI+"/"+optionGroupId+"/Deactivate").thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+"/"+optionGroupId).thenReturn().body();
        OptionGroupResponse optionGroupResponse= new Gson().fromJson(response.asString(),  OptionGroupResponse.class);
        OptionGroup optionGroup= optionGroupResponse.data;
        this.optionGroupId =  optionGroup.getId();
        Assert.assertEquals("Test Group1",optionGroup.getName());
        Assert.assertEquals("#223345",optionGroup.getColorHex());
        Assert.assertEquals(false,optionGroup.isActive());
        Assert.assertEquals(1,optionGroup.getParentId());
    }

    @Test
    public void D_activateOptionGroup(){
        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().patch(baseURI+"/"+optionGroupId+"/Activate").thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+"/"+optionGroupId).thenReturn().body();
        OptionGroupResponse optionGroupResponse= new Gson().fromJson(response.asString(),  OptionGroupResponse.class);
        OptionGroup optionGroup= optionGroupResponse.data;
        this.optionGroupId =  optionGroup.getId();
        Assert.assertEquals("Test Group1",optionGroup.getName());
        Assert.assertEquals("#223345",optionGroup.getColorHex());
        Assert.assertEquals(true,optionGroup.isActive());
        Assert.assertEquals(1,optionGroup.getParentId());
    }

    @Test
    public void gerOptionTree(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://staging.eservia.com:8008/api/v0.0/Options/Groups/Tree");
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void F_deleteOptionGroup(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().delete(baseURI+"/"+optionGroupId).thenReturn().body();
        OptionGroupBoolean optionGroupBoolean= new Gson().fromJson(response.asString(),  OptionGroupBoolean.class);
        Assert.assertEquals("success",optionGroupBoolean.getDescription());
    }

    public int J_returnId(){
        return  optionGroupId;
    }


}
