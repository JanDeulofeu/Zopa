package com.zopa.main;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class QuoteAppTest {

    private PrintStream out;

    @BeforeEach
    public void init() {
        out = mock(PrintStream.class);
        System.setOut(out);
    }


    @Test
    public void validateCalculationIsValid() {


        QuoteApp.main(new String[]{Thread.currentThread().getContextClassLoader().getResource("market.csv").getFile(), "1000"});

        verify(out).println(contains("Requested amount: £1000"));
        verify(out).println(contains("Rate: 7.0%"));
        verify(out).println(contains("Monthly repayment:  £30.78"));
        verify(out).println(contains("Total repayment:  £1108.12"));
    }


    @Test
    public void validateThrowsErrorWhenFileNotFound() {

        QuoteApp.main(new String[]{"ghost.csv", "1000"});
        verify(out).println(contains("Error Processing Loan: Error Processing File [ghost.csv]"));
    }

    @Test
    public void validateThrowsErrorWhenLoanValueNotInRange() {

        QuoteApp.main(new String[]{"ghost.csv", "100"});
        verify(out).println(contains("Error Processing Loan: Amount not valid, values should be between [1000] and [15000] with increments of [100]."));
    }

    @Test
    public void validateErrorMessageWhenFileIsEmpty() {

        QuoteApp.main(new String[]{"", "1000"});
        verify(out).println(contains("Error: Field CSV file is empty."));
    }

    @Test
    public void validateErrorMessageWhenFileIsNull() {

        QuoteApp.main(new String[]{"", "1000"});
        verify(out).println(contains("Error: Field CSV file is empty."));
    }

    @Test
    public void validateErrorMessageWhenLoanAmountIsEmpty() {

        QuoteApp.main(new String[]{"market.csv", ""});
        verify(out).println(contains("Error: Loan value is empty."));
    }

    @Test
    public void validateErrorMessageWhenLoanAmountIsNull() {

        QuoteApp.main(new String[]{"market.csv", null});
        verify(out).println(contains("Error: Loan value is empty."));
    }
}