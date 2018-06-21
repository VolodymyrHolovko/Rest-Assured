package BookingREST.Gallery;

import BookingREST.AuthBusiness.AuthBusinessTest;
import BookingREST.Businesses.BusinesessData;
import BookingREST.Businesses.CreateBusiness;
import com.google.gson.Gson;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GalleryTests {

    private  String token;
    private  String baseURL = "http://213.136.86.27:8083/api/v1.0/photos/";
    int businessId;
    int galleryId;
    int promoterId;
    int planId;
    GalleryData galleryData= new GalleryData();
    BusinesessData businesessData = new BusinesessData();

    @BeforeClass
    public void getToken(){
        AuthBusinessTest authBusinessTest = new AuthBusinessTest();
        this.token = authBusinessTest.GetAdminToken();
        CreateBusiness createBusiness = new CreateBusiness();
        this.businessId =createBusiness.validBusiness();
        this.planId = createBusiness.returnPlan();
        this.promoterId = createBusiness.returnPromoter();
    }



    @Test
    public void A_addGallery(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(galleryData.addPhoto(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().post(baseURL).thenReturn().body();
        GalleryResponse galleryResponse= new Gson().fromJson(response.asString(), GalleryResponse.class);
        Gallery gallery= galleryResponse.data;
        this.galleryId = gallery.getId();
        Assert.assertEquals("business",gallery.getObject_type());
        Assert.assertEquals(businessId,gallery.getObject_id());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl.png",gallery.getPath());
    }

    @Test
    public void B_updateGallery(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(galleryData.updatePhoto(businessId))
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().put(baseURL+galleryId+"/").thenReturn().body();
        GalleryResponse galleryResponse= new Gson().fromJson(response.asString(), GalleryResponse.class);
        Gallery gallery= galleryResponse.data;
        this.galleryId = gallery.getId();
        Assert.assertEquals("business",gallery.getObject_type());
        Assert.assertEquals(businessId,gallery.getObject_id());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png",gallery.getPath());
    }

    @Test
    public void C_getGallery(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().get(baseURL+galleryId+"/").thenReturn().body();
        GalleryResponse galleryResponse= new Gson().fromJson(response.asString(), GalleryResponse.class);
        Gallery gallery= galleryResponse.data;
        this.galleryId = gallery.getId();
        Assert.assertEquals("business",gallery.getObject_type());
        Assert.assertEquals(businessId,gallery.getObject_id());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png",gallery.getPath());
    }

    @Test
    public void D_deleteGallery(){
        ResponseBody response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete(baseURL+galleryId+"/").thenReturn().body();
        GalleryResponse galleryResponse= new Gson().fromJson(response.asString(), GalleryResponse.class);
        Gallery gallery= galleryResponse.data;
        this.galleryId = gallery.getId();
        Assert.assertEquals("business",gallery.getObject_type());
        Assert.assertEquals(businessId,gallery.getObject_id());
        Assert.assertEquals("http://staging.eservia.com/image/media/201805/jAgUxCmshMJuFrFl1.png",gallery.getPath());
    }

    @AfterClass
    public void deleAfter(){
        ResponseBody response = given().contentType(ContentType.JSON)
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .when().delete("http://213.136.86.27:8083/api/v1.0/businesses/" + businessId + "/").thenReturn().body();
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
