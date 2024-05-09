package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.BookingCONSTANTS;
import ge.tbc.tbcitacademy.data.URLS;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BookingSteps {
    JSONObject bookingData = new JSONObject();
    JSONObject bookingDates = new JSONObject();

    public BookingSteps fillFirstname() {
        bookingData.put(BookingCONSTANTS.FIRSTNAME, BookingCONSTANTS.BIDZINA);
        return this;
    }

    public BookingSteps fillLastname(){
        bookingData.put(BookingCONSTANTS.LASTNAME, BookingCONSTANTS.TABAGARI);
        return this;
    }

    public BookingSteps fillTotalPrice(){
        bookingData.put(BookingCONSTANTS.TOTALPRICE, BookingCONSTANTS.PRICEININT);
        return this;
    }
    public BookingSteps setPaidToTrue(){
        bookingData.put(BookingCONSTANTS.DEPOSITPAID, BookingCONSTANTS.TRUE);
        return this;
    }
    public BookingSteps fillBookingDates() {
        bookingDates.put(BookingCONSTANTS.CHECKIN, BookingCONSTANTS.CHECKINDATE);
        bookingDates.put(BookingCONSTANTS.CHECKOUT, BookingCONSTANTS.CHECKOUTDATE);
        bookingData.put(BookingCONSTANTS.BOOKINGDATES, bookingDates);
        return this;
    }

    public BookingSteps fillAdditionalNeeds(){
        bookingData.put(BookingCONSTANTS.ADDITIONALNEEDS, BookingCONSTANTS.LUDI);
        return this;
    }

    public BookingSteps createBookingAndValidateStatusCode() {
        Response response = given()
                .baseUri(URLS.RESTFULURL)
                .contentType(ContentType.JSON)
                .body(bookingData.toString())
                .post("/booking");
        response
                .then()
                .extract()
                .statusCode();
        response
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);
        return this;
    }

    public BookingSteps logIf201(){
        Response response = given()
                .baseUri(URLS.RESTFULURL)
                .when()
                .basePath(URLS.UPDATEBOOKING)
                .get();

        response
                .then()
                .log()
                .ifStatusCodeIsEqualTo(201);
        return this;
    }
}
