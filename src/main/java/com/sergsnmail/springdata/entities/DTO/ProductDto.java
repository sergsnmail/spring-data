package com.sergsnmail.springdata.entities.DTO;

import com.sergsnmail.springdata.entities.Product;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private float cost;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.cost = p.getCost();
    }
}
