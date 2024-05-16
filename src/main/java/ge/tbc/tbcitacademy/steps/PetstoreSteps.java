package ge.tbc.tbcitacademy.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tbc.tbcitacademy.data.PetCONSTANTS;
import ge.tbc.tbcitacademy.data.URLS;
import ge.tbc.tbcitacademy.util.OrderWithLombok;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import static ge.tbc.tbcitacademy.data.PetCONSTANTS.petID;
import static ge.tbc.tbcitacademy.specifications.PetstoreSpecs.petstoreRequestSpec;
import static ge.tbc.tbcitacademy.specifications.PetstoreSpecs.petstoreResponseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetstoreSteps {
    JSONObject requestBody = new JSONObject();
    public PetstoreSteps addID(){
        requestBody.put("id", petID);
        return this;
    }
    public PetstoreSteps addCategory() {
        JSONObject category = new JSONObject();
        category.put("id", "125");
        category.put("name", "dog");
        requestBody.put("category", category);
        return this;
    }

    public PetstoreSteps addName(String name) {
        requestBody.put("name", name);
        return this;
    }

    public PetstoreSteps addPhotoUrls() {
        JSONArray urlsArray = new JSONArray();
        requestBody.put("photoUrls", urlsArray);
        return this;
    }

    public PetstoreSteps addTags() {
        JSONArray tagsArray = new JSONArray();
        requestBody.put("tags", tagsArray);
        return this;
    }

    public PetstoreSteps addStatus(String status) {
        requestBody.put("status", status);
        return this;
    }

    public PetstoreSteps createPet() {
        Response response = given()
                .spec(petstoreRequestSpec)
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .post("/pet");
        response.then().statusCode(200);
        return this;
    }

    public PetstoreSteps validateID(){
        Response response = given()
                .spec(petstoreRequestSpec)
                .param("status", PetCONSTANTS.STATUS)
                .get("/pet/findByStatus");
        response
                .then()
                .assertThat()
                .body("category.id", hasItem(125));
        return this;
    }

    public PetstoreSteps parametersValidation(){
        Response response = given()
                .spec(petstoreRequestSpec)
                .param("status", PetCONSTANTS.STATUS)
                .get("/pet/findByStatus");
        response
                .then()
                .spec(petstoreResponseSpec)
                .body("[0].id", equalTo(petID))
                .body("[0].name", equalTo(PetCONSTANTS.NAME))
                .body("[0].category.name", equalTo(PetCONSTANTS.DOG))
                .body("[0].category.id", equalTo(125))
                .body("[0].status", equalTo(PetCONSTANTS.STATUS));
        response
                .then()
                .log()
                .all();

        return this;
    }

    public PetstoreSteps changeNameAndStatus(){
        String requestBody = """
            {
              "id": 17,
              "category": {
                "id": 125,
                "name": "dog"
              },
              "name": "NewDoggo",
              "photoUrls": [

              ],
              "tags": [

              ],
              "status": "sold"
            }
            """;

        given()
                .spec(petstoreRequestSpec)
                .body(requestBody)
                .when()
                .put("/pet")
                .then()
                .statusCode(200);
        return this;
    }

    public PetstoreSteps validateChanges(){
        Response response = given()
                .spec(petstoreRequestSpec)
                .get("/pet/{petId}", petID);
        response
                .then()
                .spec(petstoreResponseSpec);
        response
                .then()
                .assertThat()
                .body("name", equalTo(PetCONSTANTS.NEWNAME))
                .body("status", equalTo(PetCONSTANTS.NEWSTATUS));
        return this;
    }

    public PetstoreSteps uploadPicture(){
        File file = new File(PetCONSTANTS.PICTUREPATH);
        String additionalMetadata = "additionalMetadata";
        double fileSize = file.length();

        Response response = given()
                .spec(petstoreRequestSpec)
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .multiPart("additionalMetadata", additionalMetadata)
                .multiPart("file", file, "image/jpg")
                .when()
                .post("/pet/{petId}/uploadImage", petID);

        String returnedText = response.body().jsonPath().getString("text");
        double responseFileSize = response.body().jsonPath().getInt("fileSize");

        response
                .then()
                .body("message", contains(returnedText))
                .body(("message"), contains("filename"));
        assertThat(fileSize, equalTo(responseFileSize));
        return this;
    }

    @Step("Creating new post using lombok, validating status code")
    public PetstoreSteps postUsingLombok() throws JsonProcessingException {
        OrderWithLombok.Order order = OrderWithLombok.Order.builder()
                .id(1)
                .petId(152)
                .quantity(2)
                .status(PetCONSTANTS.DONE)
                .complete(true)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonOrder = mapper.writeValueAsString(order);

        Response response = given()
                .filter(new AllureRestAssured())
                .baseUri(URLS.PETS3URL)
                .contentType(ContentType.JSON)
                .body(jsonOrder)
                .post(URLS.ORDER);

        response.then()
                .log().all()
                .assertThat().statusCode(200);
        return this;
    }
}
