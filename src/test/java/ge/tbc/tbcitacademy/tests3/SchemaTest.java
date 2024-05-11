package ge.tbc.tbcitacademy.tests3;

import ge.tbc.tbcitacademy.steps.SchemaSteps;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class SchemaTest {

    SchemaSteps steps = new SchemaSteps();
    @Story("Validating that url schema is following desired pattern")
    @Test
    public void schemaTest(){
        steps
                .validateSchema();
//Schema to use cannot be null ვერგავასწორე და როგორ უნდა მექნა
    }
}
