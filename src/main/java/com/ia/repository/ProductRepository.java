package com.ia.repository;

import com.ia.entity.Product;
import com.ia.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByActiveIsTrue();
    Product findBySku(String sku);
}