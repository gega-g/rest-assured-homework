    package ge.tbc.tbcitacademy.steps;

    import ge.tbc.tbcitacademy.data.PetCONSTANTS;
    import ge.tbc.tbcitacademy.data.URLS;
    import ge.tbc.tbcitacademy.models.petstoreData.Category;
    import ge.tbc.tbcitacademy.models.petstoreData.PetstoreData;
    import io.restassured.http.ContentType;
    import io.restassured.response.Response;
    import org.json.JSONObject;

    import java.io.File;

    import static ge.tbc.tbcitacademy.specifications.PetstoreSpecs.petstoreRequestSpec;
    import static io.restassured.RestAssured.given;
    import static org.hamcrest.MatcherAssert.assertThat;
    import static org.hamcrest.Matchers.*;

    public class POJOPetstoreSteps {
        PetstoreData petstoreData = new PetstoreData();

        public POJOPetstoreSteps addID() {
            petstoreData.setId(PetCONSTANTS.petID);
            return this;
        }

        public POJOPetstoreSteps addCategory() {
            Category category = new Category();
            category.setId(125);
            category.setName("dog");
            petstoreData.setCategory(category);
            return this;
        }

        public POJOPetstoreSteps addName(String name) {
            petstoreData.setName(name);
            return this;
        }

        public POJOPetstoreSteps addStatus(String status) {
            petstoreData.setStatus(status);
            return this;
        }

        public POJOPetstoreSteps createPet() {
            JSONObject petstoreJson = new JSONObject(petstoreData);

            Response response = given()
                    .spec(petstoreRequestSpec)
                    .contentType(ContentType.JSON)
                    .body(petstoreJson.toString())
                    .post("/pet");
            response
                    .then()
                    .statusCode(200);
            return this;
        }

        public POJOPetstoreSteps validateID() {
            Response response = given()
                    .spec(petstoreRequestSpec)
                    .get("/pet/{petId}", PetCONSTANTS.petID);
            response
                    .then()
                    .assertThat()
                    .body("category.id", equalTo(125));
            return this;
        }

        public POJOPetstoreSteps parametersValidation() {
            Response response = given()
                    .spec(petstoreRequestSpec)
                    .get("/pet/{petId}", PetCONSTANTS.petID);
            response
                    .then()
                    .body("id", equalTo(PetCONSTANTS.petID))
                    .body("name", equalTo(PetCONSTANTS.NAME))
                    .body("category.name", equalTo(PetCONSTANTS.DOG))
                    .body("category.id", equalTo(125))
                    .body("status", equalTo(PetCONSTANTS.STATUS));
            response.then().log().all();
            return this;
        }

        public POJOPetstoreSteps changeNameAndStatus() {
            PetstoreData changedPetstoreData = new PetstoreData();
            changedPetstoreData.setId(17);
            Category category = new Category();
            category.setId(125);
            category.setName(PetCONSTANTS.DOG);
            changedPetstoreData.setCategory(category);
            changedPetstoreData.setName(PetCONSTANTS.NEWNAME);
            changedPetstoreData.setStatus(PetCONSTANTS.NEWSTATUS);

            given()
                    .spec(petstoreRequestSpec)
                    .contentType(ContentType.JSON)
                    .body(changedPetstoreData)
                    .when()
                    .put("/pet")
                    .then()
                    .statusCode(200);
            return this;
        }

        public POJOPetstoreSteps validateChanges() {
            Response response = given()
                    .spec(petstoreRequestSpec)
                    .get("/pet/{petId}", PetCONSTANTS.petID);
            response
                    .then()
                    .assertThat()
                    .body("name", equalTo(PetCONSTANTS.NEWNAME))
                    .body("status", equalTo(PetCONSTANTS.NEWSTATUS));
            response.then().log().all();
            return this;
        }

        public POJOPetstoreSteps uploadPicture() {
            File file = new File(PetCONSTANTS.PICTUREPATH);
            String additionalMetadata = "additionalMetadata";
            double fileSize = file.length();

            Response response = given()
                    .baseUri(URLS.PETSTOREURL)
                    .accept(ContentType.JSON)
                    .contentType(ContentType.MULTIPART)
                    .multiPart("additionalMetadata", additionalMetadata)
                    .multiPart("file", file, "image/jpg")
                    .when()
                    .post("/pet/{petId}/uploadImage", PetCONSTANTS.petID);

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