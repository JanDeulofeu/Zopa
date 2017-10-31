package com.zopa.service.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class LoanValueValidatorTest {


    @ParameterizedTest
    @CsvSource({
            "0, false",
            "100, false",
            "110, false",
            "200, false",
            "201, false",
            "299, false",
            "500, false",
            "900, false",
            "1000, true",
            "1001, false",
            "1010, false",
            "1100, true",
            "1101, false",
            "1200, true",
            "2000, true",
            "3000, true",
            "9000, true",
            "9003, false",
            "9102, false",
            "10000, true",
            "15000, true",
            "15001, false",
            "15001, false",
            "15100, false",
            "150000, false"
    })
    public void validateLoanValueIsInRangeAndHavingIncrementsOf100(final Double loan, final Boolean valid) {
        assertThat(LoanValueValidator.validateLoanValue(loan.intValue())).isEqualTo(valid);
    }

}