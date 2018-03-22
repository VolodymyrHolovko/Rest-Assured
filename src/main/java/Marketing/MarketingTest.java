package Marketing;


import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class MarketingTest {
    String baseURI = "http://staging.eservia.com:8002/api/v0.0/Marketings";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMTYyMTQ5OCwibmJmIjoxNTIxNjIxNDk4LCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIxNzA3ODk4LCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxNDYsMTYxXX0seyJpZCI6NTYsImFkZHJlc3NlcyI6WzE1MV19XX0.azE8o_XOAveVNYKsKvNyCnBzPORFlh-GjdKX1Mts5d72hH2PSNZjci5fG3n7zos-FLAIajQZ6najy9K9t3Yk5RNfy5zDRamKbs_cmtsCYpXss_Q4s0YlFZFi4R3lSf6XRTDBrpd_BxMWe8cLXFSjxu161mXHoYPsuFHPRH4pOf7GJiM519U78nIpXCGfW64DiqQhGpy3d4TuUYCpW2RGrMsFqwxgftrtCULVn-i59XHSNg5rjx56-MzvTO0eW6Txo-z9RdWssSweek0icaRaMrBBIFXmRpar2xgbY7AQZXQMUts_S3DXihVxez7c_1l0F-I2s_W3Q42HIKvWTnDNQA";
    public int ids;
    MarketingData marketingData = new MarketingData();
    /*
        @BeforeClass
   public void getToken() {
       GetToken getToken = new GetToken();
        this.token1=getToken.GetToken();
    }*/
    @Test
    public void A_addNewMarketing() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(marketingData.addNewMarketing())
                .when().post(baseURI).thenReturn().body();
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketing = marketingResponse.data;
        //System.out.println(response.asString());
        this.ids = marketing.getId();
        Assert.assertEquals("title", marketing.getTitle());
        Assert.assertEquals("description", marketing.getDescription());
        Assert.assertEquals("media/201803/eNOt8jcLD1hC7hP0.jpg", marketing.getPathToPhoto());
        Assert.assertEquals("address", marketing.getAddress());
        Assert.assertEquals(2,marketing.getMarketingTypeId());
        Assert.assertEquals(23.00,marketing.getLongitude());
        Assert.assertEquals(22.00,marketing.getLatitude());
        Assert.assertEquals("2018-03-18T11:50:30.000", marketing.getBeginTime());
        Assert.assertEquals("2018-04-15T11:45:30.000",marketing.getEndTime());
        /*Assert.assertEquals(true, marketing.isActive());*/
        Assert.assertEquals(1, marketing.getLinks().size());
        Assert.assertEquals(1, marketing.getLinks().get(0).getSocialTypeId());
        Assert.assertEquals("http://facebook.com",marketing.getLinks().get(0).getUrl());
        Assert.assertEquals(1,marketing.getWorkSchedule().size());
        Assert.assertEquals(1,marketing.getWorkSchedule().get(0).getDay());
        Assert.assertEquals(1521472003,marketing.getWorkSchedule().get(0).getStartTime());
        Assert.assertEquals(1523718403,marketing.getWorkSchedule().get(0).getEndTime());
    }
    @Test
    public void B_updateMarketing() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(marketingData.updateMarketing(ids))
                .when().put(baseURI).thenReturn().body();
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketingupdate = marketingResponse.data;
        System.out.println(response.asString());
        Assert.assertEquals("title_update", marketingupdate.getTitle());
        Assert.assertEquals("description_update", marketingupdate.getDescription());
        Assert.assertEquals("media/201803/mhGdSTf7kdT0LfdS.jpg", marketingupdate.getPathToPhoto());
        Assert.assertEquals(2, marketingupdate.getMarketingTypeId());
        Assert.assertEquals("adress_update", marketingupdate.getAddress());
        Assert.assertEquals(24.00, marketingupdate.getLongitude());
        Assert.assertEquals(25.00, marketingupdate.getLatitude());
        Assert.assertEquals("2018-03-29T11:55:33.000", marketingupdate.getBeginTime());
        Assert.assertEquals("2018-03-30T11:42:32.000", marketingupdate.getEndTime());
        Assert.assertEquals(false, marketingupdate.isActive());
        Assert.assertEquals(1,marketingupdate.getLinks().size());
        Assert.assertEquals(2,marketingupdate.getLinks().get(0).getSocialTypeId());
        Assert.assertEquals("http://www.instagram.com/",marketingupdate.getLinks().get(0).getUrl());
        Assert.assertEquals(1,marketingupdate.getWorkSchedule().size());
        Assert.assertEquals(2,marketingupdate.getWorkSchedule().get(0).getDay());
        Assert.assertEquals(1520765319,marketingupdate.getWorkSchedule().get(0).getStartTime());
        Assert.assertEquals(1521370119,marketingupdate.getWorkSchedule().get(0).getEndTime());
    }
     @Test
    public void C_deleteMarketing() {
    String deleteUrl = baseURI+"/"+ids;
    ResponseBody response = given().contentType(ContentType.JSON)
            .header("Authorization", token)
            .header("EstablishmentContextId", "1")
            .when().delete(deleteUrl).thenReturn().body();
         System.out.println(response.asString());

          ResponseBody responseGet = given().contentType(ContentType.JSON)
                 .header("Authorization", token)
                 .header("EstablishmentContextId", "1")
                 .when().get(deleteUrl).thenReturn().body();
           Error errors = new Gson().fromJson(responseGet.asString(), Error.class);
           System.out.println(responseGet.asString());
         //Marketing marketingdeleteCheck = marketingResponse.data;
         //Assert.assertEquals("Marketing does not exist", marketingResponse.getErrorDescription());
     }
}
