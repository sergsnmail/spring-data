package com.sergsnmail.springdata.services;

import com.sergsnmail.springdata.entities.DTO.ProductDto;
import com.sergsnmail.springdata.entities.Product;
import com.sergsnmail.springdata.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(ProductDto::new);
    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductDto> findAllByCostBetween(Float minCost, Float maxCost) {
        return productRepository.findAllByCostBetween(minCost, maxCost).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public List<ProductDto> findAllByCostGreaterThan(Float minCost) {
        return productRepository.findAllByCostGreaterThan(minCost).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public List<ProductDto> findAllByCostIsLessThan(Float maxCost) {
        return productRepository.findAllByCostIsLessThan(maxCost).stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public List<ProductDto> findAllByTitleIgnoreCaseLike(String title) {
        return productRepository.findAllByTitleIgnoreCaseLike(String.format("%%%s%%", title)).stream().map(ProductDto::new).collect(Collectors.toList());
    }
}
