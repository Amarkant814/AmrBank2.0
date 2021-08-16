package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EmployeeSuccess
 */
public class EmployeeSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeSuccess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if (session == null) {
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/online_bank'>Click here to Login </a> ");
		} else {
			out.print("<h1>Welcome " + session.getAttribute("emplogin") + " ..... You have logged in successfully at "
					+ new Date(session.getCreationTime()) + "</h1>");
			
			out.print("<a href='logout'>Click Here to LOGOUT</a>");
		}
	}

}
