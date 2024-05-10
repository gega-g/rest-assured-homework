package ge.tbc.tbcitacademy.tests;

import ge.tbc.tbcitacademy.specifications.BookingSpecs;
import ge.tbc.tbcitacademy.steps.BookingSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookingTest {
    @BeforeClass
    public void setUp(){
        BookingSpecs.specs();
    }
    BookingSteps updateBookingDataSteps = new BookingSteps();

    @Test
    public void bookingTest(){
        updateBookingDataSteps
                .fillFirstname()
                .fillLastname()
                .fillTotalPrice()
                .setPaidToTrue()
                .fillBookingDates()
                .fillAdditionalNeeds()
                .createBookingAndValidateStatusCode()
                .logIf201();
    }
}
