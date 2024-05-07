package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.PetData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

import static ge.tbc.tbcitacademy.data.PetData.petID;
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
                .param("status", PetData.STATUS)
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
                .param("status", PetData.STATUS)
                .get("/pet/findByStatus");
        response
                .then()
                .spec(petstoreResponseSpec)
                .body("[0].id", equalTo(petID))
                .body("[0].name", equalTo(PetData.NAME))
                .body("[0].category.name", equalTo(PetData.DOG))
                .body("[0].category.id", equalTo(125))
                .body("[0].status", equalTo(PetData.STATUS));

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
                .body("name", equalTo(PetData.NEWNAME))
                .body("status", equalTo(PetData.NEWSTATUS));
        return this;
    }

    public PetstoreSteps uploadPicture(){
//        სამსახურის ლეპტოპზე რაღაც წვდომები მაქვს შეზღუდული და ფოტოს ატვირთვისას
//        java.io.FileNotFoundException: C:\Users\User\Desktop\puppy (Access is denied) ამას მიგდებს :/ წესით სწორი უნდა იყოს არადა
        File file = new File(PetData.PICTUREPATH);
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
}
