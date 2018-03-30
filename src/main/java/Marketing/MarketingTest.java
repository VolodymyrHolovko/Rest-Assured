package Marketing;


import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class MarketingTest {
    String baseURI = "http://staging.eservia.com:8002/api/v0.0/Marketings";
    String token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiIyZGQ1MTY2ZS0xMGY2LTQ2N2YtYWFlMy0wZjEyYjBhNzZhODgiLCJwcm9tb3RlcklkIjoiMTAiLCJwb3NpdGlvbklkIjoiMSIsImFjY2Vzc1JpZ2h0cyI6ImVtcGxveWVlOnJlYWQsY3JlYXRlLHVwZGF0ZXxvcmRlcjpyZWFkLGNyZWF0ZSx1cGRhdGV8cG9zaXRpb246cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxwcmVzZW5jZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHNoaWZ0VHlwZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHdvcmtTaGlmdDpyZWFkfGRldmljZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfGRpbWVuc2lvbjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG1lcmNoYW50OnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8cHJvdmlkZXI6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXx0YWJsZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRhZzpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRheGU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxkZXBhcnRtZW50OnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8ZGVwYXJ0bWVudEdyb3VwOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8bWFya2V0aW5nOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8c3RvcmFnZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHRlY2hDYXJkOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8Y2F0ZWdvcnk6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxub21lbmNsYXR1cmU6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxub21lbmNsYXR1cmVHcm91cDpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG9wdGlvbjpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfG9wdGlvbkdyb3VwOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8c2l6ZTpyZWFkLGNyZWF0ZSx1cGRhdGUsZGVsZXRlfHNjYWxlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8dGltZVJhbmdlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8ZGlyZWN0aXZlOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8bWVudTpyZWFkLGNyZWF0ZXxjb29raW5nVHlwZXM6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZXxib29raW5nOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8Ym9va2luZ1NldHRpbmdzOnJlYWQsY3JlYXRlLHVwZGF0ZSxkZWxldGV8Y3VzdG9tZXI6cmVhZCxjcmVhdGUsdXBkYXRlLGRlbGV0ZSIsImJ1c2luZXNzZXMiOiJ7XCJidXNpbmVzc2VzXCI6W3tcImlkXCI6MTcsXCJhZGRyZXNzZXNcIjpbMjMsMjRdfSx7XCJpZFwiOjU4LFwiYWRkcmVzc2VzXCI6WzE1Ml19XX0iLCJuYmYiOjE1MjIzOTIzMzAsImV4cCI6MTUyMjQ0MjczMCwiaWF0IjoxNTIyMzkyMzMwLCJpc3MiOiJQZXJzb25uZWxTZXJ2aWNlIiwiYXVkIjoiUGVyc29ubmVsU2VydmljZSJ9.MvSOjitQJ8F__XyFvJXs0o-X69vuoJ3_-fblGPziR3-GT4pRsFHxQ21wwk1hFArlMP7tfICiDxIcxtk6OhDlbctET1B_w-GUD1rxDmqsOuZVsH-WLRW8zlozTHMRdIKFXX9sCUuWBWZnESfCQ6GV7zE7TC47qoR54dnDnH1I_VyVvFAKgqY_iVvPoXo-JkcaiUup2eXQGlnNrMj89AU3zFvZtl2D951SBIg9nLRooDrwq-qotix0P73TapWIER_RWl9-p-wrHkELZ8PWNiVKREh8_6FzLhafdRgDFSC4JLLNVT6fVnoPfUWocl6cVsdN4p77SHZmkZTlcNIHvOzB2A";
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
