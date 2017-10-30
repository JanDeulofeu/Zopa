package com.zopa.builder;

import com.zopa.model.Lender;

public class LenderBuilder {

    public static final int PERCENTAGE = 100;

    public static Lender build(final String[] input) {

        return new Lender(input[0], Double.valueOf(input[1]) * PERCENTAGE, Integer.valueOf(input[2]));
    }

}
