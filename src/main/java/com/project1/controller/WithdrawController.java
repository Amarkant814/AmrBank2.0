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

import org.apache.log4j.Logger;

import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.model.Transaction;
import com.project1.model.User;
import com.project1.service.AccountService;
import com.project1.service.TransactionService;
import com.project1.service.UserService;
import com.project1.service.impl.AccountServiceImpl;
import com.project1.service.impl.TransactionServiceImpl;
import com.project1.service.impl.UserServiceImpl;

/**
 * Servlet implementation class WithdrawController
 */
public class WithdrawController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WithdrawController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private static Logger log = Logger.getLogger(WithdrawController.class);
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = null;
		requestDispatcher = request.getRequestDispatcher("withdraw.html");
		if (session == null) {

			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/online_bank'>Click here to Login </a> ");
		} else {
			User user = new User();
			AccountService accountService = new AccountServiceImpl();
			requestDispatcher.include(request, response);
			UserService userService = new UserServiceImpl();
			try {
				user = userService.getUserByName(session.getAttribute("username").toString());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Account account = null;
			int accountId =Integer.parseInt(request.getParameter("accountno"));
			
			try {
				account = accountService.getAccountById(accountId);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			boolean valid=false;
			List<Account> accountList = new ArrayList<>();
			try {
				accountList = accountService.getAccountByUserId(user.getUserId());
			} catch (BusinessException e) {
				e.getMessage();
			}
			for(Account acc: accountList) {
				if(acc.getAccountId()==account.getAccountId()) {
					valid=true;
				}
			}
			
			if(valid) {
				double withdrawal = 0;
				withdrawal = Double.parseDouble(request.getParameter("withdrawal"));
				if (account.getAccountBal() < withdrawal) {
					out.print("<center>Insufficient Balance..</center> ");
				} else if (withdrawal < 0) {
					out.print("<center>Please enter positive value</center>");
				} else {
					account.setAccountBal(account.getAccountBal() - withdrawal);
					out.print("<center>Transaction Successfull</center><br><center><a href='success.html'>Click here to return</a></center>"); 
					try {
						account = accountService.updateAccount(account.getAccountId(), account.getAccountBal());
					} catch (BusinessException e) {
						e.getMessage();
					}
					updateTransaction("Debit", withdrawal, account.getAccountId());
					
				}
			}
			else
				out.print("<center><h2>Withdrawal is possible only from user's own account..</center>");
				log.info("from withdraw controller");
		}
	}

}
