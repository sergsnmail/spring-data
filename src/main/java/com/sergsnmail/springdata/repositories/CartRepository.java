package com.sergsnmail.springdata.repositories;

import com.sergsnmail.springdata.entities.DTO.CartLineDto;
import com.sergsnmail.springdata.entities.Product;
import com.sergsnmail.springdata.exception.cart.CartProductNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component(value = "inMemory")
public class CartRepository {

    Map<Product, Integer> content;

    @PostConstruct
    public void init() {
        content = new HashMap();
    }
    
    public List<CartLineDto> findAll() {
        return content.entrySet().stream().map((e -> new CartLineDto(e.getKey(),e.getValue()))).collect(Collectors.toList());
    }

    public void add(Product product, int count) {
        Integer productCount = content.getOrDefault(product, 0);
        content.put(product, productCount + count);
    }

    public void deleteById(Product product, int count) throws RuntimeException{
        if (!content.containsKey(product)){
            throw new CartProductNotFoundException("Product with id=" + product.getId() + " not found");
        }
        Integer productCount = content.get(product);
        if (count > 0 && count < productCount) {
            content.put(product, productCount - count);
        } else {
            content.remove(product);
        }
    }
}
