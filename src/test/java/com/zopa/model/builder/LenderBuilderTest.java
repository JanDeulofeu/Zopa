package com.zopa.model.builder;

import com.zopa.model.Lender;
import com.zopa.service.loan.LoanServiceImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LenderBuilderTest {

    @ParameterizedTest
    @MethodSource("generateTestValues")
    public void validateChangeUsingOptimalChangeForService(final String chain, final Lender expected) {

        assertThat(LenderBuilder.build(chain.split(LoanServiceImpl.SEPARATOR))).isEqualTo(expected);
    }

    private static Stream<Arguments> generateTestValues() {

        return Stream.of(
                Arguments.of("A,0.075,640", new Lender("A", 7.5d, 640)),
                Arguments.of("B,0.11,40", new Lender("B", 11d, 40)),
                Arguments.of("C,0.7,40", new Lender("C", 70d, 40))
        );
    }
}