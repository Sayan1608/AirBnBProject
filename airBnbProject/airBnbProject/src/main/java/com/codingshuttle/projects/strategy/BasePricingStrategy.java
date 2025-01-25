package com.codingshuttle.projects.strategy;

import com.codingshuttle.projects.entities.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@RequiredArgsConstructor
public class BasePricingStrategy implements PricingStrategy{
    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
