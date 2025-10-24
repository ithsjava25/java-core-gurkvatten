package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyMonths;
    private final BigDecimal weight;

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }
        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }
    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal baseCost = new BigDecimal("79");
        BigDecimal heavyWeightSurcharge = BigDecimal.ZERO;

        if (weight.compareTo(new BigDecimal("5.0")) > 0) {
            heavyWeightSurcharge = new BigDecimal("49");
        }
        return baseCost.add(heavyWeightSurcharge).setScale(2, RoundingMode.HALF_UP);

    }
    @Override
    public double weight() {
        return weight.doubleValue();
    }
    @Override
    public String productDetails() {
        return String.format("Electronics: %s, Warranty: %d months", name(), warrantyMonths);
    }

}
