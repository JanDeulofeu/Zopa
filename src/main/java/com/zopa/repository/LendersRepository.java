package com.zopa.repository;

import com.zopa.model.Lender;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LendersRepository {

    private static List<Lender> pool;
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();


    public void addLender()
    {

    }


}
