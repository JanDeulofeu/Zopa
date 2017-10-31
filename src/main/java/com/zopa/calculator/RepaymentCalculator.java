package com.zopa.calculator;

import com.zopa.model.Loan;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RepaymentCalculator {

    public static final double ZOPA_DISCOUNT = 0.11;
    public static final int MONTHS_LOAN = 36;
    public static final int NUMBER_DECIMALS = 2;


    public static Loan calculateRepayment(final Integer amount, final Double rate) {

        final Double repayment = calculateAmortization(amount, rate - ZOPA_DISCOUNT);

        final double annualPayment = repayment * MONTHS_LOAN;

        return new Loan(amount, Math.ceil(rate), roundNumber(repayment), roundNumber(annualPayment));
    }


    /**
     * A=P*( r(1+r)^n / (1+r)^n −1 )
     */
    private static Double calculateAmortization(final Integer amount, final Double interest) {

        final double periodicInterest = interest / 1200;

        return amount * (periodicInterest * Math.pow(1 + periodicInterest, MONTHS_LOAN) / (Math.pow(1 + periodicInterest, MONTHS_LOAN) - 1));
    }


    private static double roundNumber(final double value) {
        return new BigDecimal(Double.toString(value)).setScale(NUMBER_DECIMALS, RoundingMode.HALF_UP).doubleValue();
    }
}
