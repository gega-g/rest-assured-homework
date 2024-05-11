package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.BooksCONSTANTS;
import ge.tbc.tbcitacademy.data.URLS;
import ge.tbc.tbcitacademy.models.bookstoreData.BookstoreData;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class POJOBookstoreSteps {

    public POJOBookstoreSteps validateQuantity() {
        Response response = given()
                .baseUri(URLS.BOOKSTOREURL)
                .when()
                .basePath(URLS.BOOKS)
                .get();

        BookstoreData bookstoreData = response.as(BookstoreData.class);
        assertThat(bookstoreData.getBooks(), everyItem(hasProperty("pages", lessThan(1000))));
        return this;
    }

    public POJOBookstoreSteps validateAuthors() {
        Response response = given()
                .baseUri(URLS.BOOKSTOREURL)
                .when()
                .basePath(URLS.BOOKS)
                .get();

        BookstoreData bookstoreData = response.as(BookstoreData.class);
        assertThat(bookstoreData.getBooks().get(0).getAuthor(), equalTo(BooksCONSTANTS.FIRSTAUTHOR));
        assertThat(bookstoreData.getBooks().get(1).getAuthor(), equalTo(BooksCONSTANTS.SECONDAUTHOR));
        response.then().log().all();
        return this;
    }
}
