package com.codingshuttle.projects.strategy;

import com.codingshuttle.projects.entities.Inventory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {
    public BigDecimal calculateDynamicPricing(Inventory inventory){
        PricingStrategy pricingStrategy = new BasePricingStrategy();
        pricingStrategy = new SurgePricingStrategy(pricingStrategy);
        pricingStrategy = new OccupancyBasedPricingStrategy(pricingStrategy);
        pricingStrategy = new UrgencyPricingStrategy(pricingStrategy);
        pricingStrategy = new HolidayBasedPricingStrategy(pricingStrategy);

        return pricingStrategy.calculatePrice(inventory);

    }

}
