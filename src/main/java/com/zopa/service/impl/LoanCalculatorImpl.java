package com.zopa.service.impl;

import com.zopa.builder.LenderBuilder;
import com.zopa.calculator.LoanValueValidator;
import com.zopa.calculator.RepaymentCalculator;
import com.zopa.exceptions.LendersCriteriaException;
import com.zopa.exceptions.LendersFileProcessException;
import com.zopa.model.Lender;
import com.zopa.model.Loan;
import com.zopa.service.LoanCalculator;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class LoanCalculatorImpl implements LoanCalculator {

    private static final int SKIP_HEADERS = 1;
    public static final String SEPARATOR = ",";


    @Override
    public Loan calculate(final String lendersFile, final Integer amount) {

        validateAmount(amount);

        try (final Stream<String> lines = Files.lines(readFileFromResources(lendersFile).toPath()).skip(SKIP_HEADERS)) {

            final Lender lender = lines.map(line -> line.split(SEPARATOR))
                    .map(LenderBuilder::build)
                    .sorted(Comparator.comparing(Lender::getRate))
                    .min(Comparator.comparing(Lender::getRate))
                    .get();

            return RepaymentCalculator.calculateRepayment(amount, lender.getRate());

        } catch (final NoSuchElementException e) {
            throw new LendersCriteriaException("Not Lender found.");
        } catch (final IOException | NullPointerException | URISyntaxException e) {
            throw new LendersFileProcessException(String.format("Error Processing File [%s]", lendersFile), e);
        }
    }


    private void validateAmount(final Integer amount) {
        final boolean validAmount = LoanValueValidator.validateLoanValue(amount);

        if (!validAmount) {
            throw new LendersCriteriaException(String.format("Amount not valid, values are between [%s] and [%s] with increments od [%s].", LoanValueValidator.MIN_LOAN_VALUE, LoanValueValidator.MAX_LOAN_VALUE, LoanValueValidator.DELTA_LOAN_VALUE));
        }
    }


    private File readFileFromResources(final String file) throws URISyntaxException {

        final URI uri = new URI(getClass().getClassLoader().getResource(file).toString());

        return new File(uri);
    }
}
