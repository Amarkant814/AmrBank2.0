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
 * Servlet implementation class DepositController
 */
public class DepositController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(DepositController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private UserService userService = new UserServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
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
			log.info(e);
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		RequestDispatcher requestDispatcher = null;
		requestDispatcher = request.getRequestDispatcher("deposit.html");
		if (session == null) {

			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<center><h4><a href='/online_bank'>Click here to Login </a> </center>");
		} else {
			log.info("hello from log");
			User user = new User();
			UserService userService = new UserServiceImpl();
			try {
				user = userService.getUserByName(session.getAttribute("username").toString());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				log.info(e);
			}
			double withdrawal = 0;
			// log.info("Select your account:");
			Account account = null;
			int accountId =Integer.parseInt(request.getParameter("accountno"));
			AccountService accountService = new AccountServiceImpl();
			try {
				account = accountService.getAccountById(accountId);
			} catch (BusinessException e1) {
				// TODO Auto-generated catch block
				log.info(e1);
			}
			//log.info("Enter amount to withdraw:");
			withdrawal = Double.parseDouble(request.getParameter("withdrawal"));
			if (withdrawal < 0) {
				out.print("<center>Please enter positive value</center>");
			} else {
				requestDispatcher.include(request, response);
				account.setAccountBal(account.getAccountBal() + withdrawal);
				out.print("<center>Transaction Successfull</center><br><center><a href='success.html'>Click here to return</a></center>"); 
				try {
					account = accountService.updateAccount(account.getAccountId(), account.getAccountBal());
				} catch (BusinessException e) {
					log.info(e);
				}
				updateTransaction("Credit", withdrawal, account.getAccountId());
				log.info("from deposit controller");
				
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset = UTF-8");
		if (session == null) {
			out.print("<center><h1>Please Login First</h1></center>");
			out.print("<h4><a href='/login_web_app_with_session_demo'>Click here to Login </a> ");
		} else {
			User user = null;
			try {
				user = userService.getUserByName(session.getAttribute("username").toString());
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			double depo = 0;
			AccountService accountService = new AccountServiceImpl();
			List<Account> accountList = new ArrayList<>();
			try {
				accountList = accountService.getAccountByUserId(user.getUserId());
			} catch (BusinessException e) {
			}
			out.print(request.getParameter("startingamount"));
//			out.print("Select your account:");
//			for (int i = 0; i < accountList.size(); i++) {
//				log.info(i + ". " + accountList.get(i));
//			}
//			int index = 1;
//			try {
//				index = Integer.parseInt(sc.nextLine());
//			} catch (NumberFormatException e) {
//
//			}
//
//			Account account = new Account();
//			account = accountList.get(index);
//			log.info("Enter amount to deposit:");
//			depo = sc.nextDouble();
//			if (depo < 0) {
//				log.error("Invalid amount");
//			} else {
//				account.setAccountBal(account.getAccountBal() + depo);
//				log.info("Transaction Successfull");
//				try {
//					account = accountService.updateAccount(account.getAccountId(), account.getAccountBal());
//				} catch (BusinessException e) {
//					log.error(e);
//				}
//				updateTransaction("Credit", depo, account.getAccountId());
//			}
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
