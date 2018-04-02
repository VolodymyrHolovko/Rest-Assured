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
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjY2NTIzMiwibmJmIjoxNTIyNjY1MjMyLCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIyNzUxNjMyLCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxNDYsMTYxLDE4OV19LHsiaWQiOjU2LCJhZGRyZXNzZXMiOlsxNTFdfV19.Vl7pKboMfKfMdlISJtwBZoizGyGoqSfPIxewDPTY_L2FNx4Zh2XIuCXEiu-ZmKvUtF2ELDPsR3f-6m_tlTV0iyEPHXJQWYTOVe1bH3XzwyqN0_wfgkgJGk8DyCWu93SzYdurxfS3E2v39T6fZu8MqScLWwzkGWkWEVTAPE8zL_FfbJQ6EwsRDn51GaatJD_auE10cBw969l4KzvPbmwe6hQa3jamjBKOvwaCDpQQxAI3P29djZ9RkxZnG8pdP75rJkeeFiLQLGBAGrO0UG_yDX7K5N6BKsOlgAJinXVJnPYxlIlzGXrH_7Cz7JjdAK0Bnsr2BEVpFrkkrubyg2Y1vg";
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
        Assert.assertEquals(42, marketing.getBusinessId());
        Assert.assertEquals("title", marketing.getTitle());
        Assert.assertEquals("description", marketing.getDescription());
        Assert.assertEquals("media/201803/eNOt8jcLD1hC7hP0.jpg", marketing.getPathToPhoto());
        Assert.assertEquals("address", marketing.getAddress());
        Assert.assertEquals(2,marketing.getMarketingTypeId());
        Assert.assertEquals(23.00,marketing.getLongitude());
        Assert.assertEquals(22.00,marketing.getLatitude());
        Assert.assertEquals("2018-03-18T11:50:30.000", marketing.getBeginTime());
        Assert.assertEquals("2018-04-15T11:45:30.000",marketing.getEndTime());
        Assert.assertEquals(true, marketing.isActive());
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
    public  void C_getMarketingById() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+ "/" + ids).thenReturn().body();
        System.out.println("asdasdad"+response.asString());
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketingGet = marketingResponse.data;
        Assert.assertEquals(ids, marketingGet.getId());
        Assert.assertEquals("title_update", marketingGet.getTitle());
        Assert.assertEquals("description_update", marketingGet.getDescription());
        Assert.assertEquals("media/201803/mhGdSTf7kdT0LfdS.jpg", marketingGet.getPathToPhoto());
        Assert.assertEquals(2, marketingGet.getMarketingTypeId());
        Assert.assertEquals("adress_update", marketingGet.getAddress());
        Assert.assertEquals(24.00, marketingGet.getLongitude());
        Assert.assertEquals(25.00, marketingGet.getLatitude());
        Assert.assertEquals("2018-03-29T11:55:33.000", marketingGet.getBeginTime());
        Assert.assertEquals("2018-03-30T11:42:32.000", marketingGet.getEndTime());
        Assert.assertEquals(false, marketingGet.isActive());
        Assert.assertEquals(1,marketingGet.getLinks().size());
        Assert.assertEquals(2,marketingGet.getLinks().get(0).getSocialTypeId());
        Assert.assertEquals("http://www.instagram.com/",marketingGet.getLinks().get(0).getUrl());
        Assert.assertEquals(1,marketingGet.getWorkSchedule().size());
        Assert.assertEquals(2,marketingGet.getWorkSchedule().get(0).getDay());
        Assert.assertEquals(1520765319,marketingGet.getWorkSchedule().get(0).getStartTime());
        Assert.assertEquals(1521370119,marketingGet.getWorkSchedule().get(0).getEndTime());
    }
     @Test
    public void D_deleteMarketing() {
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
         MarketingErrors errors = new Gson().fromJson(responseGet.asString(), MarketingErrors.class);
         System.out.println(responseGet.asString());
         Assert.assertEquals("success", errors.getDescription());
         Assert.assertEquals(true, errors.isSuccess());
     }
}
