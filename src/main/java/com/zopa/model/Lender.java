package com.zopa.model;

import java.util.Objects;

public class Lender {

    private final String name;
    private final Double rate;
    private final Integer offers;

    public Lender(final String name, final Double rate, final Integer offers) {
        this.name = name;
        this.rate = rate;
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public Integer getOffers() {
        return offers;
    }

    @Override
    public String toString() {
        return "Lender{" +
                "name='" + name + '\'' +
                ", rate=" + rate +
                ", offers=" + offers +
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
                Objects.equals(offers, lender.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rate, offers);
    }
}
