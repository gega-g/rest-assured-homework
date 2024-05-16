package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.PlanetsCONSTANTS;
import ge.tbc.tbcitacademy.data.URLS;
import ge.tbc.tbcitacademy.models.planetsData.PlanetsData;
import ge.tbc.tbcitacademy.models.planetsData.ResultsItem;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PlanetsSteps {
    @Step("Finding three most recent created dates")
    public PlanetsSteps threeMostRecent(PlanetsData planetsData) {

        List<ResultsItem> resultsItems = planetsData.results();
        resultsItems.sort(Comparator.comparing(ResultsItem::created).reversed());

        for (int i=0; i<3; i++) {
            System.out.println(resultsItems.get(i).name());
        }
        return this;
    }

    @Step("Validating that five different fields aren't empty and match validations")
    public PlanetsSteps fiveFieldValidations(){
        given()
                .filter(new AllureRestAssured())
                .baseUri(URLS.SWAPIURL)
                .contentType(ContentType.JSON)
                .get(URLS.PLANETS)
                .then()
                .rootPath("results")
                .body("climate", everyItem(not(emptyString())))
                .body("population", everyItem(matchesPattern(PlanetsCONSTANTS.POPULATIONREGEX)))
                .body("url", everyItem(matchesPattern(PlanetsCONSTANTS.URLREGEX)))
                .body("name", everyItem(matchesPattern(PlanetsCONSTANTS.NAMEREGEX)))
                .body("climate", everyItem(not(emptyString())));
        return this;
    }

    @Step("Finding planet with most rotation time")
    public PlanetsSteps topRotationPlanet(PlanetsData planetsData) throws URISyntaxException, IOException {
        List<ResultsItem> resultsItems = planetsData.results();
        resultsItems.sort(Comparator.comparing(ResultsItem::rotationPeriod).reversed());
        ResultsItem planetWithMostRotationPeriod = resultsItems.get(0);
        Desktop.getDesktop().browse(new URI(planetWithMostRotationPeriod.residents().get(0).toString()));
        return this;
    }
}
