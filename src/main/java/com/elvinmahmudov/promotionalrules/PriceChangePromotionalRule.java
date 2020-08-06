package com.elvinmahmudov.promotionalrules;

import java.math.BigDecimal;
import java.util.Set;
import java.util.function.Function;

public class PriceChangePromotionalRule extends PromotionalRule {

    private Set<String> selectedItems;
    private BigDecimal newPrice;

    public PriceChangePromotionalRule(Set<String> selectedItems, Function<Integer[], Boolean> condition, BigDecimal newPrice) {
        this.selectedItems = selectedItems;
        this.condition = condition;
        this.newPrice = newPrice;
    }

    public Set<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(Set<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }
}
