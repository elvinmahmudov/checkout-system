package main.java.com.elvinmahmudov;

import main.java.com.elvinmahmudov.model.Item;
import main.java.com.elvinmahmudov.promotionalrules.DiscountPromotionalRule;
import main.java.com.elvinmahmudov.promotionalrules.TravelCardHolderPromotionalRule;
import main.java.com.elvinmahmudov.service.CheckoutImpl;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        var cardHolder = new Item("001", "Travel Card Holder", new BigDecimal("9.25"));
        var cufflinks = new Item("002", "Personalizsed cufflinks", new BigDecimal("45"));
        var tshirt = new Item("003", "Kids T-shirt", new BigDecimal("19.95"));

        var co = new CheckoutImpl(new TravelCardHolderPromotionalRule(), new DiscountPromotionalRule());

        co.scan(cardHolder);
        co.scan(cufflinks);
        co.scan(tshirt);

        System.out.println(co.total());
    }
}
