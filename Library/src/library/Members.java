package library;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
		boolean isDuplicate = checkForDuplicateStudentID(aMember.getStudentId(), membersArray);
		if(!isDuplicate) {
			int index = dataSize;
			membersArray[index] = aMember;
			dataSize++; // incrementing data size
			writeToMembersFile(membersArray,dataSize);
			return "\nRegistration process completed successfully.\n";
		}else {
			return "\nStudent ID already exists\n";
		}
		
	}
	
	private boolean checkForDuplicateStudentID(String studentId, Member[] membersArray) {
		for(int i=0; i< membersArray.length; i++) {
			if(membersArray[i].getStudentId().equalsIgnoreCase(studentId)) {
				return true;
			}
		}
		return false;
	}
	
	public void writeToMembersFile(Member[] membersArray, int dataSize) {
		final String file_name = "src/resources/members.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			StringBuilder content = new StringBuilder();
			for(int i=0; i<= dataSize ; i++) {
				Member member = membersArray[i];
				if(member != null) {
					content.append(member.getStudentId()+","+member.getName()+","+member.getStudentClass()+"\n");
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
	 * returns member's index given his ID
	 * 
	 * @param id
	 *            member's ID
	 * @return member's index in the members array
	 */
	public int searchStudentById(String studentId) {
		int index = -1;
		int i = 0;
		boolean found = false;
		while ((!found) && (membersArray[i] != null))// linear search
		{
			if (membersArray[i].getStudentId().equalsIgnoreCase(studentId)) {
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
	public String removeMember(String studentId) {
		String message = "";
		int index = searchStudentById(studentId);
		if (index == -1)
			message = "Invalid ID"; // ID validation
//		else if (getUserInterface().getBorrower().getUnreturnedBooks(id) != 0)
//			message = "You must return the books you borrowed before leaving the system!\n";
		else {
			System.arraycopy(membersArray, index + 1, membersArray, index,
					membersArray.length - index - 1);
			dataSize--;
			writeToMembersFile(membersArray,dataSize);
			
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