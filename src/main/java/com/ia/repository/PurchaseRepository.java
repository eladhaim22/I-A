package com.ia.repository;

import com.ia.entity.Person;
import com.ia.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Integer> {
    List<Purchase> findAllByUser_Id(Long userId);
}