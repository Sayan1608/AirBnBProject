package com.codingshuttle.projects.strategy;

import com.codingshuttle.projects.entities.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
public class UrgencyPricingStrategy implements PricingStrategy{
    private final PricingStrategy wrappedPricingStrategy;
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrappedPricingStrategy.calculatePrice(inventory);
        LocalDate today = LocalDate.now();
        if(!inventory.getDate().isBefore(today)
                && inventory.getDate().isBefore(today.plusDays(7))){
            price = price.multiply(BigDecimal.valueOf(1.5));
        }
        return price;
    }
}
