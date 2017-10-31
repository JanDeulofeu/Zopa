package com.zopa.service.calculator;

public class LoanValueValidator {

    public static final int DELTA_LOAN_VALUE = 100;
    public static final int MIN_LOAN_VALUE = 1000;
    public static final int MAX_LOAN_VALUE = 15000;


    public static boolean validateLoanValue(final Integer loanValue) {

        return isInRange(loanValue) && loanValue % DELTA_LOAN_VALUE == 0;

    }

    private static boolean isInRange(final Integer loanValue) {
        return loanValue >= MIN_LOAN_VALUE && loanValue <= MAX_LOAN_VALUE;
    }
}
