package com.my.project.epam.milena.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class UserOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1480996576043183625L;

    public UserOrder() {
    }

    private int id;
    private Order.Status status;
    private String address;
    private String cardNumber;
    private int amount;
    private BigDecimal price;
    private String productName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return id == userOrder.id && amount == userOrder.amount && status == userOrder.status && Objects.equals(address, userOrder.address) && Objects.equals(cardNumber, userOrder.cardNumber) && Objects.equals(price, userOrder.price) && Objects.equals(productName, userOrder.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, address, cardNumber, amount, price, productName);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", productName='" + productName + '\'' +
                '}';
    }
}
