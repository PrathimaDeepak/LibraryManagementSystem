package library;

/**
 * A Member object represent a library member,for each member, the system stores
 * his ID, name, address, street, city, phone number, age and e-mail.
 * 
 * @author Amr Nabil
 * 
 */
public class Member {
	/**
	 *member ID
	 */
	private String studentId;
	/**
	 * name (last and first)
	 */
	private String name;
	/**
	 * member's Address
	 */
	private int studentClass;
	
	public Member() {
		
	}
	
	public Member(String studentId, String name, int studentClass) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.studentClass = studentClass;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(int studentClass) {
		this.studentClass = studentClass;
	}
	
	

	
}