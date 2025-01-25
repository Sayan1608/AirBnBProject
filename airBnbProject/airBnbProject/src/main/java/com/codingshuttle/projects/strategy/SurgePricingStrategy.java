package com.codingshuttle.projects.strategy;

import com.codingshuttle.projects.entities.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy{
    private final PricingStrategy wrappedPricingStrategy;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrappedPricingStrategy.calculatePrice(inventory);
        return price.multiply(inventory.getSurgeFactor());
    }
}
