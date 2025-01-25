package com.codingshuttle.projects.strategy;

import com.codingshuttle.projects.entities.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@RequiredArgsConstructor
public class HolidayBasedPricingStrategy implements PricingStrategy{
    private final PricingStrategy wrappedPricingStrategy;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrappedPricingStrategy.calculatePrice(inventory);
        boolean isTodayAHoliday = true;
        if(isTodayAHoliday){
            price = price.multiply(BigDecimal.valueOf(1.5));
        }
        return price;
    }
}
