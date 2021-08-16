package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.exception.BusinessException;
import com.project1.service.AccountService;
import com.project1.service.TransactionService;
import com.project1.service.impl.AccountServiceImpl;
import com.project1.service.impl.TransactionServiceImpl;

/**
 * Servlet implementation class Delete
 */
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
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
			int accountId = Integer.parseInt(request.getParameter("accountno"));
			TransactionService ts = new TransactionServiceImpl();
			try {
				ts.deleteTransaction(accountId);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			
			AccountService accountService = new AccountServiceImpl();
			try {
				accountService.deleteAccount(accountId);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			
			out.print("<center>Account deleted successfully</center>");
		}
	}

}
