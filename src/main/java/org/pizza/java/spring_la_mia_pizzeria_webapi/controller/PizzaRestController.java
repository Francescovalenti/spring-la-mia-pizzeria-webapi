package org.pizza.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.pizza.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;



@RestController

@RequestMapping("/api/pizzas")
public class PizzaRestController {
    @Autowired
  private PizzaRepository pizzaRepository;
  
  
    @GetMapping
public List<Pizza> index() {
    return pizzaRepository.findAll();
}

@GetMapping("{id}")
public Pizza get(@PathVariable Integer id) { 
    return pizzaRepository.findById(id).get();
}

@PostMapping
public Pizza store(@RequestBody Pizza pizza){ 
   
    return pizzaRepository.save(pizza);
}

@PutMapping ("{id}")
public Pizza update( @RequestBody Pizza pizza , @PathVariable Integer id) {
    pizza.setId(id); 
    return pizzaRepository.save(pizza);

 }

@DeleteMapping("/{id}")
public void delete(@PathVariable Integer id) { 
    pizzaRepository.deleteById(id);
}




}
