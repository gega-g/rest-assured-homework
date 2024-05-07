package ge.tbc.tbcitacademy.tests;

import ge.tbc.tbcitacademy.specifications.BookstoreSpecs;
import ge.tbc.tbcitacademy.steps.BookstoreSteps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookstoreTest {
    @BeforeClass
    public void setUp(){
        BookstoreSpecs.specs();
    }
    BookstoreSteps bookstoreSteps = new BookstoreSteps();

    @Test
    public void BookstoreTests(){
        bookstoreSteps
                .validateQuantity()
                .validateAuthors()
        ;
    }
}
