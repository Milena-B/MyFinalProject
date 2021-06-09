package com.my.project.epam.milena.domain;

import java.util.Objects;

public class FilterModel {

    private String sort;
    private String volume;
    private String color;
    private String minPrice;
    private String maxPrice;

    public FilterModel(){
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterModel that = (FilterModel) o;
        return Objects.equals(sort, that.sort) && Objects.equals(volume, that.volume) && Objects.equals(color, that.color) && Objects.equals(minPrice, that.minPrice) && Objects.equals(maxPrice, that.maxPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sort, volume, color, minPrice, maxPrice);
    }

    @Override
    public String toString() {
        return "FilterModel{" +
                "sort='" + sort + '\'' +
                ", volume='" + volume + '\'' +
                ", color='" + color + '\'' +
                ", minPrice='" + minPrice + '\'' +
                ", maxPrice='" + maxPrice + '\'' +
                '}';
    }
}
