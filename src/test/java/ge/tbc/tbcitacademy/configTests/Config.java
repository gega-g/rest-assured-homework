package ge.tbc.tbcitacademy.configTests;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class Config {
    protected String username;
    protected String password;
    protected String token;
    @BeforeClass
    public void extractToken(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/java/ge/tbc/tbcitacademy/resources/config.properties"));

            username = properties.getProperty("restful-booker-username");
            password = properties.getProperty("restful-booker-password");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        token = given()
                .contentType(ContentType.JSON)
                .body("{ \"username\": \"admin\", \"password\": \"password123\" }")
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then()
                .extract()
                .path("token");
    }
}
