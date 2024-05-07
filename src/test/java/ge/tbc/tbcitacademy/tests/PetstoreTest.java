package ge.tbc.tbcitacademy.tests;

import ge.tbc.tbcitacademy.data.PetData;
import ge.tbc.tbcitacademy.specifications.PetstoreSpecs;
import ge.tbc.tbcitacademy.steps.PetstoreSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetstoreTest {
    @BeforeClass
    public void setUp(){

        PetstoreSpecs.specs();
        }
    PetstoreSteps petstoreSteps = new PetstoreSteps();
    @Test
    public void test(){
        petstoreSteps
                .addID()
                .addCategory()
                .addName(PetData.NAME)
                .addPhotoUrls()
                .addTags()
                .addStatus(PetData.STATUS)
                .createPet()
                .validateID()
                .parametersValidation()
                .changeNameAndStatus()
                .validateChanges()
//                .uploadPicture()
        ;
    }
}
