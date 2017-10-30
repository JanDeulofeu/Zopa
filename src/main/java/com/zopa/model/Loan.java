package com.zopa.model;

public class Loan {

    private Integer amount;
    private Double rate;
    private Double repayment;
    private Double totalRepayment;

    public Loan(final Integer amount, final Double rate, final Double repayment, final Double totalRepayment) {
        this.amount = amount;
        this.rate = rate;
        this.repayment = repayment;
        this.totalRepayment = totalRepayment;
    }

    public Integer getAmount() {
        return amount;
    }

    public Double getRate() {
        return rate;
    }

    public Double getRepayment() {
        return repayment;
    }

    public Double getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", rate=" + rate +
                ", repayment=" + repayment +
                ", totalRepayment=" + totalRepayment +
                '}';
    }
}
