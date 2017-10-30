package com.zopa.model;

import java.util.Objects;

public class Lender {

    private String name;
    private Double rate;
    private Integer available;

    public Lender(final String name, final Double rate, final Integer available) {
        this.name = name;
        this.rate = rate;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Lender lender = (Lender) o;
        return Objects.equals(this.name, lender.name) &&
                Objects.equals(rate, lender.rate) &&
                Objects.equals(available, lender.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, available);
    }
}
