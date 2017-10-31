package com.zopa.service;


import com.zopa.exceptions.LendersCriteriaException;
import com.zopa.exceptions.LendersFileProcessException;
import com.zopa.model.Loan;
import com.zopa.service.loan.LoanService;
import com.zopa.service.loan.LoanServiceImpl;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LoanServiceTest {

    private final LoanService serviceCalculator = new LoanServiceImpl();


    @Test
    public void validateServiceCalculatorGetsTheBestLoanFromCSVFile() throws URISyntaxException {

        final Loan result = serviceCalculator.calculate(getFileResource("market.csv"), 1000);

        assertThat(result.getAmount()).isEqualTo(1000);
        assertThat(result.getRate()).isEqualTo(7.0);
        assertThat(result.getRepayment()).isEqualTo(30.78);
        assertThat(result.getTotalRepayment()).isEqualTo(1108.12);
    }


    @Test
    public void validateServiceCalculatorThrowsExceptionIfLenderNotFound() throws URISyntaxException {

        assertThatThrownBy(() -> serviceCalculator.calculate(getFileResource("emptymarket.csv"), 1000))
                .isInstanceOf(LendersCriteriaException.class)
                .hasMessage("Not Lender found.");
    }

    @Test
    public void validateServiceCalculatorThrowsExceptionIfFileNotFound() throws URISyntaxException {

        final String file = "ghost.csv";

        assertThatThrownBy(() -> serviceCalculator.calculate(file, 1000))
                .isInstanceOf(LendersFileProcessException.class)
                .hasMessage(String.format("Error Processing File [%s]", file));
    }

    private String getFileResource(final String fileName) {
        return Thread.currentThread().getContextClassLoader().getResource(fileName).getFile();
    }
}