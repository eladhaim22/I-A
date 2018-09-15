package com.ia.repository;

import com.ia.entity.Person;
import com.ia.entity.Role;
import com.ia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByDni(String dni);
}