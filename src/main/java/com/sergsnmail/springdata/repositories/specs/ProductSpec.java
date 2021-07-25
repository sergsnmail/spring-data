package com.sergsnmail.springdata.repositories.specs;

import com.sergsnmail.springdata.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {
    public static Specification<Product> findAllByCostGreaterThan(Float minCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("cost"), minCost);
    }

    public static Specification<Product> findAllByCostIsLessThan(Float maxCost) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCost);
    }

    public static Specification<Product> findAllByTitleIgnoreCaseLike(String title) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("title")), "%" + title.toUpperCase() + "%");
    }
}
