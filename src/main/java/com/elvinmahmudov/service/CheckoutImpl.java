package main.java.com.elvinmahmudov.service;

import main.java.com.elvinmahmudov.model.Item;
import main.java.com.elvinmahmudov.promotionalrules.PromotionalRule;
import main.java.com.elvinmahmudov.service.Checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutImpl implements Checkout {

    List<PromotionalRule> promotionalRules;
    List<Item> items;

    public CheckoutImpl(PromotionalRule... promotionalRules) {
        this.promotionalRules = Arrays.asList(promotionalRules);
        this.items = new ArrayList<>();
    }

    @Override
    public void scan(Item item) {
        items.add(item);
    }

    @Override
    public BigDecimal total() {
        var lastTotalPrice = BigDecimal.ZERO;
        for (PromotionalRule rule : promotionalRules) {
            lastTotalPrice = rule.getPromotion(items, lastTotalPrice);
        }
        return lastTotalPrice;
    }
}
