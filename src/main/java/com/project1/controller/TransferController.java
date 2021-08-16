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
import com.project1.model.Transaction;
import com.project1.model.User;
import com.project1.service.AccountService;
import com.project1.service.TransactionService;
import com.project1.service.UserService;
import com.project1.service.impl.AccountServiceImpl;
import com.project1.service.impl.TransactionServiceImpl;
import com.project1.service.impl.UserServiceImpl;

/**
 * Servlet implementation class TransferController
 */
public class TransferController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = null;
		requestDispatcher = request.getRequestDispatcher("transfer.html");
		if (session == null) {

			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<center><h4><a href='/online_bank'>Click here to Login </a> </center>");
		} else {
			User user = new User();
			requestDispatcher.include(request, response);
			UserService userService = new UserServiceImpl();
			try {
				user = userService.getUserByName(session.getAttribute("username").toString());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double withdrawal = 0;
			// log.info("Select your account:");
			Account account1 = null;
			Account account2 = null;
			int accountId1 = Integer.parseInt(request.getParameter("accountno1"));
			int accountId2 = Integer.parseInt(request.getParameter("accountno2"));
			AccountService accountService = new AccountServiceImpl();
			try {
				account1 = accountService.getAccountById(accountId1);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				account2 = accountService.getAccountById(accountId2);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			boolean valid = false;
			List<Account> accountList = new ArrayList<>();
			try {
				accountList = accountService.getAccountByUserId(user.getUserId());
			} catch (BusinessException e) {
				e.getMessage();
			}
			for (Account acc : accountList) {
				if (acc.getAccountId() == account2.getAccountId()) {
					valid = true;
				}
			}
			if (valid) {
				withdrawal = Double.parseDouble(request.getParameter("withdrawal"));
				if (withdrawal < 0) {
					out.print("<center>Please enter positive value</center>");
				} else if (account2.getAccountBal() < withdrawal) {
					out.print("<center>Insufficient Balance..</center> ");
				} else {
					account1.setAccountBal(account1.getAccountBal() + withdrawal);
					account2.setAccountBal(account2.getAccountBal() - withdrawal);

					out.print(
							"<center>Transaction Successfull</center><br><center><a href='success.html'>Click here to return</a></center>");
					try {
						account1 = accountService.updateAccount(account1.getAccountId(), account1.getAccountBal());
					} catch (BusinessException e) {
						e.getMessage();
					}
					try {
						account2 = accountService.updateAccount(account2.getAccountId(), account2.getAccountBal());
					} catch (BusinessException e) {
						e.getMessage();
					}
					updateTransaction("Debit", withdrawal, account1.getAccountId());
					updateTransaction("Credit", withdrawal, account2.getAccountId());
				}

			}

			else
				out.print("<center><h2>Withdrawal is possible only from user's own account..</center>");

		}
	}

}
