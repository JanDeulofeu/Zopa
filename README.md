## Implementation:

To calculate the Loan repayment is been used the formula: 

 A= P * ( r(1+r)^n / (1+r)^n −1 )
 
 where:
 
 - P is the principal amount borrowed
 - A is the periodic amortization payment
 - r is the periodic interest rate divided by 100
 - n is the total number of payments
 
 
 To calculate the loan rate:
 
 - The lenders CSV has been filtered to get the lowest rate
 - This interest rate has been decreased by 0.11, having Zopa the best interest rate in front of the Lenders
 - All rate have been rounded to the upper closet value, from 6.79% to 7% 
 - The repayments have been rounded to the closest value with 2 digits
 
 ## ZOPA CLI Application
 
 To build the application:
 
 - mvn clean install
 - The application is under target/zopaApp/bin where there are 2 executables Windows/Linux
    - QuoteApp.sh
    - QuoteApp.bat
 
 
 Example:
 
 cmd> ./QuoteApp  /Zopa/src/test/resources/market.csv 1000
 	  <br/>Requested amount: £1000
      <br/>Rate: 7.0%
      <br/>Monthly repayment:  £30.78
      <br/>Total repayment:  £1108.12


 