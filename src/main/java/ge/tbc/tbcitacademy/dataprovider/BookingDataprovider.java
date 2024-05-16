package ge.tbc.tbcitacademy.dataprovider;

import ge.tbc.tbcitacademy.data.BookingCONSTANTS;
import ge.tbc.tbcitacademy.models.bookingData.Booking;
import ge.tbc.tbcitacademy.models.bookingData.Bookingdates;
import org.testng.annotations.DataProvider;

public class BookingDataprovider {
    @DataProvider(name = "bookingDataProvider")
    public Object[][] bookingData() {
        return new Object[][] {
                { new Booking(
                        1,
                        new Bookingdates(BookingCONSTANTS.CHECKINDATE, BookingCONSTANTS.CHECKOUTDATE),
                        BookingCONSTANTS.LASTNAME1,
                        1000,
                        BookingCONSTANTS.FIRSTNAME1,
                        true,
                        BookingCONSTANTS.DRINK,
                        null)
                },
                { new Booking(
                        2,
                        new Bookingdates(BookingCONSTANTS.CHECKINDATE2, BookingCONSTANTS.CHECKINDATE2),
                        BookingCONSTANTS.LASTNAME2,
                        4400,
                        BookingCONSTANTS.FIRSTNAME2,
                        false,
                        BookingCONSTANTS.BREAKFAST,
                        null) },
        };
    }
}
