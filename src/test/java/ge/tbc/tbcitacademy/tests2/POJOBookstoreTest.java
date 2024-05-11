package ge.tbc.tbcitacademy.tests2;

import ge.tbc.tbcitacademy.steps.POJOBookstoreSteps;
import org.testng.annotations.Test;

public class POJOBookstoreTest {
    POJOBookstoreSteps pojoBookstoreSteps = new POJOBookstoreSteps();
    @Test
    public void bookstoreTest(){
        pojoBookstoreSteps
                .validateQuantity()
                .validateAuthors();

    }
}
