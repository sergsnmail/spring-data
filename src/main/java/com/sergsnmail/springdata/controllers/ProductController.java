package com.sergsnmail.springdata.controllers;

import com.sergsnmail.springdata.entities.Product;
import com.sergsnmail.springdata.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    @GetMapping("/cost")
    public List<Product> getAllProductFilteredByCost(
            @RequestParam (name="minCost", defaultValue = "0.0", required = false) Float minCost,
            @RequestParam (name="maxCost", defaultValue = "0.0", required = false) Float maxCost) {
        if (minCost > 0F && maxCost > 0F && minCost < maxCost) {
            return productRepository.findAllByCostBetween(minCost, maxCost);
        } else if (minCost > 0F) {
            return productRepository.findAllByCostGreaterThan(minCost);
        } else if (maxCost > 0F) {
            return productRepository.findAllByCostIsLessThan(maxCost);
        } else {
            return getAllProducts();
        }
    }

    @GetMapping("/title")
    public List<Product> findAllProductByTitleLike(
            @RequestParam (name="name", defaultValue = "", required = false) String title) {
        return productRepository.findAllByTitleIgnoreCaseLike(String.format("%%%s%%", title));
    }

    @ResponseBody
    @RequestMapping("/top/pages/")
    public List<Product> getAllProductsByPage(@PageableDefault(value=10, page=0) Pageable pageable) {
        return  productRepository.findAll(pageable).getContent();
    }

}
