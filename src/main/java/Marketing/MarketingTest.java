package Marketing;

import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class MarketingTest {
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMDk0NDA4OSwibmJmIjoxNTIwOTQ0MDg5LCJwcm9tb3Rlcl9pZCI6IjI4IiwiZXhwIjoxNTIxMDMwNDg5LCJidXNpbmVzc2VzIjpbMzldfQ.hOcbsnb4xBYOO0ZbTwDjala5TnVu_sBYPxkeWu3JXDfdxdb88tH4Q2AxEwSvsiyQ6dDRN429tl3eWdGJMflc31pXi_QqSD2hRgobs5DUDH3NxqJXMfxEXjtdDvMKQJ4A_aRNCe5WJfHzWbFOL33hujw_ktT5KFo0zPhsGFHrjnyJi7MmcejgiSlV6pw0OEonsloLNMhHd-SgUqyz72Z1eh6G7IKfxef1qbvZR-q0qIJ14wD1-7V4xfXz-UwFbEGW9utXKUCY3ex414eCuOid1oRgPnw_myxgjR_qIWh-FPpeQdNYspzNR5YaZykMiNkhhKtFzRnTABXtM6-GUoncNw";
    String baseURI = "http://staging.eservia.com:8002/api/v0.0/Marketings";
    public String ids;
    public int article;
    MarketingData marketingData = new MarketingData();

//    @BeforeClass
//    public void getToken() {
//        GetToken getToken = new GetToken();
//        this.token=getToken.GetToken();
//    }

    @Test
    public void createNomenclature() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(marketingData.addNewMarketing())
                .when().post(baseURI).thenReturn().body();
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);

    }



}
