package Marketing;

import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class MarketingTest {
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMTEyMTQ1NiwibmJmIjoxNTIxMTIxNDU2LCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIxMjA3ODU2LCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxMzcsMTQ2LDE2MV19LHsiaWQiOjU2LCJhZGRyZXNzZXMiOlsxNTFdfV19.kMiTyEO4033YX4bzhyb8u-TkVwvqCjIfrcOKdhumjQtb1EL3IcUW330cumg3dvDkf1CWSMbsu963hmYzfu7iTnfNtYJZ38D7QYTnvztKLog1ctd8Y7A9GH9cSQQ_c5N6GLRXIlLuDYOop1ZvVfoECX2V3Z2RwkShiA9Jm9Rq1SodfHin3Ppsxb5rQEMcOWwXQUykZcG42bhksyrKvZscfeo5U5G0kEuit0khhxYiLUhER1ZHGmTMEldcDa0WZubA9ECKx-9z8OYZCluDVPCGfEcL7MkU43ZZsWvQ3ezxdxb6E9NBorGxejtLatMiOfJzUDFeOnlieSyRJrzTTgD9zQ";
    String baseURI = "http://staging.eservia.com:8002/api/v0.0/Marketings";
    public long ids;
    MarketingData marketingData = new MarketingData();

//    @BeforeClass
//    public void getToken() {
//        GetToken getToken = new GetToken();
//        this.token=getToken.GetToken();
//    }

    @Test
    public void addNewMarketing() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(marketingData.addNewMarketing())
                .when().post(baseURI).thenReturn().body();
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketing = marketingResponse.data;
        System.out.println(response.asString());
        this.ids = marketing.getId();
        Assert.assertEquals("title", marketing.getTitle());
        Assert.assertEquals("description", marketing.getDescription());
        Assert.assertEquals("media/201803/eNOt8jcLD1hC7hP0.jpg", marketing.getPathToPhoto());
        Assert.assertEquals("address", marketing.getAddress());
        Assert.assertEquals(2,marketing.getMarketingTypeId());
        Assert.assertEquals(23.00,marketing.getLongitude());
        Assert.assertEquals(22.00,marketing.getLatitude());
        Assert.assertEquals("2018-03-29T11:50:30.000", marketing.getBeginTime());
        Assert.assertEquals("2018-04-15T11:45:30.000",marketing.getEndTime());
        Assert.assertEquals(true, marketing.isActive());








    }


}
