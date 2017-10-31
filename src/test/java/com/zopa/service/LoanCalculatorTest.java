package com.zopa.service;


import com.zopa.exceptions.LendersCriteriaException;
import com.zopa.exceptions.LendersFileProcessException;
import com.zopa.model.Loan;
import com.zopa.service.impl.LoanCalculatorImpl;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LoanCalculatorTest {

    private final LoanCalculator loanCalculator = new LoanCalculatorImpl();


    @Test
    public void validateLoanCalculatorGetsTheBestLoanFromCSVFile() throws URISyntaxException {

        final Loan result = loanCalculator.calculate(getFileResource("market.csv"), 1000);

        assertThat(result.getAmount()).isEqualTo(1000);
        assertThat(result.getRate()).isEqualTo(7.0);
        assertThat(result.getRepayment()).isEqualTo(30.78);
        assertThat(result.getTotalRepayment()).isEqualTo(1108.12);
    }


    @Test
    public void validateLoanCalculatorThrowsExceptionIfLenderNotFound() throws URISyntaxException {

        assertThatThrownBy(() -> loanCalculator.calculate(getFileResource("emptymarket.csv"), 1000))
                .isInstanceOf(LendersCriteriaException.class)
                .hasMessage("Not Lender found.");
    }


    @Test
    public void validateLoanCalculatorThrowsExceptionIfFileNotFound() throws URISyntaxException {

        final String file = "ghost.csv";

        assertThatThrownBy(() -> loanCalculator.calculate(file, 1000))
                .isInstanceOf(LendersFileProcessException.class)
                .hasMessage(String.format("Error Processing File [%s]", file));
    }


    private String getFileResource(final String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
    }
}