package org.pizza.java.spring_la_mia_pizzeria_relazioni.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name= "specialoffers")
public class SpecialOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   
    @NotNull(message="Inserisci un titolo")
   
     private LocalDate startDate;

    @NotNull(message = "Inserisci una data")
   
    private LocalDate  enDate;

    @NotBlank(message = "Inserisci un titolo")
    @NotNull
    private String title;
    
    @ManyToOne
    @JoinColumn(name="pizza_id",nullable = false)
    @JsonBackReference
    private Pizza pizza;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEnDate() {
        return this.enDate;
    }

    public void setEnDate(LocalDate enDate) {
        this.enDate = enDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public Pizza getPizza() {
        return this.pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
   
  
  
     
}
