package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.UserService;
import com.project1.service.impl.UserServiceImpl;

/**
 * Servlet implementation class ViewUser
 */
public class ViewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		if (session == null) {
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<center><h4><a href='/online_bank'>Click here to Login </a></center>  ");
		} else {
			requestDispatcher = request.getRequestDispatcher("empsuccess.html");
			requestDispatcher.include(request, response);
			List<User> userList = new ArrayList<>();
			UserService userservice = new UserServiceImpl();
			try {
				userList = userservice.getAllUsers();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			out.print("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<u>UserId</u>&emsp;&emsp;&emsp;&emsp;<u>Username</u><br>");
			for(User usr: userList) {
				out.print("&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+usr.getUserId()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+usr.getUserName()+"<br>");
			}
		}
	}

}
