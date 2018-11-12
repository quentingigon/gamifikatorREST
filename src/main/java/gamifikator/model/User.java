package gamifikator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {


	private String userName;
	@Id
	private String email;
	private String password;

	public User() {}

	public User(String email, String userName, String password) {
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return userName;
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

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
