package ge.tbc.tbcitacademy.tests2;

import ge.tbc.tbcitacademy.steps.DriverTableSteps;
import org.testng.annotations.Test;

public class DriversTest {
    DriverTableSteps driverTableSteps = new DriverTableSteps();

    @Test
    public void driversTest(){
        driverTableSteps
                .getFirstDriver();
    }
}
