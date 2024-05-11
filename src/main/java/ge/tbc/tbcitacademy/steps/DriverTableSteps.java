package ge.tbc.tbcitacademy.steps;

import ge.tbc.tbcitacademy.data.DriverCONSTANTS;
import ge.tbc.tbcitacademy.data.URLS;
import ge.tbc.tbcitacademy.models.drivers.DriversItem;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DriverTableSteps {
    public DriverTableSteps getFirstDriver() {
//    String JSONResponse = """
//            {
//              "DriverTable": {
//                "season": "2016",
//                "Drivers": [
//                  {
//                    "driverId": "alonso",
//                    "permanentNumber": "14",
//                    "code": "ALO",
//                    "url": "http://en.wikipedia.org/wiki/Fernando_Alonso",
//                    "givenName": "Fernando",
//                    "familyName": "Alonso",
//                    "dateOfBirth": "1981-07-29",
//                    "nationality": "Spanish"
//                  }
//                ]
//              }
//            }
//            """;
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            DriverTable driverTable = objectMapper.readValue(JSONResponse, DriverTable.class);
//            List<DriversItem> drivers = driverTable.getDrivers();
//            DriversItem firstDriver = drivers.get(0);
//            System.out.println(firstDriver);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//
//        JsonPath jsonPath = new JsonPath(JSONResponse);
//        String driverFromService = jsonPath.getString("MRData.DriverTable.Drivers[0]");

        Response response = given()
                .baseUri(URLS.DRIVERSURL)
                .contentType(ContentType.JSON)
                .get();

        String jsonResponse = response.getBody().asString();

        JsonPath jsonPath = new JsonPath(jsonResponse);
        String driverFromService = jsonPath.getString("MRData.DriverTable.Drivers[0]");


        DriversItem driverInCode = new DriversItem();
        driverInCode.setDriverId(DriverCONSTANTS.DRIVERID);
        driverInCode.setPermanentNumber(DriverCONSTANTS.PERMANENTNUM);
        driverInCode.setCode(DriverCONSTANTS.CODE);
        driverInCode.setUrl(DriverCONSTANTS.URL);
        driverInCode.setGivenName(DriverCONSTANTS.GIVENNAME);
        driverInCode.setFamilyName(DriverCONSTANTS.FAMILYNAME);
        driverInCode.setDateOfBirth(DriverCONSTANTS.DATEOFBIRTH);
        driverInCode.setNationality(DriverCONSTANTS.NATIONALITY);

        assertThat(driverFromService, equalTo(driverInCode));
        return this;
//        ვერ მოვასწარი და არმუშაობს
    }
}
