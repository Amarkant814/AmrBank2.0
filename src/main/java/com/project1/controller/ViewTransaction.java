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

import com.project1.model.Transaction;
import com.project1.service.TransactionService;
import com.project1.service.impl.TransactionServiceImpl;
import com.project1.exception.BusinessException;
import com.project1.model.Account;
import com.project1.service.AccountService;
import com.project1.service.impl.AccountServiceImpl;

/**
 * Servlet implementation class ViewTransaction
 */
public class ViewTransaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTransaction() {
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
			out.print("<center><h4><a href='/online_bank'>Click here to Login </a></center> ");
		} else {
			requestDispatcher = request.getRequestDispatcher("empsuccess.html");
			requestDispatcher.include(request, response);
			List<Transaction> transactionList = new ArrayList<>();
			TransactionService ts = new TransactionServiceImpl();
			try {
				transactionList = ts.getAllTransactions();
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.getMessage();
			}
			out.print("<br>&emsp;&emsp;&emsp;&emsp;<u>TransactionId</u>&emsp;&emsp;&emsp;&emsp;<u>Type</u>"
					+ "&emsp;&emsp;&emsp;&emsp;<u>Amount</u>&emsp;&emsp;&emsp;&emsp;<u>AccountNo</u><br>");
			for(Transaction tsList : transactionList) {
				out.print("&emsp;&emsp;&emsp;&emsp;"+tsList.getTransactionId()+"&emsp;&emsp;&emsp;&emsp;&emsp;"+tsList.getType()+"&emsp;&emsp;&emsp;&emsp;"+tsList.getTransactionAmount()+
						"&emsp;&emsp;&emsp;&emsp;"+tsList.getAccountId()+"<br>");
			}
		}
	}

}
