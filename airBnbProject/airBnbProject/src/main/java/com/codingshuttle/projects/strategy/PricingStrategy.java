package com.codingshuttle.projects.strategy;

import com.codingshuttle.projects.entities.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
