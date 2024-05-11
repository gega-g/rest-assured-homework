package ge.tbc.tbcitacademy.tests2;

import ge.tbc.tbcitacademy.configTests.Config;
import ge.tbc.tbcitacademy.steps.POJOBookingSteps;
import org.testng.annotations.Test;

public class RestfulBookerTest extends Config {
    POJOBookingSteps pojoBookingSteps = new POJOBookingSteps();

    @Test
    public void bookingTest(){
        pojoBookingSteps
                .createBooking(token)
                .partialUpdateBooking(token)
                .deleteBooking(token);
    }
}
