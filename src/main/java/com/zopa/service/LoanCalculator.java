package com.zopa.service;

import com.zopa.model.Loan;

public interface LoanCalculator {

    Loan calculate(String lendersFile, Integer amount);
}
