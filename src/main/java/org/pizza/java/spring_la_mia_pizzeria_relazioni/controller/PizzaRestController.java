package org.pizza.java.spring_la_mia_pizzeria_relazioni.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping("/api/pizzas")
public class PizzaRestController {
    
}
