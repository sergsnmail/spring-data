package com.sergsnmail.springdata.controllers;

import com.sergsnmail.springdata.entities.DTO.ProductDto;
import com.sergsnmail.springdata.entities.Product;
import com.sergsnmail.springdata.exception.product.ProductNotFoundException;
import com.sergsnmail.springdata.repositories.specs.ProductSpec;
import com.sergsnmail.springdata.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getProducts(
            @RequestParam (name="minCost", required = false) Float minCost,
            @RequestParam (name="maxCost", required = false) Float maxCost,
            @RequestParam (name="title", required = false) String title) {

        Specification<Product> specProduct = Specification.where(null);

        if (minCost != null) {
            specProduct = specProduct.and(ProductSpec.findAllByCostGreaterThan(minCost));
        }
        if (maxCost != null) {
            specProduct = specProduct.and(ProductSpec.findAllByCostIsLessThan(maxCost));
        }
        if (title != null) {
            specProduct = specProduct.and(ProductSpec.findAllByTitleIgnoreCaseLike(title));
        }

        return productService.findAll(specProduct);
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

}
