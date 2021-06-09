package com.my.project.epam.milena.domain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ProductManufacturer implements Serializable {

    @Serial
    private static final long serialVersionUID = 8996517751658165054L;

    int id;
    String name;

    public ProductManufacturer() {

    }

    public ProductManufacturer(String name) {
        this.name = name;
    }

    public ProductManufacturer(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductManufacturer that = (ProductManufacturer) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ProductManufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
