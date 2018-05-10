package library;

import java.text.ParseException;

/**
 * This class manages data validation methods
 * 
 * @author Amr Nabil
 * 
 */
public class Validator {

	/**
	 * maximum number of digits in phone number
	 */
	public static final int MAXIMUM_LENGTH = 15;


    private UserInterface userInterface;


    Validator(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
        public UserInterface getUserInterface(){
        return userInterface;
    }


	/**
	 * validate e-mail
	 * 
	 * @param mail
	 *            the e-mail to check
	 * @return boolean, true if valid
	 */
	public boolean isValidEmail(String mail) {
		
		if (mail == null)
			return false;

		String[] tokens = mail.split("@");

		if (mail.lastIndexOf(".") > mail.lastIndexOf("@") && tokens.length == 2
				&& !tokens[0].isEmpty())
			return true;
		else
			return false;
	}

	/**
	 * validates the phone number
	 * 
	 * @param phoneNumber
	 *            the phone number to check
	 * @return boolean, true if valid
	 */
	public boolean isValidPhoneNumber(String phoneNumber) {

		if (phoneNumber == null || phoneNumber.length() > MAXIMUM_LENGTH)
			return false;
		int i = 0;
		while (i < phoneNumber.length()) {
			if (!Character.isDigit(phoneNumber.charAt(i))
					&& !(phoneNumber.charAt(i) == '-'))
				return false;
			else
				i++;
		}
		return true;
	}

	/**
	 * validates an Integer
	 * 
	 * @param integer
	 *            the string to check
	 * @return boolean, true when valid
	 */
	public boolean isValidInteger(String integer) {

		if (integer == null || integer.isEmpty()
				|| integer.length() > MAXIMUM_LENGTH)
			return false;
		int i = 0;
		while (i < integer.length()) {
			if (!Character.isDigit(integer.charAt(i)))
				return false;
			else
				i++;
		}
		return true;
	}

	/**
	 * returns false when the string contains ","
	 * 
	 * @param inData
	 *            the string to check
	 * @return boolean,true when valid
	 */
	public boolean isValidString(String inData) {
		if (inData.contains(","))
			return false;
		else
			return true;
	}

	/**
	 * validates the inserted name
	 * 
	 * @param name
	 *            the name to check
	 * @return boolean, true when valid
	 */
	public boolean isValidName(String name) {
		if(name == null) return false;
		else return true;
//		if (name == null)
//			return false;
//		int i = 0;
//		while (i < name.length()) {
//			if (!Character.isLetter(name.charAt(i)) && !(name.charAt(i) == ' ')
//					&& !(name.charAt(i) == '.'))
//				return false;
//			else
//				i++;
//		}
//
//		return true;

	}

	/**
	 * validates ISBN
	 * 
	 * @param isbn
	 *            the ISBN to check
	 * @return boolean, true when valid
	 */
	public boolean isValidISBN(String isbn) {
		if (isbn == null || !isbn.contains("-"))
			return false;
		int i = 0;
		while (i < isbn.length()) {
			if (!Character.isDigit(isbn.charAt(i)) && !(isbn.charAt(i) == '-'))
				return false;
			else
				i++;
		}
		return true;
	}

	/**
	 * validates the date
	 * 
	 * @param date
	 *            the string to check
	 * @return boolean, true when valid
	 */
	public boolean isValidDate(String date) {
		try {
			IOManager.parseDate(date);
		} catch (ParseException e) {
			return false;
		}
		return true;

	}

	/**
	 * validates book data when reading from a file
	 * 
	 * @param bookData
	 *            array of strings containing book data
	 * @return boolean, true when valid
	 */
	public boolean isValidBookLine(String[] bookData) {
		boolean valid = isValidName(bookData[1]) ;
		return valid;
	}

	/**
	 * validates member data when reading from a file
	 * 
	 * @param memberData
	 *            array of strings containing member data
	 * @return boolean, true when valid
	 */
	public boolean isValidMemberLine(String[] memberData) {

		boolean valid = isValidName(memberData[1]);

		return valid;
	}

	/**
	 * validates borrowing data when reading from a file
	 * 
	 * @param borrowingData
	 *            array of strings containing borrowing data
	 * @return boolean, true when valid
	 */
	public boolean isValidBorrowingLine(String[] borrowingData) {

		boolean valid = isValidISBN(borrowingData[0])
				//&& (getUserInterface().getBooks().searchISBN(borrowingData[0]) != -1)
				&& isValidInteger(borrowingData[1])
				&& isValidDate(borrowingData[2])
				&& isValidDate(borrowingData[3])
				&& ((borrowingData.length == 4 || borrowingData[4]
						.equalsIgnoreCase(" ")) ? true
						: isValidDate(borrowingData[4]));
		return valid;
	}
	
	public boolean isValidBook(String bookId,String title,String author,String category,String language,boolean availableForLoan) {
		if(bookId.isEmpty() || title.isEmpty() || author.isEmpty() || category.isEmpty() || language.isEmpty()) {
			return false;
		}
		//TO DO: add regex validation for book ID
		return true;
	}
	
	public boolean isValidMember(String studentId, String name, int studentClass) {
		if(studentId.isEmpty() || name.isEmpty() || studentClass == 0 || studentClass < 1 || studentClass > 10) {
			return false;
		}
		
		return true;
	}
	
	public boolean isValidReturnBookData(String bookId, String studentId) {
		if(studentId.isEmpty() || bookId.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public boolean isValidIssueBookData(String bookId, String studentId) {
		if(studentId.isEmpty() || bookId.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	


}