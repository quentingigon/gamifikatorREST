package gamifikator.services;

import gamifikator.model.User;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class UserDAO implements UserDAOLocal{

	@Resource(name = "jdbc/gamifikator")
	DataSource dataSource;

	@Override
	public boolean create(User user) {
		boolean added = false;

		try (Connection connection = dataSource.getConnection()) {

			// if user doesnt already exists
			if (findByEmail(user.getEmail()).getEmail().equals("")) {
				String addQuery = "INSERT INTO users(lastName ,firstName , email, password) VALUES(?,?,?,?)";

				PreparedStatement ps = connection.prepareStatement(addQuery);

				ps.setString(1, user.getLastName());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());

				added = true;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return added;
	}

	@Override
	public void update(User user) {

	}

	@Override
	public void delete(User user) {
		String query = "DELETE FROM users WHERE email = " + user.getEmail();

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public User findById(User id) {
		return null;
	}

	public User findByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = " + email;

		User user = new User();

		try (Connection connection = dataSource.getConnection()) {
			ResultSet rs = connection
				.prepareStatement(query)
				.executeQuery();
			rs.next();
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return user;
	}

	/*
	@Override
	public User getUser(String email) {
		String query = "SELECT * FROM users WHERE email = " + email;

		User user = new User();

		try (Connection connection = dataSource.getConnection()) {
			ResultSet rs = connection
				.prepareStatement(query)
				.executeQuery();
			rs.next();
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return user;

	}

	@Override
	public boolean addUser(User user) {

		String searchQuery = "SELECT email FROM users WHERE email = " + user;
		boolean added = false;

		try (Connection connection = dataSource.getConnection()) {

			// if user doesnt already exists
			if (getUser(user.getEmail()).getEmail().equals("")) {
				String addQuery = "INSERT INTO users(lastName ,firstName , email, password) VALUES(?,?,?,?)";

				PreparedStatement ps = connection.prepareStatement(addQuery);

				ps.setString(1, user.getLastName());
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getEmail());
				ps.setString(4, user.getPassword());

				added = true;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return added;

	}

	@Override
	public boolean deleteUser(String email) {

		String query = "DELETE FROM users WHERE email = " + email;
		boolean deleted = false;

		try (Connection connection = dataSource.getConnection()) {
			PreparedStatement preparedStmt = connection.prepareStatement(query);
			preparedStmt.execute();
			deleted = true;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return deleted;

	}*/
}