package gamifikator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User extends AbstractObject<Long> {

	private String lastName;
	private String firstName;
	private String email;
	private String password;

	public User() {}

	public User(String lastName, String firstName, String email, String password) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
