package org.pizza.java.spring_la_mia_pizzeria_webapi.repository;

import java.util.List;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;


public interface PizzaRepository extends JpaRepository<Pizza,Integer>{
   public  List<Pizza> findByNameContainingIgnoreCase(String name);
    public List<Pizza> findByName(String name);
    public List<Pizza> findByDescription(String description);
    public List<Pizza> findByPrice(BigDecimal price);
    
} 
    

