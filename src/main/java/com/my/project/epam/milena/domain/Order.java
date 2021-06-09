package com.my.project.epam.milena.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 4027416755640198094L;
    private int id;
    private Status status;
    private String address;
    private String cardNumber;
    private int userId;

    public Order() {

    }

    public Order(Status status, String address, String cardNumber, int userId) {
        this.status = status;
        this.address = address;
        this.cardNumber = cardNumber;
        this.userId = userId;
    }

    public Order(String address, String cardNumber) {
        this.address = address;
        this.cardNumber = cardNumber;
    }

    public enum Status {
        REGISTERED,
        PAID,
        CANCELED
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && userId == order.userId && status == order.status && Objects.equals(address, order.address) && Objects.equals(cardNumber, order.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, address, cardNumber, userId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", userId=" + userId +
                '}';
    }
}
