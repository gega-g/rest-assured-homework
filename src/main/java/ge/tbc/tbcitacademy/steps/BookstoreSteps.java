package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.BooksData;
import ge.tbc.tbcitacademy.data.URLS;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookstoreSteps {
    public BookstoreSteps validateQuantity(){
//        Response response = ExtractResponse.sendRequest(bookstoreRequestSpec, bookstoreResponseSpec);
        Response response = given()
                .baseUri(URLS.BOOKSTOREURL)
                .when()
                .basePath(URLS.BOOKS)
                .get();
        response
                .then()
                .assertThat()
                .body("books.pages", everyItem(lessThan(1000)));
        return this;
    }

    public BookstoreSteps validateAuthors(){
//        Response response = ExtractResponse.sendRequest(bookstoreRequestSpec);
        Response response = given()
                .baseUri(URLS.BOOKSTOREURL)
                .when()
                .basePath(URLS.BOOKS)
                .get();
        response
                .then()
                .assertThat()
                .body("books.author[0]", equalTo(BooksData.FIRSTAUTHOR))
                .body("books.author[1]", equalTo(BooksData.SECONDAUTHOR));
        return this;
    }
}
