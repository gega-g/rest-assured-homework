package ge.tbc.tbcitacademy.tests2;

import ge.tbc.tbcitacademy.data.PetCONSTANTS;
import ge.tbc.tbcitacademy.specifications.PetstoreSpecs;
import ge.tbc.tbcitacademy.steps.POJOPetstoreSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class POJOPetstoreTest {
    POJOPetstoreSteps petstoreSteps = new POJOPetstoreSteps();

    @BeforeClass
    public void setUp(){

        PetstoreSpecs.specs();
    }
    @Test
    public void petStoreTest(){
        petstoreSteps
                .addID()
                .addCategory()
                .addName(PetCONSTANTS.NAME)
                .addStatus(PetCONSTANTS.STATUS)
                .createPet()
                .validateID()
                .parametersValidation()
                .changeNameAndStatus()
                .validateChanges();
    }
}
