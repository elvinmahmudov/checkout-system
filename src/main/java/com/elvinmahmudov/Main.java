package main.java.com.elvinmahmudov;

import main.java.com.elvinmahmudov.model.Item;
import main.java.com.elvinmahmudov.promotionalrules.PriceChangePromotionalRule;
import main.java.com.elvinmahmudov.promotionalrules.TotalChangePromotionalRule;
import main.java.com.elvinmahmudov.service.CheckoutImpl;

import java.math.BigDecimal;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        var cardHolder = new Item("001", "Travel Card Holder", new BigDecimal("9.25"));
        var cufflinks = new Item("002", "Personalizsed cufflinks", new BigDecimal("45"));
        var tshirt = new Item("003", "Kids T-shirt", new BigDecimal("19.95"));

        var priceChangePromotionalRule = new PriceChangePromotionalRule(Set.of("001"),
                (params) -> params[0] >= 2, new BigDecimal("3"));
        var totalChangePromotionalRule = new TotalChangePromotionalRule(params -> params[1] >= 60, total -> total.multiply(new BigDecimal("0.9")));

        var co = new CheckoutImpl(totalChangePromotionalRule, priceChangePromotionalRule);

        co.scan(cardHolder);
        co.scan(cufflinks);
        co.scan(tshirt);

        System.out.println(co.total());
    }
}
