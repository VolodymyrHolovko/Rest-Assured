package Marketing;


import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class MarketingTest {
    String baseURI = "http://staging.eservia.com:8002/api/v0.0/Marketings";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMTUzNzg3MCwibmJmIjoxNTIxNTM3ODcwLCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIxNjI0MjcwLCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxNDYsMTYxXX0seyJpZCI6NTYsImFkZHJlc3NlcyI6WzE1MV19XX0.Y46ws9lkDLV9ssMRlBs2QrxWDgMv3Q8mmjD49LFXT2kyYOR0Mqoa1tljR24AygQ4tADx9Y3aj9QZVRPgq3VWtXaqxjb5pUVbzZtiOXMEztcZylOucfhD91qdv8RGywEtRJRxR0NRlMjjozWm3Sd0dc0XZa9AT5eH3yIwOL-yhCdyPUqZUQQzztKzTPpN__EgNTPsOibcueeVniA2voduAxz5rWWWvYd924x1G6P-aKRXRSwdeZn46RifgjhXQAnTiWKlgYkdHWifawF41uJBts6O9cG3NQxOyr9rcmlzf3RwkQTuGMA8r7h1E4_94VeD1dXnrz5zijD7vAg-NEQ6Cw";
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
        System.out.println(response.asString());
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
         MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
         Marketing marketingdelete = marketingResponse.data;
         System.out.println(response.asString());

         ResponseBody responseGet = given().contentType(ContentType.JSON)
                 .header("Authorization", token)
                 .header("EstablishmentContextId", "1")
                 .when().get(deleteUrl).thenReturn().body();
         System.out.println(responseGet.asString());

     }
}
