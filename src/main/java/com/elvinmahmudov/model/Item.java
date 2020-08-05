package main.java.com.elvinmahmudov.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String productCode;

    private String name;

    private BigDecimal price;

    private Boolean isPromoted = false;

    public Item(String productCode, String name, BigDecimal price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsPromoted() {
        return isPromoted;
    }

    public void setIsPromoted(Boolean isPromoted) {
        this.isPromoted = isPromoted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(productCode, item.productCode) &&
                Objects.equals(name, item.name) &&
                Objects.equals(price, item.price) &&
                Objects.equals(isPromoted, item.isPromoted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, name, price, isPromoted);
    }
}
