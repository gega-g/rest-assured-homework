package ge.tbc.tbcitacademy.specifications;

import ge.tbc.tbcitacademy.data.URLS;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class BookstoreSpecs {
    public static RequestSpecification bookstoreRequestSpec;
    public static ResponseSpecification bookstoreResponseSpec;
    public static void specs(){
        bookstoreRequestSpec = new RequestSpecBuilder()
                .setBaseUri(URLS.BOOKSTOREURL)
                .setBasePath(URLS.BOOKS)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization",URLS.TOKEN)
                .build().log().all();

        bookstoreResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody(not(empty()))
                .log(LogDetail.ALL)
                .build();
    }
}
