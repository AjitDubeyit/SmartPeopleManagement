package net.codejava.javaee.people;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class BookDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertPeople(People people) throws SQLException {
		String sql = "INSERT INTO PEOPLE (first_name, last_name, email_address) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, people.getFirstName());
		statement.setString(2, people.getLastName());
		statement.setString(3, people.getEmailId());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<People> listAllPeoples() throws SQLException {
		List<People> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM PEOPLE";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("people_id");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String emailId = resultSet.getString("email_address");
			
			People people = new People(id, firstName, lastName, emailId);
			listBook.add(people);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	
	public List<People> searchPeoples(HttpServletRequest request) throws SQLException {
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		System.out.println("Search by Name : " + name + " Email: "+ email);
		List<People> listBook = new ArrayList<>();
		
		String query = null;
		
		connect();
		Statement statement = jdbcConnection.createStatement();
		if(name==null || name=="")
			query = "select people_id, first_name, last_name, email_address from people where email_address='"+email.trim()+"'";
		else
			query = "select people_id, first_name, last_name, email_address from people where first_name='"+name.trim()+"'";
		
		ResultSet resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("people_id");
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String emailId = resultSet.getString("email_address");
			
			People people = new People(id, firstName, lastName, emailId);
			listBook.add(people);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	
	public boolean deleteBook(People people) throws SQLException {
		String sql = "DELETE FROM PEOPLE where people_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, people.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updatePeople(People people) throws SQLException {
		String sql = "UPDATE PEOPLE SET first_name = ?, last_name = ?, email_address = ?";
		sql += " WHERE people_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, people.getFirstName());
		statement.setString(2, people.getLastName());
		statement.setString(3, people.getEmailId());
		statement.setInt(4, people.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public People getPeople(int id) throws SQLException {
		People people = null;
		String sql = "SELECT * FROM people WHERE people_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String firstName = resultSet.getString("first_name");
			String lastName = resultSet.getString("last_name");
			String emailId = resultSet.getString("email_address");
			people = new People(id, firstName, lastName, emailId);
		}
		
		resultSet.close();
		statement.close();
		
		return people;
	}
}
