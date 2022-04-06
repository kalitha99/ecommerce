package com.ecommerce.sample.dto;

import com.ecommerce.sample.model.Cart;
import com.ecommerce.sample.model.Product;

public class CartitemDto {
    private int id;
    private int quantity;
    private Product product;

    public CartitemDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CartitemDto(Cart cart) {
        this.id = cart.getcId();
        this.quantity=cart.getQuantity();
        this.setProduct(cart.getProduct());
    }
}
