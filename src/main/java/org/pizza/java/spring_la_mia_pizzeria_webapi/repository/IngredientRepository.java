package org.pizza.java.spring_la_mia_pizzeria_webapi.repository;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {

}
