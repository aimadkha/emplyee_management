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
 * Servlet implementation class TestThymeleaf
 */
@WebServlet("/registration")
public class TestThymeleaf extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeeDao employeeDao = new EmployeeDao();

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestThymeleaf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		response.setCharacterEncoding("utf-8");
		context.setVariable("message", "test template engine");
		engine.process("add_employee.html", context, response.getWriter());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
		WebContext context = new WebContext(request, response, request.getServletContext());
		response.setCharacterEncoding("utf-8");
		
		String frstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		//String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String line = request.getParameter("line");

		
		Employee employee = new Employee(frstName,lastName,email,phone);
		Address adress = new Address(line, city, country);
		
		employee.setAddress(adress);
		
		try {
			employeeDao.saveEmployee(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/templates/employeedetails.html");
		dispatcher.forward(request, response);
		//response.sendRedirect("");
	}

	

}
