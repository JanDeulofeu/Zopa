package com.zopa.service;

import com.zopa.model.Lender;

import java.io.File;
import java.util.List;

public interface FileReader {

    List<Lender> readLenders(File file);
}
