# AmrBank2.0

## An Overview
 
 This project is a Java based application along with Bootstrap and JavaScript for the UI part that has abilities to perform banking 
 operations. There are users of who can login to their account and then deposit, withdraw, transfer and
 view account balance in this application.
 
 Also there is the bank employee who can view all the users, accounts and transactions in the app. The employee can also 
 approve or reject an account if he finds something mischieveous related to the account. 
 
 ### Internal Implementation
 
 -Servlets are used to get the http request and act as controller for the application. <br>
 -HttpSession is used for session management.<br>
 -Request Dispatcher is used to redirect request.<br>
 -Jackson Library is used to convert POJO to JSON and vice versa.
 
 ## User Domain
 
 1. Open Account - A user can open account with a starting amount. 
 2. Deposit - The user can deposit money in his account. 
 3. Withdraw - The user can withdraw moeney from his account provided he as sufficient balance in that account.
 4. Transfer - He can transfer money from one of his account to another account. Again he must have sufficient balance.
 5. View Balance - The user can view account balance by selecting the account he wants to check.
 
 ## Employee Domain
 
 1. View Users - The employee has the access to see all the users enrolled to the bank.
 2. View Accounts - The employee can also have eyes on the account details. 
 3. View Transactions - He has access to see all the transactions that are taking place in the bank.
 4. Delete Account - This option is given to the employee so that he can delete any account if he finds not appropraite or not following the rules of the bank.
 5. Open Account - Also the employee can open his own account in the bank for accessing the banking services that the bank provides to its customers.
 
 ### **Tools and Technologies used**
 --Java<br>
 --HTML<br>
 --CSS<br>
 --JavaScript<br>
 --BootStrap<br>
 --Servlet<br>
 --JDBC<br>
 --Maven<br>
 --Log4j<br>
 --Junit5<br>
 --DBeaver<br>
 --PostgreSQL<br>
 --Spring Tool Suite<br>
 --VS code<br>
 
