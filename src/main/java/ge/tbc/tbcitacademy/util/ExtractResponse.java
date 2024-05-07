//package ge.tbc.tbcitacademy.util;
//
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import io.restassured.specification.RequestSpecification;
//import io.restassured.specification.ResponseSpecification;
//
//import static io.restassured.RestAssured.given;
//
//public class ExtractResponse {
//    public static Response sendRequest(RequestSpecification requestSpec) {
//        return given()
//                .spec(requestSpec)
//                .contentType(ContentType.JSON)
//                .when()
//                .put();
//    }
//}
