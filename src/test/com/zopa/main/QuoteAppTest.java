package com.zopa.main;

import org.junit.jupiter.api.Test;

import java.io.PrintStream;

class QuoteAppTest {

    private String g;

    @Test
    public void validateCalculationIsValid() {

        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        QuoteApp.main(new String[]{"market.csv", "1000"});
    }


}