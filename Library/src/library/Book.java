package library;

import java.util.Date;

/**
 * A Book object represent a reading book, such that for each book, the system
 * stores the id, title, author, category, language and if it is available for loan
 * 
 */
public class Book {
	
	private String bookId;
	
	private String title;
	
	private String author;
	
	private String category;
	
	private String language;
	
	private boolean availableForLoan;
	
	public Book() {
		
	}

	public Book(String bookId, String title, String author, String category, String language,
			boolean availableForLoan) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.language = language;
		this.availableForLoan = availableForLoan;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isAvailableForLoan() {
		return availableForLoan;
	}

	public void setAvailableForLoan(boolean availableForLoan) {
		this.availableForLoan = availableForLoan;
	}
	
	
}