package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import beans.Article;
import beans.User;

public class ArticlesDao {
	private static String DB_NAME = "MyApp.db";
	// Version must be changed every time updates are made to the table (add a
	// column, etc.)
	private static final int DB_VER = 1;
	private static final String TABLE_NAME = "article";
	private static final String COLUMN_NAME_TITLE = "title";
	private static final String COLUMN_NAME_POST = "post";
	private static final String COLUMN_NAME_USER = "user";
//	private static final String COLUMN_NAME_ID = "id";
	private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_NAME
			+ " ("
			+ COLUMN_NAME_TITLE
			+ " TEXT,"
			+ COLUMN_NAME_POST + " TEXT," + COLUMN_NAME_USER +  " TEXT"
			+		")";

	private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
			+ TABLE_NAME;

	public ArticlesDao() {
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

	public int postArticle(Article newArticle) {
		int rowsAffected = 0;

		try {
			// get the connection for the database
			Connection connection = DbHelper.getConnectionToDb();

			// write the insert query
			String insertQuery = "insert into article values(?,?,?)";
			

			// set parameters with PreparedStatement
			java.sql.PreparedStatement statement = connection
					.prepareStatement(insertQuery);
			statement.setString(1, newArticle.getTitle());
			statement.setString(2, newArticle.getPostContent());
			statement.setString(3, newArticle.getCreator());

			// execute the statement
			rowsAffected = statement.executeUpdate();

		} catch (SQLException exception) {
			System.out.println("ON INSERT: ");
			exception.printStackTrace();
		}

		return rowsAffected;
	}
	
//	public String generateId(User user){
//		Random rand = new Random();
//		int id = rand.nextInt(859999999-85000000)+85000000;
//		return Integer.toString(id);
//	}
//
//	public int registerUser(User user) {
//		int rowsAffected = 0;
//
//		try {
//			// get the connection for the database
//			Connection connection = DbHelper.getConnectionToDb();
//
//			// write the insert query
//			String insertQuery = "insert into user values(?,?,?,?)";
//			
//			// id for record
//			String id = generateId(user);
//			user.setId(id);
//
//			// set parameters with PreparedStatement
//			java.sql.PreparedStatement statement = connection
//					.prepareStatement(insertQuery);
//			statement.setString(1, user.getFullName());
//			statement.setString(2, user.getUserName());
//			statement.setString(3, user.getPassWord());
//			statement.setString(4, user.getId());
//
//			// execute the statement
//			rowsAffected = statement.executeUpdate();
//
//		} catch (SQLException exception) {
//			System.out.println("ON INSERT: ");
//			exception.printStackTrace();
//		}
//
//		return rowsAffected;
//	}
//
//	public boolean isValidUser(User user) {
//		boolean isValid = false;
//		List<User> users = new ArrayList<User>();
//		users = getUsers();
//		for (User u : users) {
//			System.out.println();
//			if (u.getUserName().equals(user.getUserName())
//					&& u.getPassWord().equals(user.getPassWord())) {
//				isValid = true;
//			}
//		}
//
//		return isValid;
//	}
//
//	public User searchUser (String username){
//		User user = null;
//		List<User> users = new ArrayList<User>();
//		try {
//			// get connection to database
//			Connection connection = DbHelper.getConnectionToDb();
//
//			// write select query to get all users
//			String sql = "select * from user where username=?";
//			java.sql.PreparedStatement statement = connection.prepareStatement(sql);
//			statement.setString(1, username);
//			
//
//			// execute the statement
//			ResultSet set = statement.executeQuery();
//			
//			int count = 0;
//			while (set.next()) {
//				user = new User();
//				user.setFullName(set.getString("name"));
//				user.setUserName(set.getString("username"));
//				user.setPassWord(set.getString("password"));
//				user.setId(set.getString("id"));
//				users.add(user);
//				System.out.println(count + " USER: " + user);
//				count+=1;
//			}
//			set.close();
//		} catch (SQLException exception) {
//			exception.printStackTrace();
//		}
//		try{
//			return users.get(0);
//		}
//		catch (Exception e){
//			e.printStackTrace();
//			return null;
//		}
//	}
//
	/**
	 * Test class to test db persistance
	 * 
	 * @return
	 */
	public ArrayList<Article> getArticles() {
		Article article = null;
		ArrayList<Article> articles = new ArrayList<Article>();
		try {
			// get connection to database
			Connection connection = DbHelper.getConnectionToDb();

			// write select query to get all users
			String sql = "select * from article";
			PreparedStatement statement = connection.prepareStatement(sql);
			// statement.setString(1, username);

			// execute query, get resultset and return users
			ResultSet set = statement.executeQuery();

			int count = 0;
			while (set.next()) {
				article = new Article();
				article.setTitle(set.getString("title"));
				article.setPostContent(set.getString("post"));
				article.setCreator(set.getString("user"));
				articles.add(article);
				System.out.println(count + " ARTICLE: " + article);
				count += 1;
			}
			set.close();
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return articles;
	}
}
