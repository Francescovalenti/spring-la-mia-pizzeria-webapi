package org.pizza.java.spring_la_mia_pizzeria_relazioni.model;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Ingredients")

public class Ingredient {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @ManyToMany(mappedBy = "ingredients")
    @JsonBackReference
    private Set<Pizza> pizzas;

    
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void setPizzas(Set<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

}
