package ge.tbc.tbcitacademy.models.bookstoreData;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BookstoreData{

	@JsonProperty("books")
	private List<BooksItem> books;

	public void setBooks(List<BooksItem> books){
		this.books = books;
	}

	public List<BooksItem> getBooks(){
		return books;
	}

	@Override
 	public String toString(){
		return 
			"BookstoreData{" + 
			"books = '" + books + '\'' + 
			"}";
		}
}