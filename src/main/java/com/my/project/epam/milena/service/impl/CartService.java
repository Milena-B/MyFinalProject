package com.my.project.epam.milena.service.impl;


import com.my.project.epam.milena.domain.Product;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartService implements Serializable {

    @Serial
    private static final long serialVersionUID = -1969221632172691995L;

    private final Map<Product, Integer> cart;

    public CartService() {
        cart = new HashMap<>();
    }

    /**
     * Used to add product to cart
     *
     * @param product product
     */

    public void add(Product product) {
        int currentCount = cart.getOrDefault(product, 0);
        cart.put(product, currentCount + 1);
    }

    /**
     * Used to get total sum of products
     *
     * @return bigDecimal totalSum
     */
    public BigDecimal getTotalSum() {
        BigDecimal totalSum = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
            var product = entry.getKey();
            var integer = entry.getValue();
            BigDecimal productPrice = product.getPrice().multiply(new BigDecimal(integer));
            totalSum = totalSum.add(productPrice);
        }
        return totalSum;
    }

    /**
     * Used to get cart
     *
     * @return cart
     */
    public Map<Product, Integer> getCart() {
        return cart;
    }

    /**
     * Used to clear cart
     */
    public void clear() {
        cart.clear();
    }

    /**
     * Used to remove product from cart
     *
     * @param product product
     */
    public void delete(Product product) {
        cart.remove(product);
    }

}
