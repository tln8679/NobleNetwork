package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.AppDao;
import Dao.ArticlesDao;
import beans.Article;
import beans.User;

public class NewPost extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NewPost() {
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
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArticlesDao ad = new ArticlesDao();
		request.setAttribute("allArticles", ad.getArticles());
		request.getRequestDispatcher("/Articles.jsp").forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String article = request.getParameter("postcontent");
		String un = request.getParameter("username");
		System.out.println(title +" "+ article + " "+ un);
		
		Article newArticle = new Article();
		newArticle.setTitle(title);
		newArticle.setPostContent(article);
		newArticle.setCreator(un);
		
		// Set up connection to dao and insert the user
		ArticlesDao ad = new ArticlesDao();
		int rows = ad.postArticle(newArticle);
		
		// prepare an information message for user about the success or failure of the operation
		String infoMessage = null;
		if(rows==0){
			infoMessage="Sorry, an error occurred!";
		}
		else{
			infoMessage="User registered successfully!";
		}
		System.out.println(infoMessage);
		
		request.setAttribute("allArticles", ad.getArticles());
		
		request.getRequestDispatcher("/Articles.jsp").forward(request, response);
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
