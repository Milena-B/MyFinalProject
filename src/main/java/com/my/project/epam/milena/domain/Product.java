package com.my.project.epam.milena.domain;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 3898175517109372095L;
    private int id;
    private String name;
    private int volume;
    private String color;
    private BigDecimal price;
    private ProductManufacturer productManufacturer;
    private LocalDateTime localDateTime;

    public Product() {

    }

    public Product(int id, String name, int volume, String color, BigDecimal price, ProductManufacturer productManufacturer, LocalDateTime localDateTime) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.color = color;
        this.price = price;
        this.productManufacturer = productManufacturer;
        this.localDateTime = localDateTime;
    }

    public Product(String name, int volume, String color, BigDecimal price, ProductManufacturer productManufacturer, LocalDateTime localDateTime) {
        this.name = name;
        this.volume = volume;
        this.color = color;
        this.price = price;
        this.productManufacturer = productManufacturer;
        this.localDateTime = localDateTime;
    }


    public ProductManufacturer getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(ProductManufacturer productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var product = (Product) o;
        return id == product.id && volume == product.volume && Objects.equals(name, product.name) && Objects.equals(color, product.color) && Objects.equals(price, product.price) && Objects.equals(productManufacturer, product.productManufacturer) && Objects.equals(localDateTime, product.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, volume, color, price, productManufacturer, localDateTime);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", volume=" + volume +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", productManufacturer='" + productManufacturer + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}