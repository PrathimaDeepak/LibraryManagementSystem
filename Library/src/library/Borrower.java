package library;


/**
 * A Borrower object represent a borrowed book, such that for each book, the
 * system stores the book's ID, student ID, date issued and date due
 * 
 * @author Prathima
 * 
 */

public class Borrower {

	/**
	 * book's ID
	 */
	private String bookId;
	/**
	 * student ID
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