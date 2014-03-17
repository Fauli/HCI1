package Jtable;


public class Person {

	private String gender;
	private String firstName;
	private String lastName;
	private String birthday;

	public Person(String gender, String firstName, String lastName,
			String birthday) {
		this.gender = gender;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;

	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

}
