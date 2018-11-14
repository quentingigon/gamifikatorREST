package gamifikator.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {


	private String username;
	@Id
	private String email;
	private String password;
	private boolean isAdmin;
	private boolean isSuspended;

	public User() {}

	public User(String email, String username, String password, boolean isAdmin, boolean isSuspended) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isSuspended = isSuspended;
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

	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean isSuspended() {
		return isSuspended;
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

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public void setSuspended(boolean suspended) {
		isSuspended = suspended;
	}
}
