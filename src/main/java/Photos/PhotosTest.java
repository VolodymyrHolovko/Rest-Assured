package Photos;

import Auth.GetToken;
import com.google.gson.Gson;
import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
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
                .contentType("multiparts/jpg")
                .accept("application/json")
                .header("Authorization", token)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .multiPart(new File("C:\\Users\\User\\Desktop.квартира.png"))
                .post("http://staging.eservia.com:8001/api/v0.0/Photo/FormData")
                .getBody();
        System.out.println(response.asString());
    }
}

