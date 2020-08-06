package com.elvinmahmudov.service;

import com.elvinmahmudov.model.Item;

import java.math.BigDecimal;

public interface Checkout {
    void scan(Item item);

    BigDecimal total();
}
