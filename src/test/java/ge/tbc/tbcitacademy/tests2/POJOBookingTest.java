package ge.tbc.tbcitacademy.tests2;

import ge.tbc.tbcitacademy.configTests.Config;
import ge.tbc.tbcitacademy.steps.POJOBookingSteps;
import org.testng.annotations.Test;

public class POJOBookingTest extends Config {
    POJOBookingSteps pojoBookingSteps = new POJOBookingSteps();

    @Test
    public void POJObookingTest(){
        pojoBookingSteps
                .createBooking(token)
                .validateFirstname()
                .validateLastname()
                .validateTotalPrice()
                .validatePayment()
                .validateBookingDates()
                .validateAdditionalNeeds();
    }
}
