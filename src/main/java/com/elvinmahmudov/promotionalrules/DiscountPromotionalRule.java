package main.java.com.elvinmahmudov.promotionalrules;

import main.java.com.elvinmahmudov.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class DiscountPromotionalRule implements PromotionalRule {

    private static final BigDecimal DEFAULT_MINIMUM_DISCOUNT_AMOUNT = new BigDecimal("60");
    private static final BigDecimal DEFAULT_DISCOUNT_VALUE = new BigDecimal("0.9");

    @Override
    public BigDecimal getPromotion(List<Item> items, BigDecimal lastTotalPrice) {
        BigDecimal totalPrice;

        if (BigDecimal.ZERO.equals(lastTotalPrice)) {
            totalPrice = items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            totalPrice = lastTotalPrice;
        }

        if (totalPrice.compareTo(DEFAULT_MINIMUM_DISCOUNT_AMOUNT) > 0) {
            return totalPrice.multiply(DEFAULT_DISCOUNT_VALUE);
        }

        return totalPrice;
    }
}
