package main.java.com.elvinmahmudov.promotionalrules;

import java.util.function.Function;

public abstract class PromotionalRule {
    protected Function<Integer[], Boolean> condition;

    public Function<Integer[], Boolean> getCondition() {
        return condition;
    }

    public void setCondition(Function<Integer[], Boolean> condition) {
        this.condition = condition;
    }
}
