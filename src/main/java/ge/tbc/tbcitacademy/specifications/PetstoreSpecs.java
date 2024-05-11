package ge.tbc.tbcitacademy.specifications;

import ge.tbc.tbcitacademy.data.URLS;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

public class PetstoreSpecs {
    static {
        specs();
    }

    public static RequestSpecification petstoreRequestSpec;
    public static ResponseSpecification petstoreResponseSpec;
    public static void specs(){
        petstoreRequestSpec = new RequestSpecBuilder()
                .setBaseUri(URLS.PETSTOREURL)
                .setBasePath(URLS.V2)
                .setContentType(ContentType.JSON)
                .build();

        petstoreResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody(not(empty()))
                .log(LogDetail.ALL)
                .build();
    }
}
