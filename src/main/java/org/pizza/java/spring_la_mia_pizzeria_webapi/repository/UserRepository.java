package org.pizza.java.spring_la_mia_pizzeria_webapi.repository;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Integer> {
    Optional <User> findByUsername(String username);
}
