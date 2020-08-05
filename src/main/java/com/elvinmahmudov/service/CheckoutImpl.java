package main.java.com.elvinmahmudov.service;

import main.java.com.elvinmahmudov.model.Item;
import main.java.com.elvinmahmudov.promotionalrules.PromotionalRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutImpl implements Checkout {

    private final List<PromotionalRule> promotionalRules;
    private final List<Item> items;

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
        return new PromotionComputer(promotionalRules).getTotal(items);
    }
}
