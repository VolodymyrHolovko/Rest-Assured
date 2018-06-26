package BookingREST.Comments;

import Auth.Users.GetUserToken;
import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessResponse;
import BookingREST.Businesses.Businesses;
import BookingREST.Businesses.CreateBusiness;
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

import static com.jayway.restassured.RestAssured.given;

public class CommentsTests {
    String baseUrl = "http://staging.eservia.com:8083/api/v1.0/comments/";
    String usertoken;
    String token;
    int businessId;
    int commentId;
    String userId;
    int promoterId;
    int planId;
    CommentData commentData = new CommentData();
    CreateBusiness createBusiness = new CreateBusiness();



    @BeforeClass
    public void beforeClass(){
        businessId = createBusiness.validBusiness();
        this.planId = createBusiness.returnPlan();
        this.promoterId = createBusiness.returnPromoter();
        GetUserToken getUserToken= new GetUserToken();
        this.usertoken = getUserToken.GetUserToken();

        AuthBusinessTest getToken = new AuthBusinessTest();
        this.token = getToken.GetAdminToken();

        GetUserToken getUserToken1= new GetUserToken();
        this.userId = getUserToken1.GetUserId();
    }

    @Test
    public void A_createComment(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .body(commentData.createComment(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        CommentsResponse commentsResponse= new Gson().fromJson(respons.asString(), CommentsResponse.class);
        Comments comments= commentsResponse.data;
        this.commentId = comments.getId();
        Assert.assertEquals(7,(int)comments.getRating());
        Assert.assertEquals(7,comments.getRating_payload().getConvenience());
        Assert.assertEquals(6,comments.getRating_payload().getPurity());
        Assert.assertEquals(8,comments.getRating_payload().getQuality());
    }

    @Test
    public void B_getComment(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseUrl+commentId+"/").thenReturn().body();
        CommentsResponse commentsResponse= new Gson().fromJson(respons.asString(), CommentsResponse.class);
        Comments comments= commentsResponse.data;
        this.commentId = comments.getId();
        Assert.assertEquals(7,(int)comments.getRating());
        Assert.assertEquals(7,comments.getRating_payload().getConvenience());
        Assert.assertEquals(6,comments.getRating_payload().getPurity());
        Assert.assertEquals(8,comments.getRating_payload().getQuality());
    }

    @Test
    public void C_getAllComments(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get(baseUrl);
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void D_getUserComment() {
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://213.136.86.27:8083/api/v1.0/users/"+userId+"/comments/");
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void E_getAllComments(){
        RequestSpecification httpRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization",token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter());
        Response response = httpRequest.get("http://213.136.86.27:8083/api/v1.0/businesses/"+businessId+"/comments/");
        Assert.assertEquals(200,response.getStatusCode());
    }

    @Test
    public void F_getComment(){
        ResponseBody respons = given()
                .contentType(ContentType.JSON)
                .header("Authorization", usertoken)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseUrl+commentId+"/").thenReturn().body();

    }

    @AfterClass
    public void O_deleteBusines() {
    ResponseBody response = given().contentType(ContentType.JSON)
            .header("Authorization", token)
            .filter(new RequestLoggingFilter())
            .filter(new ResponseLoggingFilter())
            .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + businessId + "/").thenReturn().body();
    BusinesessResponse businesessResponse = new Gson().fromJson(response.asString(), BusinesessResponse.class);
    Businesses businesses = businesessResponse.data;

        ResponseBody respons = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/promoters/" + promoterId + "/").thenReturn().body();
        ResponseBody respon = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/plans/" + planId + "/").thenReturn().body();
    }
}
