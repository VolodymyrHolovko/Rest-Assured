package Marketing;


import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import token.GetToken;

import static com.jayway.restassured.RestAssured.given;

public class MarketingTest {
    String baseURI = "http://staging.eservia.com:8002/api/v0.0/Marketings";
    String token = "";
    public int ids;
    MarketingData marketingData = new MarketingData();

        @BeforeClass
   public void getToken() {
        this.token="Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJkOGMyOTczNy0wYjFkLTQ1ZmItOTc4OS05ZTAzZDI1ODhmYmIiLCJwcm9tb3RlcklkIjoiMTAiLCJwb3NpdGlvbklkIjoiMSIsImFjY2Vzc1JpZ2h0cyI6ImVtcGxveWVlOnJlYWQsY3JlYXRlLHVwZGF0ZXxvcmRlcjpyZWFkLGNyZWF0ZSx1cGRhdGV8cG9zaXRpb246cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxwcmVzZW5jZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHNoaWZ0VHlwZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHdvcmtTaGlmdDpyZWFkfGRldmljZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGRpbWVuc2lvbjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG1lcmNoYW50OnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8cHJvdmlkZXI6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXx0YWJsZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRhZzpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRheDpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGRlcGFydG1lbnQ6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxkZXBhcnRtZW50R3JvdXA6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxtYXJrZXRpbmc6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxzdG9yYWdlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8dGVjaENhcmQ6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxjYXRlZ29yeTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG5vbWVuY2xhdHVyZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG5vbWVuY2xhdHVyZUdyb3VwOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8b3B0aW9uOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8b3B0aW9uR3JvdXA6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxzaXplOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8c2NhbGU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXx0aW1lUmFuZ2U6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxkaXJlY3RpdmU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxtZW51OnJlYWQsY3JlYXRlfGNvb2tpbmdUeXBlczpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGJvb2tpbmc6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxib29raW5nU2V0dGluZ3M6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxjdXN0b21lcjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGFkZHJlc3NTZXR0aW5nczpyZWFkLHVwZGF0ZXxwcm9tb3RlclNldHRpbmdzOnJlYWQsdXBkYXRlIiwiYnVzaW5lc3NlcyI6IntcImJ1c2luZXNzZXNcIjpbe1wiaWRcIjoxNyxcImFkZHJlc3Nlc1wiOlsyMywyNF19LHtcImlkXCI6NTgsXCJhZGRyZXNzZXNcIjpbMTUyXX1dfSIsIm5iZiI6MTUyMjY1NTk2MywiZXhwIjoxNTIyNzA2MzYzLCJpYXQiOjE1MjI2NTU5NjMsImlzcyI6IlBlcnNvbm5lbFNlcnZpY2UiLCJhdWQiOiJQZXJzb25uZWxTZXJ2aWNlIn0.TjOv47NgMs18FL1wDSbSTyZJbiRQG33MQePPCQ9OEkYWrTp1eya7KtjJPbdcloDRs7ixEm1Li3Zgdenl3p-bPU2BaNQjaqvNSS6ZVtbG3J4WwF7AGvilQUUphf8aBG2jYBOVgmm8rWnOWDsQT2Cy_cVoKGWmqiLlH1tRAWoeGQtxEiGt-ml2mtvjBLsrxi--CbytIIjp9mF3wuKh9oGqTr-3UOyqhNMA_LOUrXABNzHvO4JGTHHkm9o7vzWQ709FR5NfGwMcYGKz-Xq6saIHkn9TbKMC7FDX3pZiueI7vS_3dkd6yMBQYtskGo2Rw9d9LBy1Cd3ZnMeWPeyZL3r1XQ";
    }
    @Test
    public void A_addNewMarketing() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(marketingData.addNewMarketing())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURI).thenReturn().body();
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketing = marketingResponse.data;
        //System.out.println(response.asString());
        this.ids = marketing.getId();
        Assert.assertEquals(17,marketing.getBusinessId());
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
