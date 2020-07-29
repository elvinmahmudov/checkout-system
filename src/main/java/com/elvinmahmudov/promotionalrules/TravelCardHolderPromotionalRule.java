package main.java.com.elvinmahmudov.promotionalrules;

import main.java.com.elvinmahmudov.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class TravelCardHolderPromotionalRule implements PromotionalRule {

    public static final BigDecimal DEFAULT_PRICE_AFTER_DISCOUNT = new BigDecimal("8.50");
    public static final int DEFAULT_MINIMUM_DISCOUNTABLE_ITEMS_COUNT = 2;

    @Override
    public BigDecimal getPromotion(List<Item> items, BigDecimal lastTotalPrice) {
        List<Item> travelCardHolders = getTravelCardHolders(items);

        if (travelCardHolders.size() >= DEFAULT_MINIMUM_DISCOUNTABLE_ITEMS_COUNT) {
            travelCardHolders.forEach(holder -> holder.setPrice(DEFAULT_PRICE_AFTER_DISCOUNT));
        }

        return items.stream().map(Item::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Item> getTravelCardHolders(List<Item> items) {
        return items.stream()
                    .filter(item -> item.getProductCode().equals("001"))
                    .collect(Collectors.toList());
    }
}
