package com.sergsnmail.springdata.repositories;

import com.sergsnmail.springdata.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostGreaterThan(Float minCost);
    List<Product> findAllByCostIsLessThan(Float maxCost);
    List<Product> findAllByCostBetween(Float minCost, Float maxCost);
    List<Product> findAllByTitleIgnoreCaseLike(String title);
    List<Product> findAllBy();
}
