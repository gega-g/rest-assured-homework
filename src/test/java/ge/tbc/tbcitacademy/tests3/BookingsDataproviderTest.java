package ge.tbc.tbcitacademy.tests3;

import ge.tbc.tbcitacademy.dataprovider.BookingDataprovider;
import ge.tbc.tbcitacademy.models.bookingData.Booking;
import ge.tbc.tbcitacademy.steps.BookingSteps;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class BookingsDataproviderTest {
    BookingSteps bookingSteps = new BookingSteps();
    @Story("Changing field order using lombok, creating booking, asserting status code and field value")
    @Test(dataProviderClass = BookingDataprovider.class, dataProvider = "bookingDataProvider")
    public void testBooking(Booking booking) {
        bookingSteps
                .assertStatusCode(booking)
                .checkFirstnameField(booking);
    }

}
