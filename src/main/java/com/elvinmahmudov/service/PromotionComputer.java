package com.elvinmahmudov.service;

import com.elvinmahmudov.model.Item;
import com.elvinmahmudov.promotionalrules.PriceChangePromotionalRule;
import com.elvinmahmudov.promotionalrules.PromotionalRule;
import com.elvinmahmudov.promotionalrules.TotalChangePromotionalRule;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionComputer {

    private List<PriceChangePromotionalRule> onPriceRules = new ArrayList<>();

    private List<TotalChangePromotionalRule> onTotalRules = new ArrayList<>();

    public PromotionComputer(List<PromotionalRule> rules) {
        rules.forEach(rule -> {
            if (rule instanceof TotalChangePromotionalRule) {
                onTotalRules.add((TotalChangePromotionalRule) rule);
            } else if (rule instanceof PriceChangePromotionalRule) {
                onPriceRules.add((PriceChangePromotionalRule) rule);
            } else {
                throw new RuntimeException("Unknown rule " + rule.getClass());
            }
        });
    }

    public BigDecimal getTotal(List<Item> checkedItems) {
        applyPriceRules(checkedItems);

        return applyTotalChangeRules(checkedItems);
    }

    private BigDecimal applyTotalChangeRules(List<Item> checkedItems) {
        var total = getTotalPrice(checkedItems);

        for (TotalChangePromotionalRule rule : onTotalRules) {
            if (rule.getCondition().apply(checkedItems.size(), total)) {
                total = rule.getTotalChange().apply(total);
            }
        }

        return total.setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getTotalPrice(List<Item> checkedItems) {
        return checkedItems.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private void applyPriceRules(List<Item> checkedItems) {
        onPriceRules.forEach(rule -> {
            var itemsToPromote = checkedItems.stream()
                    .filter(item -> !item.getIsPromoted())
                    .filter(item -> rule.getSelectedItems().contains(item.getProductCode()))
                    .collect(Collectors.toList());

            if (rule.getCondition().apply(itemsToPromote.size(), getTotalPrice(checkedItems))) {
                itemsToPromote.forEach(item -> {
                    item.setPrice(rule.getNewPrice());
                    item.setIsPromoted(true);
                });
            }
        });
    }
}
