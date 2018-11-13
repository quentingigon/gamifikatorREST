package gamifikator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {


	private String username;
	@Id
	private String email;
	private String password;

	public User() {}

	public User(String email, String username, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
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

	public void setUsername(String username) {
		this.username = username;
	}
}
