package com.sergsnmail.springdata.services;

import com.sergsnmail.springdata.entities.DTO.CartLineDto;
import com.sergsnmail.springdata.repositories.CartRepository;
import com.sergsnmail.springdata.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public List<CartLineDto> findAll() {
        return cartRepository.findAll();
    }

    public void add(Long productId, int count) {
        cartRepository.add(productRepository.getById(productId), count);
    }

    public void deleteById(Long productId, int count) {
        cartRepository.deleteById(productRepository.getById(productId), count);
    }
}
