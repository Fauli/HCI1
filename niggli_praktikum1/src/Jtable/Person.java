package Jtable;

import java.util.Date;

public class Person {
	public enum Gender {
		MALE, FEMALE
	}

	private Gender gender;
	private String firstName;
	private String lastName;
	private Date birthday;
}