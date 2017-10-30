package com.zopa.calculator;

import com.zopa.model.Lender;

import java.util.List;

public interface RateCalculator {

    List<Lender> loanCalculator(double loanValue);
}
