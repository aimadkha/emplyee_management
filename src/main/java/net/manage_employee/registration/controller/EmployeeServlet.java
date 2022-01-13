package net.manage_employee.registration.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import net.manage_employee.registration.dao.EmployeeDao;
import net.manage_employee.registration.model.Address;
import net.manage_employee.registration.model.Employee;
import net.manage_employee.registration.thymleaf.TemplateEngineUtil;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao;
	TemplateEngine engine;
	WebContext context;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeServlet() {
		this.employeeDao = new EmployeeDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
		/*
		 * String firstName = request.getParameter("firstName"); String lastName =
		 * request.getParameter("lastName"); String email =
		 * request.getParameter("email"); String password =
		 * request.getParameter("password"); String phone =
		 * request.getParameter("phone"); String city = request.getParameter("city");
		 * String country = request.getParameter("country"); String line =
		 * request.getParameter("line");
		 * 
		 * Employee employee = new Employee(firstName, lastName, email, phone); Address
		 * adress = new Address("76 taqdom", "yousssoufia", "maroc");
		 * 
		 * employee.setAddress(adress);
		 * 
		 * try { employeeDao.saveEmployee(employee); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } RequestDispatcher
		 * dispatcher =
		 * request.getRequestDispatcher("/WEB-INF/views/employeedetails.jsp");
		 * dispatcher.forward(request, response); //response.sendRedirect("");
		 * 
		 */
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			showNewForm(request, response);
			break;

		case "/insert":
			insertEmployee(request, response);
			break;

		case "/delete":
			deleteEmployee(request, response);
			break;

		case "/edit":
			showEditForm(request, response);
			break;

		case "/update":
			updateEmployee(request, response);
			break;

		default:
			break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		this.context = new WebContext(request, response, request.getServletContext());
		response.setCharacterEncoding("utf-8");
		engine.process("add_employee.html", context, response.getWriter());
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		this.context = new WebContext(request, response, request.getServletContext());
		response.setCharacterEncoding("utf-8");
		Long id = Long.parseLong(request.getParameter("id"));
		Employee existingEmployee = employeeDao.getEmployeeById(id);
		context.setVariable("editEmp", existingEmployee);
		engine.process("edit_employee.html", context, response.getWriter());
	}
	
	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
	}

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		this.context = new WebContext(request, response, request.getServletContext());
		response.setCharacterEncoding("utf-8");
		
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		//String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String line = request.getParameter("line");

		
		Employee employee = new Employee(firstName,lastName,email,phone);
		Address adress = new Address(line, city, country);
		
		employee.setAddress(adress);
		
		try {
			employeeDao.saveEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.setVariable("name", firstName);
		engine.process("employeedetails.html", context, response.getWriter());
	}
	
	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Long id = Long.parseLong(request.getParameter("id"));
		employeeDao.deleteEmployee(id);
		response.sendRedirect("list");
	}


}
