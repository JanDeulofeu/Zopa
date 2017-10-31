package com.zopa.main;

import com.zopa.model.Loan;
import com.zopa.service.LoanCalculator;
import com.zopa.service.impl.LoanCalculatorImpl;

public class QuoteApp {

    private static final LoanCalculator loanCalculator = new LoanCalculatorImpl();

    public static void main(final String[] args) {

        if (isValidInput(args)) {

            final Loan loanCalculation = loanCalculator.calculate(args[0], Integer.valueOf(args[1]));

            System.out.println("Requested amount: £" + loanCalculation.getAmount());
            System.out.println("Rate: " + loanCalculation.getRate() + "%");
            System.out.println("Monthly repayment:  £" + loanCalculation.getRepayment());
            System.out.println("Total repayment:  £" + loanCalculation.getTotalRepayment());

        }
    }


    private static boolean isValidInput(final String[] args) {

        if (args[0] == null || args[0] == "") {
            System.out.println("Error: Field CSV file is empty.");
            return false;
        } else if (args[1] == null || args[1] == "") {
            System.out.println("Error: Loan value is empty.");
            return false;
        }

        return true;
    }
}
