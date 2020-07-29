package main.test.com.elvinmahmudov.service;

import main.java.com.elvinmahmudov.service.CheckoutImpl;
import main.java.com.elvinmahmudov.promotionalrules.DiscountPromotionalRule;
import main.java.com.elvinmahmudov.model.Item;
import main.java.com.elvinmahmudov.promotionalrules.TravelCardHolderPromotionalRule;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CheckoutTest {

    private static Map<String, Item> testBasketMap = new HashMap<>();

    private static void createTestBasket() {
        testBasketMap.put("001", new Item("001", "Travel Card Holder", new BigDecimal("9.25")));
        testBasketMap.put("002", new Item("002", "Personalizsed cufflinks", new BigDecimal("45")));
        testBasketMap.put("003", new Item("003", "Kids T-shirt", new BigDecimal("19.95")));
    }

    public static void main(String[] args) {
        createTestBasket();

        testDiscountPromotionalRule();
        testTravelCardHolderPromotionalRule();
        testTravelCardHolderAndDiscountPromotionalRules();
    }

    private static void testDiscountPromotionalRule() {
        var co = new CheckoutImpl(new TravelCardHolderPromotionalRule(), new DiscountPromotionalRule());

        co.scan(testBasketMap.get("001"));
        co.scan(testBasketMap.get("002"));
        co.scan(testBasketMap.get("003"));

        assertTrue(new BigDecimal("66.78"), co.total());
    }

    private static void testTravelCardHolderPromotionalRule() {
        var co = new CheckoutImpl(new TravelCardHolderPromotionalRule(), new DiscountPromotionalRule());

        co.scan(testBasketMap.get("001"));
        co.scan(testBasketMap.get("003"));
        co.scan(testBasketMap.get("001"));

        assertTrue(new BigDecimal("36.95"), co.total());
    }

    private static void testTravelCardHolderAndDiscountPromotionalRules() {
        var co = new CheckoutImpl(new TravelCardHolderPromotionalRule(), new DiscountPromotionalRule());

        co.scan(testBasketMap.get("001"));
        co.scan(testBasketMap.get("002"));
        co.scan(testBasketMap.get("001"));
        co.scan(testBasketMap.get("003"));

        assertTrue(new BigDecimal("73.755"), co.total());
    }

    private static void assertTrue(BigDecimal expectedValue, BigDecimal actualValue) {
        if (expectedValue.compareTo(actualValue) != 0) {
            throw new RuntimeException("ExpectedValue " + expectedValue + " does not match with actual value " + actualValue);
        }
    }
}
