package com.sergsnmail.springdata.entities.DTO;

import com.sergsnmail.springdata.entities.Product;
import lombok.Data;

@Data
public class CartLineDto {
    private Long id;
    private String title;
    private float cost;
    private int count;
    private float subtotal;

    public CartLineDto(Product p, int count) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.cost = p.getCost();
        this.count = count;
        this.subtotal = p.getCost() * count;
    }

}
