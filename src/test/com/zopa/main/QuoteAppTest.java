package com.zopa.main;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class QuoteAppTest {

    private String g;

    @Test
    public void validateCalculationIsValid() {

        final ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        bo.flush();

        QuoteApp.main(new String[]{"market.csv", "1000"});
    }


}