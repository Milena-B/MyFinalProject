package com.my.project.epam.milena.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderedProduct implements Serializable {

    @Serial
    private static final long serialVersionUID = 8272712788449024386L;

    public OrderedProduct(int productId, int amount, BigDecimal price) {
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }

    private int orderId;
    private int productId;
    private int amount;
    private BigDecimal price;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedProduct that = (OrderedProduct) o;
        return orderId == that.orderId && productId == that.productId && amount == that.amount && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, amount, price);
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}