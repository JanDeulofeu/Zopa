package com.zopa.service.loan;

import com.zopa.exceptions.LendersCriteriaException;
import com.zopa.exceptions.LendersFileProcessException;
import com.zopa.model.Lender;
import com.zopa.model.Loan;
import com.zopa.model.builder.LenderBuilder;
import com.zopa.service.calculator.LoanValueValidator;
import com.zopa.service.calculator.RepaymentCalculator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LoanServiceImpl implements LoanService {

    private static final int SKIP_HEADERS = 1;
    public static final String SEPARATOR = ",";


    @Override
    public Loan calculate(final String lendersFile, final Integer amount) {

        validateAmount(amount);

        try (final Stream<String> lines = Files.lines(new File(lendersFile).toPath()).skip(SKIP_HEADERS)) {

            final Lender lender = lines.map(line -> line.split(SEPARATOR))
                    .map(LenderBuilder::build)
                    .sorted(Comparator.comparing(Lender::getRate))
                    .min(Comparator.comparing(Lender::getRate))
                    .get();

            return RepaymentCalculator.calculateRepayment(amount, lender.getRate());

        } catch (final NoSuchElementException e) {
            throw new LendersCriteriaException("Not Lender found.");
        } catch (final IOException | NullPointerException e) {
            throw new LendersFileProcessException(String.format("Error Processing File [%s]", lendersFile), e);
        }
    }


    private void validateAmount(final Integer amount) {

        final boolean validAmount = LoanValueValidator.validateLoanValue(amount);

        if (!validAmount) {

            throw new LendersCriteriaException(String.format("Amount not valid, values should be between [%s] and [%s] with increments of [%s].",
                    LoanValueValidator.MIN_LOAN_VALUE,
                    LoanValueValidator.MAX_LOAN_VALUE,
                    LoanValueValidator.DELTA_LOAN_VALUE));
        }
    }
}
