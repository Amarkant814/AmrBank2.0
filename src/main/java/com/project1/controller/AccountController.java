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
import com.project1.model.Account;
import com.project1.model.User;
import com.project1.service.AccountService;
import com.project1.service.UserService;
import com.project1.service.impl.AccountServiceImpl;
import com.project1.service.impl.UserServiceImpl;

/**
 * Servlet implementation class AccountController
 */
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = null;
		requestDispatcher = request.getRequestDispatcher("success.html");
		if (session == null) {

			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<center><h4><a href='/online_bank'>Click here to Login </a></center> ");
		}else {
			User user = null;
			requestDispatcher.include(request, response);
			UserService userService = new UserServiceImpl();
			try {
				user = userService.getUserByName(session.getAttribute("username").toString());
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			AccountService accountService = new AccountServiceImpl();
			List<Account> accountList = new ArrayList<>();
			try {
				accountList = accountService.getAccountByUserId(user.getUserId());
			} catch (BusinessException e) {
				out.print(e);
			}
			
			out.print("&emsp;&emsp;&emsp;&emsp;<u>AccountId</u>&emsp;&emsp;&emsp;&emsp;<u>AccountBal</u>"
					+ "&emsp;&emsp;&emsp;&emsp;<u>Valid</u>&emsp;&emsp;&emsp;&emsp;<u>UserId</u><br>");
			
			for (Account acc:accountList) {
				out.print("&emsp;&emsp;&emsp;&emsp;"+acc.getAccountId()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+acc.getAccountBal()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+acc.isValid()+
						"&emsp;&emsp;&emsp;&emsp;"+acc.getUserId()+"<br>");
			}
			
		}
		
	}

}
