package com.zopa.service;

import com.zopa.model.Lender;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderTest {

    private FileReader fileReader;

    @ParameterizedTest
    @CsvFileSource(resources = {"/resources/Lenders.csv"})
    public void validateCSVIsTransformedToLenders(final String lender, final String rate, final String available) {

        final List<Lender> lenders = fileReader.readLenders(null);
        assertThat(lenders).isNotNull();
        assertThat(lenders).isNotEmpty();
    }

}