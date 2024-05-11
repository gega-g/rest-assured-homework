package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.BookingCONSTANTS;
import ge.tbc.tbcitacademy.data.URLS;
import ge.tbc.tbcitacademy.models.bookingData.Booking;
import ge.tbc.tbcitacademy.models.bookingData.Bookingdates;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class POJOBookingSteps {
    Booking booking = new Booking();
    Bookingdates bookingdates = new Bookingdates();
    int bookingID;

    public POJOBookingSteps createBooking(String token) {
        booking.setFirstname(BookingCONSTANTS.BIDZINA);
        booking.setLastname(BookingCONSTANTS.TABAGARI);
        booking.setTotalprice(BookingCONSTANTS.PRICEININT);
        booking.setDepositpaid(BookingCONSTANTS.TRUE);
        bookingdates.setCheckin(BookingCONSTANTS.CHECKINDATE);
        bookingdates.setCheckout(BookingCONSTANTS.CHECKOUTDATE);
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds(BookingCONSTANTS.LUDI);

        JSONObject bookingJson = new JSONObject(booking);

        Response response = given()
                .baseUri(URLS.RESTFULURL)
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(bookingJson.toString())
                .post("/booking");

        response.then().log().all();

        response
                .then()
                .assertThat()
                .statusCode(200);
        bookingID = response.jsonPath().getInt("bookingid");
        return this;
    }

    public POJOBookingSteps validateFirstname() {
        assertThat(booking.getFirstname(), equalTo(BookingCONSTANTS.BIDZINA));
        return this;
    }

    public POJOBookingSteps validateLastname() {
        assertThat(booking.getLastname(), equalTo(BookingCONSTANTS.TABAGARI));
        return this;
    }

    public POJOBookingSteps validateTotalPrice() {
        assertThat(booking.getTotalprice(), equalTo(BookingCONSTANTS.PRICEININT));
        return this;
    }

    public POJOBookingSteps validatePayment() {
        assertThat(booking.isDepositpaid(), equalTo(BookingCONSTANTS.TRUE));
        return this;
    }

    public POJOBookingSteps validateBookingDates() {
        assertThat(booking.getBookingdates().getCheckin(), equalTo(BookingCONSTANTS.CHECKINDATE));
        assertThat(booking.getBookingdates().getCheckout(), equalTo(BookingCONSTANTS.CHECKOUTDATE));
        return this;
    }

    public POJOBookingSteps validateAdditionalNeeds() {
        assertThat(booking.getAdditionalneeds(), equalTo(BookingCONSTANTS.LUDI));
        return this;
    }

    public POJOBookingSteps partialUpdateBooking(String token) {
        booking.setFirstname(BookingCONSTANTS.UPDATEDFIRSTNAME);
        booking.setLastname(BookingCONSTANTS.UPDATEDLASTNAME);
        booking.setTotalprice(BookingCONSTANTS.UPDATEDPRICEININT);

        JSONObject bookingJson = new JSONObject(booking);

        Response response = given()
                .baseUri(URLS.RESTFULURL)
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .body(bookingJson.toString())
                .put("/booking/"+bookingID);

        response.then().log().all();

        response
                .then()
                .assertThat()
                .statusCode(200);
        return this;
    }

    public POJOBookingSteps deleteBooking(String token){
        Response response = given()
                .baseUri(URLS.RESTFULURL)
                .header("Cookie", "token=" + token)
                .contentType(ContentType.JSON)
                .delete("/booking/"+bookingID);
        response
                .then()
                .assertThat()
                .statusCode(201)
                .log().all();
        return this;
    }
}
