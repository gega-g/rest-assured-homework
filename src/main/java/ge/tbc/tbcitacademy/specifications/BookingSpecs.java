package ge.tbc.tbcitacademy.specifications;

import ge.tbc.tbcitacademy.data.URLS;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BookingSpecs {
    public static RequestSpecification bookingRequestSpec;
    public static ResponseSpecification bookingResponseSpec;
    public static void specs(){
        bookingRequestSpec = new RequestSpecBuilder()
                .setBaseUri(URLS.RESTFULURL)
                .setBasePath(URLS.UPDATEBOOKING)
                .setContentType(ContentType.JSON)
                .build();

        bookingResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }
}
