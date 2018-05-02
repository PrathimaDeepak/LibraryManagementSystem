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
	private int id;
	/**
	 * name (last and first)
	 */
	private String name;
	/**
	 * member's Address
	 */
	private String address;
	/**
	 * Street name
	 */
	private String street;
	/**
	 * city name
	 */
	private String city;
	/**
	 *phone number
	 */
	private String phoneNumber;
	/**
	 * member's age
	 */
	private int age;
	/**
	 *e-mail adress
	 */
	private String email;

	/**
	 * Default constructor
	 */
	public Member() {
		this(0, "", "", "", "", "", 0, "");
	}

	/**
	 * @param id
	 *            member's Id
	 * @param name
	 *            member's name
	 * @param address
	 *            member's address
	 * @param street
	 *            street
	 * @param city
	 *            city
	 * @param phoneNumber
	 *            member's phone number
	 * @param age
	 *            member's age
	 * @param email
	 *            member's e-mail
	 */
	public Member(int id, String name, String address, String street,
			String city, String phoneNumber, int age, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.street = street;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.age = age;
		this.email = email;
	}

	// setters and getters for class fields
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the e-mail
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the e-mail to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * member data is represented in a comma delimited line
	 * 
	 * @see java.lang.Object#toString()
	 * @return formatted string
	 */
	public String toString() {
		return id + "," + name + "," + address + "," + street + "," + city
				+ "," + phoneNumber + "," + age + "," + email;
	}
}