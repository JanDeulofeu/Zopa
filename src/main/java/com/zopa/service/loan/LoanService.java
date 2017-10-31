package com.zopa.service.loan;

import com.zopa.model.Loan;

public interface LoanService {

    Loan calculate(String lendersFile, Integer amount);
}
