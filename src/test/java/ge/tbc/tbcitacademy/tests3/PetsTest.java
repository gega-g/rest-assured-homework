package ge.tbc.tbcitacademy.tests3;

import com.fasterxml.jackson.core.JsonProcessingException;
import ge.tbc.tbcitacademy.steps.PetstoreSteps;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class PetsTest {
    PetstoreSteps petstoreSteps = new PetstoreSteps();
    @Story("Post a new pet using lombok")
    @Test
    public void petTest() throws JsonProcessingException {
        petstoreSteps
                .postUsingLombok();
    }
}
