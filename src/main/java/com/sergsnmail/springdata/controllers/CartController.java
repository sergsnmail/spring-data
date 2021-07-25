package com.sergsnmail.springdata.controllers;

import com.sergsnmail.springdata.entities.CartLine;
import com.sergsnmail.springdata.entities.DTO.CartLineDto;
import com.sergsnmail.springdata.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public List<CartLineDto> getProduct() {
        return cartService.findAll();
    }

    @PostMapping
    public void addProduct(@RequestBody CartLine cartLine) {
        cartService.add(cartLine.getProductId(), cartLine.getCount());
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id,
                                  @RequestParam (name="count", defaultValue = "1", required = false) Integer count) {
        cartService.deleteById(id, count);
    }
}
