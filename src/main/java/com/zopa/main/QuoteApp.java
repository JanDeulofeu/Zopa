package com.zopa.main;

import com.zopa.model.Loan;
import com.zopa.service.loan.LoanService;
import com.zopa.service.loan.LoanServiceImpl;

public class QuoteApp {

    private static final LoanService loanCalculator = new LoanServiceImpl();

    public static void main(final String[] args) {

        if (isValidInput(args)) {

            try {
                final Loan loanCalculation = loanCalculator.calculate(args[0], Integer.valueOf(args[1]));

                System.out.println("Requested amount: £" + loanCalculation.getAmount());
                System.out.println("Rate: " + loanCalculation.getRate() + "%");
                System.out.println("Monthly repayment:  £" + loanCalculation.getRepayment());
                System.out.println("Total repayment:  £" + loanCalculation.getTotalRepayment());

            } catch (final RuntimeException e) {
                System.out.println("Error Processing Loan: " + e.getMessage());
            }
        }
    }


    private static boolean isValidInput(final String[] args) {

        if (args[0] == null || "".equals(args[0])) {
            System.out.println("Error: Field CSV file is empty.");
            return false;
        } else if (args[1] == null || "".equals(args[1])) {
            System.out.println("Error: Loan value is empty.");
            return false;
        }

        return true;
    }
}
