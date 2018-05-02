package library;



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
		int index = dataSize;
		booksArray[index] = aBook; // inserting the book in the empty location
		dataSize++;
		return "\nBook inserted into the system successfully.\n";
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
	 * @param isbn
	 *            the ISBN of the book
	 * @return message representing the search results
	 */
	public String searchBook(String title, String author, String category,
			String isbn) {

		if (title.isEmpty() && author.isEmpty() && category.isEmpty()
				&& isbn.isEmpty()) // checking for empty input from the user
			return "No results matching your request!\n";

		String results = "Results:\n--------\n";
		int foundBooks = 0;
		int i = 0;
		title = title.trim().toLowerCase();
		author = author.trim().toLowerCase();
		category = category.trim().toLowerCase();
		isbn = isbn.trim().toLowerCase();
		while (booksArray[i] != null) // linear search
		{

			if (booksArray[i].getTitle().toLowerCase().contains(title)
					&& booksArray[i].getAuthor().toLowerCase().contains(author)
					&& booksArray[i].getCategory().toLowerCase().contains(
							category)) {
				foundBooks++;
				results = results + foundBooks + ")" + booksArray[i].getTitle()
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
	 * @param isbn
	 *            the book's ISBN
	 * @return the index of the book in the books array
	 */
	public int searchISBN(String isbn) {

		if (isbn.isEmpty())
			return -1;
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (booksArray[i] != null)) // linear search
		{
			/*if (booksArray[i].getIsbn().equalsIgnoreCase(isbn)) {
				index = i;
				found = true;

			}*/
			i++;

		}
		return index;

	}

	/**
	 * Adds a number of copies to a book
	 * 
	 * @param isbn
	 *            the book's ISNB
	 * @param n
	 *            the number of copies to add
	 * @return message displaying method progress
	 */
//	public String addCopy(String isbn, int n) {
//		String message = "";
//		int index = searchISBN(isbn);
//		if (index == -1)
//			message = "Invalid ISBN!\n"; // ISBN validation
//		else {
//			booksArray[index].setNumberOfCopies(booksArray[index]
//					.getNumberOfCopies()
//					+ n);
//			booksArray[index].setAvailableNumberOfCopies(booksArray[index]
//					.getAvailableNumberOfCopies()
//					+ n);
//			message = "Number of copies has been updated.\n";
//		}
//		return message;
//
//	}

	/**
	 * Deletes a book given its ISBN
	 * 
	 * @param isbn
	 *            the book's ISBN
	 * @return message displaying method progress
	 */
	public String deleteBook(String isbn) {
		String message;
		int index = searchISBN(isbn);
		if (index == -1)
			message = "Invalid ISBN\n"; // ISBN Validation
		else {
			System.arraycopy(booksArray, index + 1, booksArray, index,
					booksArray.length - index - 1);
			dataSize--;
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

		String message = "Books:\n------\n";
		int counter = 0;
		for (int i = 0; i < dataSize; i++) {
			counter++;
			message = message + counter + ")" + booksArray[i].getTitle()
					+ "\r\n";
		}
		if (counter == 0)
			message = "No available books\n";
		return message;

	}

	
}