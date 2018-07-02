package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import beans.User;
import Dao.AppDao;
import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;

public class LoggedIn extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoggedIn() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User newUser = (User) session.getAttribute("user");
		
		if (newUser == null) {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
		
		else{
			String un = newUser.getUserName();
			String pw = newUser.getPassWord();
			AppDao ad = new AppDao();
			request.setAttribute("username", un);
			request.setAttribute("password", pw);
			if (newUser.getUserName().equals("admin")) {
				ArrayList<User> allRecords = ad.getUsers();

				User foundUser = ad.searchUser(newUser.getUserName());
				String fullname = foundUser.getFullName();
				request.setAttribute("fullname", fullname);
				request.setAttribute("id", foundUser.getId());

				request.setAttribute("records", allRecords);
				
				// set session to keep user logged in
				session.setAttribute("user", newUser);
				
				request.getRequestDispatcher("/Home.jsp")
						.forward(request, response);
			} else{
				User foundUser = ad.searchUser(newUser.getUserName());
				String fullname = foundUser.getFullName();
				request.setAttribute("id", foundUser.getId());
				request.setAttribute("fullname", fullname);
				
				// set session to keep user logged in
				session.setAttribute("user", newUser);
				
				request.getRequestDispatcher("/Home.jsp")
						.forward(request, response);
			} 
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User newUser = (User) session.getAttribute("user");

		if (newUser == null) {
			String un = request.getParameter("username");
			String pw = request.getParameter("password");

			request.setAttribute("username", un);
			request.setAttribute("password", pw);

			newUser = new User();
			newUser.setUserName(un);
			newUser.setPassWord(pw);
		}

		// Set up connection to dao and insert the user
		AppDao ad = new AppDao();
		boolean isValid = ad.isValidUser(newUser);
		System.out.println("VALID: " + isValid);

		// forward control to loggedin jsp
		// If admin display all users
		if (newUser.getUserName().equals("admin") && isValid) {
			ArrayList<User> allRecords = ad.getUsers();

			User foundUser = ad.searchUser(newUser.getUserName());
			String fullname = foundUser.getFullName();
			request.setAttribute("fullname", fullname);
			request.setAttribute("id", foundUser.getId());

			request.setAttribute("records", allRecords);
			
			// set session to keep user logged in
			session.setAttribute("user", newUser);
			
			request.getRequestDispatcher("/Home.jsp")
					.forward(request, response);
		} else if (isValid) {
			User foundUser = ad.searchUser(newUser.getUserName());
			String fullname = foundUser.getFullName();
			request.setAttribute("id", foundUser.getId());
			request.setAttribute("fullname", fullname);
			
			// set session to keep user logged in
			session.setAttribute("user", newUser);
			
			request.getRequestDispatcher("/Home.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("/index.jsp").forward(request,
					response);
		}
	}

	public String getHTMLString(String filePath, String message)
			throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}

		reader.close();
		String page = buffer.toString();

		page = MessageFormat.format(page, message);

		return page;

	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}