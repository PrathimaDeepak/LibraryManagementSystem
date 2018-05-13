package library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * This class manages different transactions on borrowing data.
 * 
 * @author Prathima
 * 
 */

public class Borrowing {

	/**
	 * borrowing array
	 */
	private Borrower[] borrowingArray;

	/**
	 * array data size
	 */
	private int dataSize;

	/**
	 * Default constructor
	 */
    private UserInterface userInterface;


    Borrowing(UserInterface userInterface) {
      this.userInterface = userInterface;
    }
    public UserInterface getUserInterface(){
        return userInterface;
    }
	/**
	 * @return the borrowingArray
	 */
	public Borrower[] getBorrowingArray() {
		return borrowingArray;
	}

	/**
	 * @param borrowingArray
	 *            the borrowingArray to set
	 */
	public void setBorrowingArray(Borrower[] borrowingArray) {
		this.borrowingArray = borrowingArray;
	}


	/**
	 * @param dataSize
	 *            the dataSize to set
	 */
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * allows a member to borrow a book given its ID and the student ID
	 * 
	 * @param isbn
	 *            the book's ISBN
	 * @param id
	 *            the student ID
	 * @param due
	 *            date due to return
	 * @return message displaying method progress
	 * @throws ParseException
	 */
	public String borrow(String bookId, String studentId, Date dueDate) throws ParseException {
		String message = "";

		int bookIndex = getUserInterface().getBooks().searchBookById(bookId);

		int studentIndex = getUserInterface().getMembers().searchStudentById(String.valueOf(studentId));

		if (bookIndex == -1)
			message = "Invalid Book Id\n";// validating ISBN

		else if (studentIndex == -1)// validating ID
			message = "Invalid Student Id\n";

		else if (!getUserInterface().getBooks().getBooksArray()[bookIndex].isAvailableForLoan())
			message = "Sorry, The Book is not available for loan!\n";

		else if (getUnreturnedBooks(studentId) >= 1)
			message = "You cannot borrow more than 1 book at a time!\n";

		else {
			Book borrowedBook = getUserInterface().getBooks().getBooksArray()[bookIndex];
			int index = dataSize;
			borrowingArray[index] = new Borrower(bookId, studentId, IOManager.formatDate(new Date()), IOManager.formatDate(dueDate)); // inserting borrowing data in the array
			dataSize++;
			writeToBorrowFile(borrowingArray, dataSize);
			//set the book to not available for loan in books array
			borrowedBook.setAvailableForLoan(false);
			getUserInterface().getBooks().updateBook(borrowedBook);
			message = "Data updated successfully.\n";
		}

		return message;

	}
	
	/**
	 * allows a student to borrow a book given its ID and the student ID
	 * 
	 * @param borrowingArray, datasize
	 * 
	 */
	public void writeToBorrowFile(Borrower[] borrowingArray, int dataSize) {
		final String file_name = "C:\\PFApp\\resources\\borrow.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			StringBuilder content = new StringBuilder();
			for(int i=0; i<= dataSize ; i++) {
				Borrower borrower = borrowingArray[i];
				if(borrower != null) {
					content.append(borrower.getBookId()+","+borrower.getStudentId()+","+borrower.getDateIssued()+","+borrower.getDueDate()+ "\n");
				}
			}
			fw = new FileWriter(file_name);
			bw = new BufferedWriter(fw);
			bw.write(content.toString());
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @param memberID
	 * @return number of books the student has borrowed and has not been returned
	 */
	public int getUnreturnedBooks(String memberID) {
		int numberOfBooks = 0;
		int i = 0;
		while (borrowingArray[i] != null) {
			if ((borrowingArray[i].getStudentId().equalsIgnoreCase(memberID)))
				numberOfBooks++;
			i++;
		}
		return numberOfBooks;
	}

	/**
	 * searches for a book in the borrowing array given its ISBN and the
	 * member's ID who has borrowed this book
	 * 
	 * @param bookId
	 *            book's ID
	 * @param id
	 *            student's ID
	 *            
	 * @return the index of the book in the borrowing array
	 */
	private int searchBook(String bookId, String studentId) {
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (borrowingArray[i] != null))// linear search
		{
			if (borrowingArray[i].getBookId().equalsIgnoreCase(bookId)
					&& (borrowingArray[i].getStudentId().equalsIgnoreCase(studentId)))
			{
				index = i;
				found = true;
			}
			i++;
		}

		return index;
	}

	/**
	 * returns a borrowed book to the library system
	 * 
	 * @param isbn
	 *            Book's ID
	 * @param id
	 *            student's ID
	 * 
	 * @return message displaying method progress
	 */
	public String returnBook(String bookId, String studentId) {
		try {
			int index = searchBook(bookId, studentId);
			String message = "";

			if (index == -1)
				message = "Invalid Book ID and Member ID\n";

			else {

				int bookIndex = getUserInterface().getBooks().searchBookById(bookId);
				if (bookIndex != -1)// Book ID validation
				{
					Book returnedBook = getUserInterface().getBooks().getBooksArray()[bookIndex];
					returnedBook.setAvailableForLoan(true);
					getUserInterface().getBooks().updateBook(returnedBook);
					borrowingArray[index] = null;
					dataSize--;
					writeToBorrowFile(borrowingArray, dataSize);
					setBorrowingArray(getUserInterface().getData().readBorrowingFile());
					message = "Data updated successfully.\n"; // available copies
				} else
					message = "This book was deleted";

			}
			return message;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * displays over due books
	 * 
	 * @return over due books
	 */
	public String getOverDueBooks() {
		int counter = 0;
		String message = "Over due books:\n---------------\n";

		int i = 0;
		while (borrowingArray[i] != null)// linear search
		{
			try {
				if (IOManager.parseDate(borrowingArray[i].getDueDate()).before(new Date())) {
					counter++;
					int bookIndex = getUserInterface().getBooks().searchBookById(
							borrowingArray[i].getBookId());
					int studentIndex = getUserInterface().getMembers().searchStudentById(borrowingArray[i].getStudentId());
					
					if (bookIndex == -1)
						message = "The book with ID  "
								+ borrowingArray[i].getBookId() + " was deleted\n";
					else
						message = message 
								+ getUserInterface().getBooks().getBooksArray()[bookIndex].getBookId() + "     "
								+ getUserInterface().getBooks().getBooksArray()[bookIndex].getTitle() + " -- is borrowed by "
								+ getUserInterface().getMembers().getMembersArray()[studentIndex].getName()
								+ "\r\n";
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			i++;

		}

		if (counter == 0)
			message = "No over due books.\n";

		return message;

	}


	/**
	 * This method is to search the borrowers as per studentID, bookId and due date
	 * 
	 * @return the book and borrower details
	 */
	public String searchBorrowDetails(String studentId, String bookId, String dueDateString) {

		if (studentId.isEmpty() && bookId.isEmpty() && dueDateString.isEmpty()) // checking for empty input from the
																				// user
			return "No results matching your request!\n";

		String results = "Results:\n--------\n" + "BookID   "
				+ "Title  --  Borrower\n-------------------------------------\n";
		int foundEntries = 0;
		int i = 0;
		bookId = bookId.trim().toLowerCase();
		studentId = studentId.trim().toLowerCase();
		try {
			Date dueDate = null;
			if (!dueDateString.isEmpty()) {
				dueDate = IOManager.parseDate(dueDateString);
			}
			while (borrowingArray[i] != null) // linear search
			{

				if (borrowingArray[i].getBookId().toLowerCase().contains(bookId)
						&& borrowingArray[i].getStudentId().toLowerCase().contains(studentId)
						&& (dueDate == null || IOManager.parseDate(borrowingArray[i].getDueDate()).equals(dueDate))) {
					foundEntries++;
					int bookIndex = getUserInterface().getBooks().searchBookById(borrowingArray[i].getBookId());
					int studentIndex = getUserInterface().getMembers().searchStudentById(borrowingArray[i].getStudentId());
					if (bookIndex > -1 && studentIndex > -1)
						results = results + getUserInterface().getBooks().getBooksArray()[bookIndex].getBookId()
								+ "     " + getUserInterface().getBooks().getBooksArray()[bookIndex].getTitle()
								+ " -- is borrowed by "
								+ getUserInterface().getMembers().getMembersArray()[studentIndex].getName() + "\r\n";

				}
				i++;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (foundEntries == 0)
			results = "No results matching your request!\n";
		return results;
	}
	

}