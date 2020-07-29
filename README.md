# Checkout system with promotional rules

## Getting started

```$xslt
    var cardHolder = new Item("001", "Travel Card Holder", new BigDecimal("9.25"));
    var cufflinks = new Item("002", "Personalizsed cufflinks", new BigDecimal("45"));
    var tshirt = new Item("003", "Kids T-shirt", new BigDecimal("19.95"));

    var co = new CheckoutImpl(new TravelCardHolderPromotionalRule(), new DiscountPromotionalRule());

    co.scan(cardHolder);
    co.scan(cufflinks);
    co.scan(tshirt);

    System.out.println(co.total()); //OUTPUT: 66.780
```

## Tests

Available unit tests are in src/main/test directory

