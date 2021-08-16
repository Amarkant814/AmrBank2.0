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
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private UserService userService = new UserServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		User user = new User();
		user.setUserName(request.getParameter("username"));
		System.out.println(user.getUserName());
		user.setPassword(request.getParameter("password"));
		RequestDispatcher requestDispatcher = null;
		PrintWriter out = response.getWriter();
		User userdb = new User();
		try {
			userdb = userService.getUserByName(user.getUserName());
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(user.getPassword().equals(userdb.getPassword())) {
			HttpSession session=request.getSession();
			session.setAttribute("username", user.getUserName());
			response.sendRedirect("success.html");
		}
		else {
			requestDispatcher=request.getRequestDispatcher("login.html");
			requestDispatcher.include(request, response);
			out.print("<center>Invalid credentials</center>");
		}
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset = UTF-8");
		Gson gson = new Gson();
		User user = gson.fromJson(request.getReader(), User.class);
		System.out.println(user);
	}

}
