package main.java.com.elvinmahmudov.service;

import main.java.com.elvinmahmudov.model.Item;

import java.math.BigDecimal;

public interface Checkout {
    void scan(Item item);

    BigDecimal total();
}
