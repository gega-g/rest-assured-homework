package ge.tbc.tbcitacademy.tests3;

import ge.tbc.tbcitacademy.models.planetsData.PlanetsData;
import ge.tbc.tbcitacademy.steps.PlanetsSteps;
import ge.tbc.tbcitacademy.util.ExtractPlanetsData;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class PlanetsTest {
    PlanetsSteps planetsSteps = new PlanetsSteps();
    PlanetsData planetsData = new ExtractPlanetsData().extractPlanetData();

    @Test
    @Story("Get three most recent planets, creating five different pattern validations and getting planet with most rotation time")
    public void first() throws URISyntaxException, IOException {
        planetsSteps
                .threeMostRecent(planetsData)
                .fiveFieldValidations()
                .topRotationPlanet(planetsData);
    }
}
