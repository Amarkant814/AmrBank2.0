package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class EmployeeController
 */
public class EmployeeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");
		String uname = request.getParameter("username").toString();
		System.out.println(uname);
		String pass = request.getParameter("password").toString();
		System.out.println(pass);
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		if(uname.equals("admin") && pass.equals("secured ")) {
			HttpSession session=request.getSession();
			session.setAttribute("emplogin", uname);
			response.sendRedirect("empsuccess.html");
		}
		else {
			requestDispatcher=request.getRequestDispatcher("emplogin.html");
			requestDispatcher.include(request, response);
			out.print("<center>Invalid Credentials</center>");
		}
	}

}
