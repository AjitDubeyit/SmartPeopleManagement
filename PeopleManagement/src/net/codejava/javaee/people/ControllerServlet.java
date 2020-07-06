package net.codejava.javaee.people;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PeopleDAO peopleDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		peopleDAO = new PeopleDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			case "/search":
				System.out.println("Inside search controller");
				searchPeople(request, response);
				break;
			default:
				listPeople(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPeople(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<People> listPeople = peopleDAO.listAllPeoples();
		request.setAttribute("listPeople", listPeople);
		RequestDispatcher dispatcher = request.getRequestDispatcher("peopleList.jsp");
		dispatcher.forward(request, response);
	}

	private void searchPeople(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<People> listPeople = peopleDAO.searchPeoples(request);
		request.setAttribute("listPeople", listPeople);
		RequestDispatcher dispatcher = request.getRequestDispatcher("peopleList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		People existingPeople = peopleDAO.getPeople(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
		request.setAttribute("people", existingPeople);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId = request.getParameter("emailId");

		People newBook = new People(firstName, lastName, emailId);
		peopleDAO.insertPeople(newBook);
		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailId =  request.getParameter("emailId");

		People book = new People(id, firstName, lastName, emailId);
		peopleDAO.updatePeople(book);
		response.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		People people = new People(id);
		peopleDAO.deleteBook(people);
		response.sendRedirect("list");

	}

}
