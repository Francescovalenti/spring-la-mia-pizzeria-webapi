package org.pizza.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.List;
import java.util.Optional;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.pizza.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<Pizza> pizzas = pizzaRepository.findAll();
        return pizzas;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> show(@PathVariable Integer id) {
        Optional<Pizza> pizzaAttempt = pizzaRepository.findById(id);

        if (pizzaAttempt.isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Pizza>(pizzaAttempt.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> store(@RequestBody Pizza pizza) {
        return new ResponseEntity<Pizza>(pizzaRepository.save(pizza), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Pizza> update(@RequestBody Pizza pizza, @PathVariable Integer id) {

        if (pizzaRepository.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);

        }

        pizza.setId(id);
        return new ResponseEntity<Pizza>(pizzaRepository.save(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pizza> delete(@PathVariable Integer id) {
        if (pizzaRepository.findById(id).isEmpty()) {
            return new ResponseEntity<Pizza>(HttpStatus.NOT_FOUND);
        }

        pizzaRepository.deleteById(id);
        return new ResponseEntity<Pizza>(HttpStatus.OK);
    }
}