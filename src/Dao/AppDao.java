package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import beans.User;

public class AppDao {
	private static String DB_NAME = "MyApp.db";
	// Version must be changed every time updates are made to the table (add a
	// column, etc.)
	private static final int DB_VER = 1;
	private static final String TABLE_NAME = "user";
	private static final String COLUMN_NAME_FULLNAME = "name";
	private static final String COLUMN_NAME_USERNAME = "username";
	private static final String COLUMN_NAME_PASSWORD = "password";
	private static final String COLUMN_NAME_ID = "id";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME
			+ " ("
			+ COLUMN_NAME_FULLNAME
			+ " TEXT,"
			+ COLUMN_NAME_USERNAME + " TEXT," + COLUMN_NAME_PASSWORD + " TEXT," 
			+ COLUMN_NAME_ID + " TEXT"
			+		")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	public AppDao() {
		try {
			Connection connection = DbHelper.getConnectionToDb();
			Statement sql = connection.createStatement();

			/*** ONLY UNCOMMENT BELOW TO DELETE ALL DATA IN THE USER TABLE ***/
//			sql.execute(SQL_DELETE_ENTRIES);

			sql.execute(SQL_CREATE_ENTRIES);

			System.out.println("Success!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String generateId(User user){
		Random rand = new Random();
		int id = rand.nextInt(859999999-85000000)+85000000;
		return Integer.toString(id);
	}

	public int registerUser(User user) {
		int rowsAffected = 0;

		try {
			// get the connection for the database
			Connection connection = DbHelper.getConnectionToDb();

			// write the insert query
			String insertQuery = "insert into user values(?,?,?,?)";
			
			// id for record
			String id = generateId(user);
			user.setId(id);

			// set parameters with PreparedStatement
			java.sql.PreparedStatement statement = connection
					.prepareStatement(insertQuery);
			statement.setString(1, user.getFullName());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassWord());
			statement.setString(4, user.getId());

			// execute the statement
			rowsAffected = statement.executeUpdate();

		} catch (SQLException exception) {
			System.out.println("ON INSERT: ");
			exception.printStackTrace();
		}

		return rowsAffected;
	}

	public boolean isValidUser(User user) {
		boolean isValid = false;
		List<User> users = new ArrayList<User>();
		users = getUsers();
		for (User u : users) {
			System.out.println();
			if (u.getUserName().equals(user.getUserName())
					&& u.getPassWord().equals(user.getPassWord())) {
				isValid = true;
			}
		}

		return isValid;
	}

	public User searchUser (String username){
		User user = null;
		List<User> users = new ArrayList<User>();
		try {
			// get connection to database
			Connection connection = DbHelper.getConnectionToDb();

			// write select query to get all users
			String sql = "select * from user where username=?";
			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			

			// execute the statement
			ResultSet set = statement.executeQuery();
			
			int count = 0;
			while (set.next()) {
				user = new User();
				user.setFullName(set.getString("name"));
				user.setUserName(set.getString("username"));
				user.setPassWord(set.getString("password"));
				user.setId(set.getString("id"));
				users.add(user);
				System.out.println(count + " USER: " + user);
				count+=1;
			}
			set.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		try{
			return users.get(0);
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Test class to test db persistance
	 * 
	 * @return
	 */
	public ArrayList<User> getUsers() {
		User user = null;
		ArrayList<User> users = new ArrayList<User>();
		try {
			// get connection to database
			Connection connection = DbHelper.getConnectionToDb();

			// write select query to get all users
			String sql = "select * from user";
			PreparedStatement statement = connection.prepareStatement(sql);
			// statement.setString(1, username);

			// execute query, get resultset and return users
			ResultSet set = statement.executeQuery();

			int count = 0;
			while (set.next()) {
				user = new User();
				user.setFullName(set.getString("name"));
				user.setUserName(set.getString("username"));
				user.setPassWord(set.getString("password"));
				user.setId(set.getString("id"));
				users.add(user);
				System.out.println(count + " USER: " + user);
				count += 1;
			}
			set.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return users;
	}
}