package com.elvinmahmudov.service

import com.elvinmahmudov.model.Item
import com.elvinmahmudov.promotionalrules.PriceChangePromotionalRule
import com.elvinmahmudov.promotionalrules.TotalChangePromotionalRule
import spock.lang.Specification

class CheckoutServiceSpec extends Specification {
    private Map<String, Item> testBasketMap = new HashMap<>()
    private PriceChangePromotionalRule priceChangeRule
    private TotalChangePromotionalRule totalChangeRule

    def createTestBasketMap() {
        testBasketMap.put("001", new Item("001", "Travel Card Holder", new BigDecimal("9.25")))
        testBasketMap.put("002", new Item("002", "Personalizsed cufflinks", new BigDecimal("45")))
        testBasketMap.put("003", new Item("003", "Kids T-shirt", new BigDecimal("19.95")))
    }

    def createPromotionalRules() {
        priceChangeRule = new PriceChangePromotionalRule(Set.of("001"),
                (itemsCount, totalPrice) -> itemsCount >= 2, new BigDecimal("8.5"))
        totalChangeRule = new TotalChangePromotionalRule((itemsCount, totalPrice) -> totalPrice >= new BigDecimal("60"), (total) -> total.multiply(new BigDecimal("0.9")))
    }

    def setup() {
        createTestBasketMap()
        createPromotionalRules()
    }

    def "apply no promotions"() {
        given:
        def checkoutService = new CheckoutImpl(priceChangeRule, totalChangeRule)

        checkoutService.scan(testBasketMap.get("001"))
        checkoutService.scan(testBasketMap.get("002"))
        checkoutService.scan(testBasketMap.get("002"))
        checkoutService.scan(testBasketMap.get("003"))

        when:
        def actual = checkoutService.total()

        then:
        new BigDecimal("107.28") == actual
    }

    def "empty basket"() {
        given:
        def checkoutService = new CheckoutImpl(priceChangeRule, totalChangeRule)
        when:
        def actual = checkoutService.total()

        then:
        new BigDecimal("0.00") == actual
    }

    def "apply discount if 2 promotional items selected"() {
        given:
        def checkoutService = new CheckoutImpl(priceChangeRule)

        checkoutService.scan(testBasketMap.get("001"))
        checkoutService.scan(testBasketMap.get("001"))

        when:
        def actual = checkoutService.total()

        then:
        new BigDecimal("17.00") == actual
    }

    def "apply discount if total price is above 60"() {
        given:
        def checkoutService = new CheckoutImpl(totalChangeRule)

        checkoutService.scan(testBasketMap.get("001"))
        checkoutService.scan(testBasketMap.get("002"))
        checkoutService.scan(testBasketMap.get("003"))

        when:
        def actual = checkoutService.total()

        then:
        new BigDecimal("66.78") == actual
    }

    def "apply more than 1 discount"() {
        given:
        def checkoutService = new CheckoutImpl(priceChangeRule, totalChangeRule)

        checkoutService.scan(testBasketMap.get("001"))
        checkoutService.scan(testBasketMap.get("001"))
        checkoutService.scan(testBasketMap.get("002"))
        checkoutService.scan(testBasketMap.get("003"))

        when:
        def actual = checkoutService.total()

        then:
        new BigDecimal("73.76") == actual
    }
}
