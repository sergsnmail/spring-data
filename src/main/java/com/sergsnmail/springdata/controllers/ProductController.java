package com.sergsnmail.springdata.controllers;

import com.sergsnmail.springdata.entities.DTO.ProductDto;
import com.sergsnmail.springdata.entities.Product;
import com.sergsnmail.springdata.exception.product.ProductNotFoundException;
import com.sergsnmail.springdata.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Product with id=" + id + " not found"));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct) {
        return productService.saveOrUpdate(newProduct);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/cost")
    public List<ProductDto> getAllProductFilteredByCost(
            @RequestParam (name="minCost", defaultValue = "0.0", required = false) Float minCost,
            @RequestParam (name="maxCost", defaultValue = "0.0", required = false) Float maxCost) {
        if (minCost > 0F && maxCost > 0F && minCost < maxCost) {
            return productService.findAllByCostBetween(minCost, maxCost);
        } else if (minCost > 0F) {
            return productService.findAllByCostGreaterThan(minCost);
        } else if (maxCost > 0F) {
            return productService.findAllByCostIsLessThan(maxCost);
        } else {
            return getAllProducts();
        }
    }

    @GetMapping("/title")
    public List<ProductDto> findAllProductByTitleLike(
            @RequestParam (name="name", defaultValue = "", required = false) String title) {
        return productService.findAllByTitleIgnoreCaseLike(String.format("%%%s%%", title));
    }

}
