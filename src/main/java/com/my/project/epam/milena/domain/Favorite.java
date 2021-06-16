package com.my.project.epam.milena.domain;

import java.util.Objects;

public class Favorite {
    int userId;
    int productId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorites = (Favorite) o;
        return userId == favorites.userId && productId == favorites.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, productId);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
