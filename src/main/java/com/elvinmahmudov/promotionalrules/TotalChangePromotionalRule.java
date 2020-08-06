package com.elvinmahmudov.promotionalrules;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TotalChangePromotionalRule extends PromotionalRule {

    private Function<BigDecimal, BigDecimal> totalChange;

    public TotalChangePromotionalRule(BiFunction<Integer, BigDecimal, Boolean> condition, Function<BigDecimal, BigDecimal> totalChange) {
        this.condition = condition;
        this.totalChange = totalChange;
    }

    public Function<BigDecimal, BigDecimal> getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(Function<BigDecimal, BigDecimal> totalChange) {
        this.totalChange = totalChange;
    }
}
