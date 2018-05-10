package library;

import java.util.Date;

/**
 * A Borrower object represent a borrowed book, such that for each book, the
 * system stores the book's ISBN, user ID, date issued,date due to return and
 * date returned.
 * 
 * @author Amr Nabil
 * 
 */

public class Borrower {

	/**
	 * book's ISBN
	 */
	private String bookId;
	/**
	 * member's ID
	 */
	private String studentId;
	/**
	 * date of borrowing the book
	 */
	private String dateIssued;
	/**
	 * Date due to return
	 */
	private String dueDate;
	
	public Borrower() {
		
	}
	
	public Borrower(String bookId, String studentId, String dateIssued, String dueDate) {
		super();
		this.bookId = bookId;
		this.studentId = studentId;
		this.dateIssued = dateIssued;
		this.dueDate = dueDate;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	

}