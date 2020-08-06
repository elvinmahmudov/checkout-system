package com.elvinmahmudov.promotionalrules;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public abstract class PromotionalRule {
    protected BiFunction<Integer, BigDecimal, Boolean> condition;

    public BiFunction<Integer, BigDecimal, Boolean> getCondition() {
        return condition;
    }

    public void setCondition(BiFunction<Integer, BigDecimal, Boolean> condition) {
        this.condition = condition;
    }
}
