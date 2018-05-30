package Photos;

import com.jayway.restassured.builder.MultiPartSpecBuilder;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.response.ResponseBody;

import java.io.File;

import static com.jayway.restassured.RestAssured.given;

public class PhotosTest {
    String baseUrl = "http://staging.eservia.com:8001/api/v0.0/Photo/FormData";
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJib29raW5nLnByb21vdGVyIiwiYXVkIjoiYm9va2luZy5wcm9tb3RlciIsImlhdCI6MTUyMjY2NTIzMiwibmJmIjoxNTIyNjY1MjMyLCJwcm9tb3Rlcl9pZCI6IjI5IiwiZXhwIjoxNTIyNzUxNjMyLCJidXNpbmVzc2VzIjpbeyJpZCI6NDIsImFkZHJlc3NlcyI6WzEwMCwxNDYsMTYxLDE4OV19LHsiaWQiOjU2LCJhZGRyZXNzZXMiOlsxNTFdfV19.Vl7pKboMfKfMdlISJtwBZoizGyGoqSfPIxewDPTY_L2FNx4Zh2XIuCXEiu-ZmKvUtF2ELDPsR3f-6m_tlTV0iyEPHXJQWYTOVe1bH3XzwyqN0_wfgkgJGk8DyCWu93SzYdurxfS3E2v39T6fZu8MqScLWwzkGWkWEVTAPE8zL_FfbJQ6EwsRDn51GaatJD_auE10cBw969l4KzvPbmwe6hQa3jamjBKOvwaCDpQQxAI3P29djZ9RkxZnG8pdP75rJkeeFiLQLGBAGrO0UG_yDX7K5N6BKsOlgAJinXVJnPYxlIlzGXrH_7Cz7JjdAK0Bnsr2BEVpFrkkrubyg2Y1vg";
    PhotosData photosData = new PhotosData();


  // @Test
    public void addPhotoFromFolder() {
        ResponseBody response = given()
                .multiPart(new MultiPartSpecBuilder(new File("C:\\Users\\User\\Desktop\\статус.png"))
                        .fileName("my_image.jpg")
                        // controlName is the name of the
                        // RequestParam associated with the
                        // MultipartFile[] array
                        .controlName("file")
                        .mimeType("image/jpg")
                        .build())
               // .header("Authorization", token)
                /*.body(photosData.addPhotosFromFolder())*/
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .post("http://staging.eservia.com:8001/api/v0.0/Photo/FormData")
                .getBody();
        System.out.println(response.asString());
    }
}

