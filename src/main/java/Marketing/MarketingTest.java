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
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjc1ODQ3MiwibmJmIjoxNTIyNzU4NDcyLCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIyODQ0ODcyLCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxNDYsMTYxLDE4OV19LHsiaWQiOjU2LCJhZGRyZXNzZXMiOlsxNTFdfV19.TStCq1MwChO54bXNZ7tXl_DiONqCRmOlFXiivltoKanjn2DaUFmtvTID9oQeS7omaiaE8z_Yih2oVisWDSZLRkGoa3DW42aJ09tB8Rm8mXZnzcHOUGduj_8ghKKA0egMAGj_RLwfUCtG46XBmA4THSUZhvm48VWr3uNSV6_g4ZPH7bAEd1_gh1IuZ9p4mZXhYk_-0JzhGCPcARWbp8a_UpM5SQ3vVqdUkoYQhLfi8RbJhsm2UkIloVxPNQWm7p94wiPPaloiIF3h5GBJpeRGct_NPerptJBmacife3ULWu-qo-dGZ-bADSIN2bMIyl8B9_stNHs1Pf6y7s6CtgsLAg";
    String tokenCustomer = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1laWQiOiJkNWEwZDc3Ny0yMGU5LTQwYTktOWY4YS05MzhlOTAxN2MzNGYiLCJ1bmlxdWVfbmFtZSI6IiszODA5Nzk4MTI4NTkiLCJpYXQiOjE1MjI3NTg1MTQsImdpdmVuX25hbWUiOiJWb2xvZHlteXJyIiwiZmFtaWx5X25hbWUiOiIiLCJlbWFpbCI6Im1hcmt1cy5jYWJyZXJhOTQyMUBnbWFpbC5jb20iLCJuYmYiOjE1MjI3NTg1MTQsImV4cCI6MTUyMjc2MDMxNCwiaXNzIjoiZXNlcnZpYSIsImF1ZCI6ImVzZXJ2aWEifQ.BujaH247wKNd_UYi1ZjOZUFYvvoJqTt2oZ4fBB4sEklWzX0SCKTLo6vlUV-58AvTAcc1N1BmcYx5kpu6HmPn5PDur8_yW1kKOaJDIWxLNWOY2gobLVlMmaRtC4d5C9B13wNMkwbOPWqGQVfJXXv48r-yyMP48mlS-yMEBue3Tszh9Yn1rrIXJxRu5wOH7xe0vDO7gAtLqHZ9cT_lO6FoxYxZZbwBeROnGaAsKSEVz0mPvTyZjIn69upUitK0ejXZzG3fXouRIOquLLYY-Ta33pOB7sfnPuWeMVdnRr49KCEYjaq3GMpNNQJiqQbCeIsQ5oK_Vg_MX2P95YbT8FCZSQ" +
            "" +
            "";
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
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
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
    public  void B_getMarketingById() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+ "/" + ids).thenReturn().body();
        System.out.println("asdasdad"+response.asString());
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketingGet = marketingResponse.data;
        Assert.assertEquals(ids, marketingGet.getId());
        Assert.assertEquals("title", marketingGet.getTitle());
        Assert.assertEquals("description", marketingGet.getDescription());
        Assert.assertEquals("media/201803/eNOt8jcLD1hC7hP0.jpg", marketingGet.getPathToPhoto());
        Assert.assertEquals("address", marketingGet.getAddress());
        Assert.assertEquals(2,marketingGet.getMarketingTypeId());
        Assert.assertEquals(23.00,marketingGet.getLongitude());
        Assert.assertEquals(22.00,marketingGet.getLatitude());
        Assert.assertEquals("2018-03-18T11:50:30.000", marketingGet.getBeginTime());
        Assert.assertEquals("2018-04-15T11:45:30.000",marketingGet.getEndTime());
        Assert.assertEquals(true, marketingGet.isActive());
        Assert.assertEquals(1, marketingGet.getLinks().size());
        Assert.assertEquals(1, marketingGet.getLinks().get(0).getSocialTypeId());
        Assert.assertEquals("http://facebook.com",marketingGet.getLinks().get(0).getUrl());
        Assert.assertEquals(1,marketingGet.getWorkSchedule().size());
        Assert.assertEquals(1,marketingGet.getWorkSchedule().get(0).getDay());
        Assert.assertEquals(1521472003,marketingGet.getWorkSchedule().get(0).getStartTime());
        Assert.assertEquals(1523718403,marketingGet.getWorkSchedule().get(0).getEndTime());
    }
    @Test
    public void C_getMarketingByCustomer() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", tokenCustomer)
                .header("EstablishmentContextId", "1")
                .when().get(baseURI+ "/" + ids).thenReturn().body();
        MarketingResponse marketingResponse = new Gson().fromJson(response.asString(), MarketingResponse.class);
        Marketing marketingGetCustomer = marketingResponse.data;
        Assert.assertEquals(ids, marketingGetCustomer.getId());
        Assert.assertEquals("title", marketingGetCustomer.getTitle());
        Assert.assertEquals("description", marketingGetCustomer.getDescription());
        Assert.assertEquals("media/201803/eNOt8jcLD1hC7hP0.jpg", marketingGetCustomer.getPathToPhoto());
        Assert.assertEquals("address", marketingGetCustomer.getAddress());
        Assert.assertEquals(2,marketingGetCustomer.getMarketingTypeId());
        Assert.assertEquals(23.00,marketingGetCustomer.getLongitude());
        Assert.assertEquals(22.00,marketingGetCustomer.getLatitude());
        Assert.assertEquals("2018-03-18T11:50:30.000", marketingGetCustomer.getBeginTime());
        Assert.assertEquals("2018-04-15T11:45:30.000",marketingGetCustomer.getEndTime());
        Assert.assertEquals(true, marketingGetCustomer.isActive());
        Assert.assertEquals(1, marketingGetCustomer.getLinks().size());
        Assert.assertEquals(1, marketingGetCustomer.getLinks().get(0).getSocialTypeId());
        Assert.assertEquals("http://facebook.com",marketingGetCustomer.getLinks().get(0).getUrl());
        Assert.assertEquals(1,marketingGetCustomer.getWorkSchedule().size());
        Assert.assertEquals(1,marketingGetCustomer.getWorkSchedule().get(0).getDay());
        Assert.assertEquals(1521472003,marketingGetCustomer.getWorkSchedule().get(0).getStartTime());
        Assert.assertEquals(1523718403,marketingGetCustomer.getWorkSchedule().get(0).getEndTime());
    }
    @Test
    public void D_updateMarketing() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .header("EstablishmentContextId", "1")
                .body(marketingData.updateMarketing(ids))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
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
    public void E_deleteMarketing() {
    String deleteUrl = baseURI+"/"+ids;
    ResponseBody response = given().contentType(ContentType.JSON)
            .header("Authorization", token)
            .header("EstablishmentContextId", "1")
            .when().delete(deleteUrl).thenReturn().body();
            System.out.println(response.asString());
        ResponseBody responseGet = given().contentType(ContentType.JSON)
                 .header("Authorization", token)
                 .header("EstablishmentContextId", "1")
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                 .when().get(deleteUrl).thenReturn().body();
         MarketingErrors errors = new Gson().fromJson(responseGet.asString(), MarketingErrors.class);
         System.out.println(responseGet.asString());
         Assert.assertEquals("success", errors.getDescription());
         Assert.assertEquals(true, errors.isSuccess());
     }
}
