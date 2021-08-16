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
import com.project1.service.AccountService;
import com.project1.service.impl.AccountServiceImpl;

/**
 * Servlet implementation class ViewAccount
 */
public class ViewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		RequestDispatcher requestDispatcher = null;
		if (session == null) {
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/online_bank'>Click here to Login </a> ");
		} else {
			requestDispatcher = request.getRequestDispatcher("empsuccess.html");
			requestDispatcher.include(request, response);
			AccountService accountService = new AccountServiceImpl();
			List<Account> accountList = new ArrayList<>();
			try {
				accountList = accountService.getAllAccounts();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			out.print("&emsp;&emsp;&emsp;&emsp;<u>AccountId</u>&emsp;&emsp;&emsp;&emsp;<u>AccountBal</u>"
					+ "&emsp;&emsp;&emsp;&emsp;<u>Valid</u>&emsp;&emsp;&emsp;&emsp;<u>UserId</u><br>");
			
			for (Account acc : accountList) {
				out.print("&emsp;&emsp;&emsp;&emsp;"+acc.getAccountId()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+acc.getAccountBal()+"&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;"+acc.isValid()+
						"&emsp;&emsp;&emsp;&emsp;"+acc.getUserId()+"<br>");
			}
		}
	}
}
