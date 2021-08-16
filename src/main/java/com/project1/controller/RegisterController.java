package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.UserService;
import com.project1.service.impl.UserServiceImpl;

/**
 * Servlet implementation class RegisterController
 */
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserService userService = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "http://localhost:8080/online_bank/";
		response.setContentType("text/html");
		User user = new User();
		int alreadyRegistered = 0;
		user.setUserName(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		PrintWriter out = response.getWriter();
		try {
			user = userService.createUser(user);
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
			alreadyRegistered = 1;
			out.print("<center>"+ user.getUserName()+" is already registered...<a href=\"+url+\">Click here to login</a></center>");
		}
		if(alreadyRegistered==0) {
			out.print("<center><h1> Registered Successfully</h1> <a href="+url+">Click here to login</a></center>");			
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
