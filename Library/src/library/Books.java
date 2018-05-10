package library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class manages book data
 * 
 * @author Amr Nabil
 * 
 */
public class Books {

	/**
	 *book's array
	 */
	private Book[] booksArray;
	/**
	 * array data size
	 */
	private int dataSize;


	/**
	 * Default constructor
	 */
    private UserInterface userInterface;
    
    	public UserInterface getUserInterface(){
        return userInterface;
    }
        
    Books(UserInterface userInterface) {
       this.userInterface = userInterface;
    }

	// setters and getters for class fields
	/**
	 * @return the booksArray
	 */
	public Book[] getBooksArray() {
		return booksArray;
	}

	/**
	 * @param booksArray
	 *            the booksArray to set
	 */
	public void setBooksArray(Book[] booksArray) {
		this.booksArray = booksArray;
	}

	/**
	 * @return the dataSize
	 */
	public int getDataSize() {
		return dataSize;
	}

	/**
	 * @param dataSize
	 *            the dataSize to set
	 */
	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}


	/**
	 * This method adds a new book to the library
	 * 
	 * @param aBook
	 *            the book to insert
	 */
	public String insertBook(Book aBook) {
		boolean isDuplicate = checkForDuplicateBookID(aBook.getBookId(),booksArray);
		if(!isDuplicate) {
			int index = dataSize;
			booksArray[index] = aBook; // inserting the book in the empty location
			writeToBooksFile(booksArray, dataSize);
			dataSize++;
			return "\nBook inserted into the system successfully.\n";			
		}else {
			return "\nBook ID already exists\n";
		}
	}
	
	public void updateBook(Book aBook) {
			int index = searchBookById(aBook.getBookId());
			booksArray[index] = aBook; // updating the book
			writeToBooksFile(booksArray, dataSize);
			dataSize++;
	}
	
	private boolean checkForDuplicateBookID(String bookId,Book[] booksArray) {
		for(int i=0; i< booksArray.length; i++) {
			if(booksArray[i].getBookId().equalsIgnoreCase(bookId)) {
				return true;
			}
		}
		return false;
	}
	
	public void writeToBooksFile(Book[] booksArray, int dataSize) {
		final String file_name = "src/resources/books.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			StringBuilder content = new StringBuilder();
			for(int i=0; i<= dataSize ; i++) {
				Book book = booksArray[i];
				if(book != null) {
					content.append(book.getBookId()+","+book.getTitle()+","+book.getAuthor()+","+book.getCategory()+","+book.getLanguage()+","+book.isAvailableForLoan()+"\n");
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
	 * Searches for a book in the library
	 * 
	 * @param title
	 *            the title of the book to search for
	 * @param author
	 *            the author of the book
	 * @param category
	 *            the category of the book
	 * @param bookId
	 *            the ID of the book
	 * @return message representing the search results
	 */
	public String searchBook(String title, String author, String category,
			String bookId) {

		if (title.isEmpty() && author.isEmpty() && category.isEmpty()
				&& bookId.isEmpty()) // checking for empty input from the user
			return "No results matching your request!\n";

		String results = "Results:\n--------\n" + "BookID   " + "Title  --  Availability\n-------------------------------------\n";
		int foundBooks = 0;
		int i = 0;
		title = title.trim().toLowerCase();
		author = author.trim().toLowerCase();
		category = category.trim().toLowerCase();
		bookId = bookId.trim().toLowerCase();
		while (booksArray[i] != null) // linear search
		{

			if (booksArray[i].getTitle().toLowerCase().contains(title)
					&& booksArray[i].getAuthor().toLowerCase().contains(author)
					&& booksArray[i].getCategory().toLowerCase().contains(category)
					&& booksArray[i].getBookId().toLowerCase().contains(bookId)) {
				foundBooks++;
				String isAvailable;
				if(booksArray[i].isAvailableForLoan()) {
					isAvailable = "Available for loan";
				}else {
					isAvailable = "Not available for loan";
				}
				results = results + booksArray[i].getBookId() + "   " + booksArray[i].getTitle()  + " -- " + isAvailable
						+ "\r\n";
			}
			i++;
		}
		if (foundBooks == 0)
			results = "No results matching your request!\n";
		return results;
	}

	/**
	 * searches for a book with a given ISBN and returns its index or returns -1
	 * when not found
	 * 
	 * @param bookId
	 *            the book's ID
	 * @return the index of the book in the books array
	 */
	public int searchBookById(String bookId) {

		if (bookId.isEmpty())
			return -1;
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (booksArray[i] != null)) // linear search
		{
			if (booksArray[i].getBookId().equalsIgnoreCase(bookId)) {
				index = i;
				found = true;

			}
			i++;

		}
		return index;

	}

	
	/**
	 * Deletes a book given its ISBN
	 * 
	 * @param bookId
	 *            the book's ID
	 * @return message displaying method progress
	 */
	public String deleteBook(String bookId) {
		String message;
		int index = searchBookById(bookId);
		if (index == -1)
			message = "Invalid Book ID\n"; // ISBN Validation
		else {
			System.arraycopy(booksArray, index + 1, booksArray, index,
					booksArray.length - index - 1);
			dataSize--;
			writeToBooksFile(booksArray,dataSize);
			message = "Book deleted successfully.\n";
		}
		return message;

	}

	
	

	/**
	 * displays a list of all available books
	 * 
	 * @return list of available books
	 */
	public String displayBooks() {

		String message = "BookID   " + "Title  --  Author\n-------------------------------------\n";
		int counter = 0;
		for (int i = 0; i < dataSize; i++) {
			counter++;
			message = message + booksArray[i].getBookId() + "   " + booksArray[i].getTitle() + " -- " + booksArray[i].getAuthor() + "\r\n";
		}
		if (counter == 0)
			message = "No available books\n";
		return message;

	}

	
}