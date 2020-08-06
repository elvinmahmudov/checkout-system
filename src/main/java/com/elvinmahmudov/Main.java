package com.elvinmahmudov;

import com.elvinmahmudov.model.Item;
import com.elvinmahmudov.promotionalrules.PriceChangePromotionalRule;
import com.elvinmahmudov.promotionalrules.TotalChangePromotionalRule;
import com.elvinmahmudov.service.CheckoutImpl;

import java.math.BigDecimal;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        var cardHolder = new Item("001", "Travel Card Holder", new BigDecimal("9.25"));
        var cufflinks = new Item("002", "Personalizsed cufflinks", new BigDecimal("45"));
        var tshirt = new Item("003", "Kids T-shirt", new BigDecimal("19.95"));

        var priceChangePromotionalRule = new PriceChangePromotionalRule(Set.of("001"),
                (itemsCount, totalPrice) -> itemsCount >= 2, new BigDecimal("3"));
        var totalChangePromotionalRule = new TotalChangePromotionalRule((itemsCount, totalPrice) -> totalPrice.compareTo(new BigDecimal("60")) >= 0, total -> total.multiply(new BigDecimal("0.9")));

        var co = new CheckoutImpl(totalChangePromotionalRule, priceChangePromotionalRule);

        co.scan(cardHolder);
        co.scan(cufflinks);
        co.scan(tshirt);

        System.out.println(co.total());
    }
}
