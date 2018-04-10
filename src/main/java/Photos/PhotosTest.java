package Photos;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.specification.MultiPartSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import sun.text.resources.FormatData;
import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class PhotosTest {
    String baseUrl = "http://staging.eservia.com:8001/api/v0.0/Photo/FormData";
    PhotosData photosData = new PhotosData();
    String token;

    @BeforeClass
    public void getToken(){
        GetToken getToken = new GetToken();
        this.token = getToken.GetFinallyToken();
    }


    @Test
    public void addPhotoFromFolder() {
        ResponseBody response = given()
                .multiPart(new MultiPartSpecBuilder(new File("/Users/volodymyr_holovko/Pictures/604.jpg"))
                        .fileName("my_image.jpg")
                        // controlName is the name of the
                        // RequestParam associated with the
                        // MultipartFile[] array
                        .controlName("file")
                        .mimeType("image/jpg")
                        .build())
               // .header("Authorization", Auth)
                /*.body(photosData.addPhotosFromFolder())*/
                .filter(new RequestLoggingFilter())
                .when().post(baseUrl).thenReturn().body();
        //Photos addPhotoFolder = new Gson().fromJson(response.asString(), Photos.class);
        System.out.println(response.asString());




    }
}

