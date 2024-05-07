package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.BookingData;
import ge.tbc.tbcitacademy.data.URLS;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BookingSteps {
    JSONObject bookingData = new JSONObject();
    JSONObject bookingDates = new JSONObject();

    public BookingSteps fillFirstname() {
        bookingData.put(BookingData.FIRSTNAME, BookingData.BIDZINA);
        return this;
    }

    public BookingSteps fillLastname(){
        bookingData.put(BookingData.LASTNAME, BookingData.TABAGARI);
        return this;
    }

    public BookingSteps fillTotalPrice(){
        bookingData.put(BookingData.TOTALPRICE, 2000);
        return this;
    }
    public BookingSteps setPaidToTrue(){
        bookingData.put(BookingData.DEPOSITPAID, true);
        return this;
    }
    public BookingSteps fillBookingDates() {
        bookingDates.put(BookingData.CHECKIN, BookingData.CHECKINDATE);
        bookingDates.put(BookingData.CHECKOUT, BookingData.CHECKOUTDATE);
        bookingData.put(BookingData.BOOKINGDATES, bookingDates);
        return this;
    }

    public BookingSteps fillAdditionalNeeds(){
        bookingData.put(BookingData.ADDITIONALNEEDS, BookingData.LUDI);
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
