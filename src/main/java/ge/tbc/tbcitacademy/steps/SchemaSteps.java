package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.URLS;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaSteps {
    @Step("Validating that JSON on webpage matches desired schema")
    public SchemaSteps validateSchema(){
        given()
                .filter(new AllureRestAssured())
                .when()
                .get(URLS.FAKERAPIURL)
                .then()
                .log().all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("src/main/java/ge/tbc/tbcitacademy/resources/fakerapi-schema.json"));
        return this;
    }
}
