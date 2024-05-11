package ge.tbc.tbcitacademy.util;

import ge.tbc.tbcitacademy.models.planetsData.PlanetsData;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ExtractPlanetsData {
    public PlanetsData extractPlanetData(){
        PlanetsData planetsData = given()
                .baseUri("https://swapi.dev")
                .contentType(ContentType.JSON)
                .get("/api/planets/?format=json")
                .then()
                .extract().body().as(PlanetsData.class);
        return planetsData;
    }
}
