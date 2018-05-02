package library;



/**
 * This class manages members data
 * 
 * @author Amr Nabil
 * 
 */

public class Members {

	/**
	 *member's array
	 */
	private Member[] membersArray;
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
        
    Members(UserInterface userinterface) {
        this.userInterface = userinterface;
    }

	/**
	 * @return the membersArray
	 */
	public Member[] getMembersArray() {
		return membersArray;
	}

	/**
	 * @param membersArray
	 *            the membersArray to set
	 */
	public void setMembersArray(Member[] membersArray) {
		this.membersArray = membersArray;
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
	 * Registers a new member
	 * 
	 * @param aMember
	 *            the member to register
	 * @return message displaying method progress
	 */
	public String register(Member aMember) {

		int index = dataSize;
		membersArray[index] = aMember;
		dataSize++; // incrementing data size
		return "\nRegistration process completed successfully.\n";
	}

	/**
	 * returns member's index given his ID
	 * 
	 * @param id
	 *            member's ID
	 * @return member's index in the members array
	 */
	public int searchID(int id) {
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (membersArray[i] != null))// linear search
		{
			if (membersArray[i].getId() == id) {
				index = i;
				found = true;

			}
			i++;
		}

		return index;
	}

	/**
	 * Removes a member given his ID
	 * 
	 * @param id
	 *            the member's ID
	 * @return message representing operation progress
	 */
	public String removeMember(int id) {
		String message = "";
		int index = searchID(id);
		if (index == -1)
			message = "Invalid ID"; // ID validation
		else if (getUserInterface().getBorrower().getUnreturnedBooks(id) != 0)
			message = "You must return the books you borrowed before leaving the system!\n";
		else {
			System.arraycopy(membersArray, index + 1, membersArray, index,
					membersArray.length - index - 1);
			dataSize--;
			message = "Member removed.\n";

		}
		return message;
	}

	/**
	 * Displays a list of all registered members
	 * 
	 * @return list of members
	 */
	public String displayMembers() {

		String message = "Members\n--------\n";
		int counter = 0;
		for (int i = 0; i < dataSize; i++) {
			counter++;
			message = message + counter + ")" + membersArray[i].getName()
					+ "\r\n";
		}
		if (counter == 0)
			message = "No available members\n";
		return message;

	}



}