package BookingREST.Businesses;

import Auth.Users.GetUserToken;
import BookingREST.Addresses.AddressData;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Comments.CommentData;
import BookingREST.Favorites.Favorites;
import BookingREST.Favorites.FavoritesResponse;
import BookingREST.Plans.Plan;
import BookingREST.Plans.PlanResponse;
import BookingREST.Promoter.Promoter;
import BookingREST.Promoter.PromoterData;
import BookingREST.Promoter.PromoterResponse;
import BookingREST.Sector.Sector;
import BookingREST.Sector.SectorData;
import BookingREST.Sector.SectorResponse;
import BookingREST.Strategy.Strategy;
import BookingREST.Strategy.StrategyData;
import BookingREST.Strategy.StrategyResponse;
import BookingREST.Plans.PlanData;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;

public class BusinessesTests {
    String token;
    CommentData commentData = new CommentData();
    SectorData sectorData = new SectorData();
    BusinesessData businesessData = new BusinesessData();
    PromoterData promoterData = new PromoterData();
    StrategyData strategyData = new StrategyData();
    PlanData planData = new PlanData();
    AddressData addressData = new AddressData();
    String baseUrl = "https://staging.eservia.com:8083/api/v1.0/businesses/";

    public String randomString(){
        String characters = "abcdefghijklmnopqrstuvwxyz";
        String randomString="";
        int length = 15;
        Random random = new Random();
        char[] text = new char[length];
        for(int i = 0;i<length;i++){
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        for (int i = 0;i<text.length;i++){
            randomString+=text[i];
        }
        return randomString;
    }

    Faker faker = new Faker();
<<<<<<< HEAD
    String sectorName = faker.name().nameWithMiddle().toLowerCase()+faker.name().firstName().toLowerCase();
    String firstName = faker.name().firstName()+faker.name().firstName()+faker.name().firstName().toLowerCase();
    String lastName = faker.name().lastName()+faker.name().firstName()+faker.name().firstName().toLowerCase();
    String name = faker.name().firstName()+faker.name().firstName()+faker.name().firstName().toLowerCase();
    String alias = faker.name().firstName().toLowerCase()+faker.name().firstName().toLowerCase()+faker.name().firstName()+faker.name().firstName().toLowerCase() ;
    String email = faker.name().firstName()+faker.name().firstName()+faker.name().firstName()+"@gmail.com";
=======
    String sectorName = randomString();
    String firstName = randomString();
    String lastName = randomString();
    String name = randomString();
    String alias = randomString();
    String email = randomString()+"@gmail.com";
>>>>>>> Maks
    String phone = faker.regexify("+380[0-9]{9}");
    int sectorId;
    int promoterId;
    int strategyId;
    int businessId;
    int favoritesId;
    int planId;
    String usertoken;
    String uesrId;


        @BeforeClass
        public void tokens(){
            GetUserToken getUserToken= new GetUserToken();
            this.usertoken = getUserToken.GetUserToken();

            GetUserToken getUserToken1= new GetUserToken();
            this.uesrId = getUserToken1.GetUserId();


            AuthBusinessTest getToken = new AuthBusinessTest();
            this.token = getToken.GetAdminToken();


        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(sectorData.createSector(sectorName))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/sectors/").thenReturn().body();
        SectorResponse sectorResponse= new Gson().fromJson(respons.asString(), SectorResponse.class);
        Sector sector = sectorResponse.getData();
         this.sectorId =sector.getId();

        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(promoterData.addPromoters(firstName, lastName, email, phone))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/promoters/").thenReturn().body();
        PromoterResponse promoterResponse = new Gson().fromJson(response.asString(), PromoterResponse.class);
        Promoter addPromoter = promoterResponse.getData();
        this.promoterId = addPromoter.getId();

        ResponseBody responses = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(strategyData.addPromoters(name))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/strategies/").thenReturn().body();
        StrategyResponse strategyResponse = new Gson().fromJson(responses.asString(), StrategyResponse.class);
        Strategy addStrategy = strategyResponse.getData();
        System.out.println(response.asString());
        this.strategyId = addStrategy.getId();

            ResponseBody responseess = given()
                    .contentType(ContentType.JSON)
                    .header("Authorization", token)
                    .body(planData.freePlan(businessId))
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter())
                    .when().post("https://staging.eservia.com:8083/api/v1.0/plans/").thenReturn().body();
            PlanResponse planResponse = new  Gson().fromJson(responseess.asString(), PlanResponse.class);
            Plan plan = planResponse.getData();
            this.planId = plan.getId();


    }

    @Test
    public void A_createBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.createBusinesses(promoterId,1,sectorId,alias))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/businesses/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;
        this.businessId = businesses.getId();

        Assert.assertEquals(promoterId,businesses.getPromoter_id());
        Assert.assertEquals(1,businesses.getStrategy_id());
        Assert.assertEquals(sectorId,businesses.getSector_id());
        Assert.assertEquals("maximum",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія",businesses.getDescription());
        Assert.assertEquals(alias,businesses.getAlias());
        Assert.assertEquals(false,businesses.is_verified);
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java/3.12.0",businesses.getUrl());
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("https://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec",businesses.getLink_facebook());


    }

    @Test
    public void Ba_subscribePlan(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/plans/"+planId+"/subscribe/").thenReturn().body();


    }

    @Test
    public void B_updateBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(businesessData.updateBusiness())
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals("maximum1",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства1",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія1",businesses.getDescription());
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java1/3.12.0",businesses.getUrl());
        Assert.assertEquals("https://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("https://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv1/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec1",businesses.getLink_facebook());
    }

    @Test
    public void C_getBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals(planId, businesses.getPlan_id());
        Assert.assertEquals("maximum1",businesses.getName());
        Assert.assertEquals("Створимо цей заклад на благо людства1",businesses.getShort_description());
        Assert.assertEquals("Стара піцерія1",businesses.getDescription());
        Assert.assertEquals("https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java1/3.12.0",businesses.getUrl());
        Assert.assertEquals("https://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getBackground());
        Assert.assertEquals("https://staging.eservia.com/image/media/2018051/jAgUxCmshMJuFrFl.png",businesses.getLogo());
        Assert.assertEquals("https://www.instagram.com/original.cv1/?hl=ru",businesses.getLink_instagram());
        Assert.assertEquals("https://www.facebook.com/max.lutkovec1",businesses.getLink_facebook());
    }

    @Test
    public void D_verifyBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/verify/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals(true,businesses.is_verified);
    }

    @Test
    public void F_displayBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/display/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals(true,businesses.is_searchable);
    }

    @Test
    public void G_hideBusines(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().patch("https://staging.eservia.com:8083/api/v1.0/businesses/"+businessId+"/hide/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals(false,businesses.is_searchable);
    }




    @Test
    public void H_promoterBusines(){
        this.alias = faker.name().firstName();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .body(businesessData.promoterBusinesses(strategyId,sectorId,alias+"a"))
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/promoters/"+promoterId+"/businesses/").thenReturn().body();
        BusinesessResponse businesessResponse= new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses= businesessResponse.data;

        Assert.assertEquals(false,businesses.is_searchable);
        Assert.assertEquals(alias+"a", businesses.getAlias());
        Assert.assertEquals(true,businesses.getId()>businessId);
    }

    @Test
    public void I_promoterBusines(){
        this.alias = faker.name().firstName();
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8083/api/v1.0/promoters/"+promoterId+"/businesses/").thenReturn().body();
        BusinessArray businessArray= new Gson().fromJson(response.asString(), BusinessArray.class);

    }

    @Test
    public void J_addToFavoriteBusines() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post("https://staging.eservia.com:8083/api/v1.0/businesses/" + businessId + "/favorites/").thenReturn().body();
        FavoritesResponse favoritesResponse= new Gson().fromJson(response.asString(), FavoritesResponse.class);
        Favorites favorites = favoritesResponse.getData().get(0);
        this.favoritesId = favorites.getObject_id();
    }

    @Test
    public void K_getFavoriteBusines() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8083/api/v1.0/businesses/" + businessId + "/favorites/").thenReturn().body();
        FavoritesResponse favoritesResponse= new Gson().fromJson(response.asString(), FavoritesResponse.class);
        Favorites favorites = favoritesResponse.getData().get(0);
        Assert.assertEquals(favoritesId,favorites.getObject_id());
    }

    @Test
    public void L_getUserFavoriteBusines() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get("https://staging.eservia.com:8083/api/v1.0/users/" + uesrId + "/favorites/").thenReturn().body();
        FavoritesResponse favoritesResponse= new Gson().fromJson(response.asString(), FavoritesResponse.class);
        Favorites favorites = favoritesResponse.getData().get(0);
    }

    @Test
    public void M_getAllBusiness(){
            RequestSpecification httpsRequest = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("Authorization",token)
                    .filter(new RequestLoggingFilter())
                    .filter(new ResponseLoggingFilter());
        Response response = httpsRequest.get(baseUrl);
        Assert.assertEquals(200,response.getStatusCode());
    }


    @Test
    public void N_deleteFavoriteBusines() {
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("https://staging.eservia.com:8083/api/v1.0/businesses/" + businessId + "/favorites/").thenReturn().body();
        FavoritesResponse favoritesResponse= new Gson().fromJson(response.asString(), FavoritesResponse.class);
        Favorites favorites = favoritesResponse.getData().get(0);
        this.favoritesId = favorites.getObject_id();
    }



    @Test
    public void O_deleteBusines() {
        ResponseBody response = given().contentType(ContentType.JSON).header("Authorization", token).filter(new RequestLoggingFilter()).filter(new ResponseLoggingFilter()).when().delete("https://staging.eservia.com:8083/api/v1.0/businesses/" + businessId + "/").thenReturn().body();
        BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
        Businesses businesses = businesessResponse.data;
        this.businessId = businesses.getId();
    }

    @AfterClass
    public void deleteBefore() {
        ResponseBody responses = given().contentType(ContentType.JSON)
                .header("Authorization", token).when()
                .delete("https://staging.eservia.com:8083/api/v1.0/plans/"+planId).thenReturn().body();

        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken).when()
                .get("https://staging.eservia.com:8083/api/v1.0/promoters/" + promoterId).thenReturn().body();

        ResponseBody response1 = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken).when()
                .get("https://staging.eservia.com:8083/api/v1.0/sector/" + sectorId).thenReturn().body();

        ResponseBody response2 = given().contentType(ContentType.JSON)
                .header("Authorization", usertoken).when()
                .get("https://staging.eservia.com:8083/api/v1.0/strategy/" + strategyId).thenReturn().body();
    }
    }
