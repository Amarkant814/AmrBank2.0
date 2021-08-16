package com.project1.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project1.model.Account;
import com.project1.service.AccountService;
import com.project1.service.TransactionService;
import com.project1.service.impl.AccountServiceImpl;
import com.project1.service.impl.TransactionServiceImpl;
import com.project1.model.Transaction;
import com.project1.exception.BusinessException;
import com.project1.model.User;
import com.project1.service.UserService;
import com.project1.service.impl.UserServiceImpl;

/**
 * Servlet implementation class BankServiceController
 */
public class BankServiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankServiceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public static void updateTransaction(String type, double amount, int accountId) {
		TransactionService transactionService = new TransactionServiceImpl();
		Transaction transaction = new Transaction();
		transaction.setType(type);
		transaction.setTransactionAmount(amount);
		transaction.setAccountId(accountId);
		try {
			transactionService.createTransaction(transaction);
		} catch (BusinessException e) {
			e.getMessage();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = null;
		if (session == null) {

			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<center><h4><a href='/online_bank'>Click here to Login </a></center> ");
		} else {
			requestDispatcher=request.getRequestDispatcher("openaccount.html");
			requestDispatcher.include(request, response);
			User user = new User();
			UserService userService = new UserServiceImpl();
			try {
				user = userService.getUserByName(session.getAttribute("username").toString());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Account account = new Account();
			account.setUserId(user.getUserId());
			// if amount < 0
			account.setAccountBal(Double.parseDouble(request.getParameter("startingamount")));
			AccountService accountService = new AccountServiceImpl();
			try {
				account = accountService.createAccount(account);

			} catch (BusinessException e) {
				e.getMessage();
			}
			out.print("<center>**** Account created successfully ***</center> ");
			
			String type = "Credit";
			updateTransaction(type, account.getAccountBal(), account.getAccountId());
			out.print("<center><a href='success.html'>Click here to return</a></center>");

			
		}
	}

}
