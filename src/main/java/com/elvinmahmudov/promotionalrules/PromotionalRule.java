package main.java.com.elvinmahmudov.promotionalrules;

import main.java.com.elvinmahmudov.model.Item;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionalRule {
    BigDecimal getPromotion(List<Item> items, BigDecimal lastTotalPrice);
}
