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
	private String isbn;
	/**
	 * member's ID
	 */
	private int id;
	/**
	 * date of borrowing the book
	 */
	private Date dateIssued;
	/**
	 * Date due to return
	 */
	private Date dueDate;
	/**
	 * Date of returning the book
	 */
	private Date dateReturned;

	/**
	 * Default constructor
	 */
	public Borrower() {
		this("", 0, null, null, null);
	}

	/**
	 * @param isbn
	 *            book's ISBN
	 * @param id
	 *            member's ID
	 * @param dateIssued
	 *            date issued
	 * @param dueDate
	 *            date due to return
	 * @param dateReturned
	 *            date of returning the book
	 */
	public Borrower(String isbn, int id, Date dateIssued, Date dueDate,
			Date dateReturned) {
		this.isbn = isbn;
		this.id = id;
		this.dateIssued = dateIssued;
		this.dueDate = dueDate;
		this.dateReturned = dateReturned;
	}

	// setters and getters for class fields
	/**
	 * @return the ISBN
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn
	 *            the ISBN to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @return the ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the ID to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the dateIssued
	 */
	public Date getDateIssued() {
		return dateIssued;
	}

	/**
	 * @param dateIssued
	 *            the dateIssued to set
	 */
	public void setDateIssued(Date dateIssued) {
		this.dateIssued = dateIssued;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate
	 *            the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dateReturned
	 */
	public Date getDateReturned() {
		return dateReturned;
	}

	/**
	 * @param dateReturned
	 *            the dateReturned to set
	 */
	public void setDateReturned(Date dateReturned) {
		this.dateReturned = dateReturned;
	}

	/**
	 * borrower data represented in a line delimited line
	 * 
	 * @return formatted string
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return isbn + "," + id + "," + IOManager.formatDate(dateIssued) + ","
				+ IOManager.formatDate(dueDate) + ","
				+ IOManager.formatDate(dateReturned);

	}

}