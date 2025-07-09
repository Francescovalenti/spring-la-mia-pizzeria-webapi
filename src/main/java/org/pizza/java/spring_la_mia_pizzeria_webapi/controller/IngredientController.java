package org.pizza.java.spring_la_mia_pizzeria_webapi.controller;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Ingredient;
import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.pizza.java.spring_la_mia_pizzeria_webapi.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "ingredients/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).orElseThrow());
        return "ingredients/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    @PostMapping()
    public String store(@Valid @ModelAttribute Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientRepository.findById(id).orElseThrow());
        return "ingredients/edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ingredient") Ingredient formIngredient,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/edit";
        }

        formIngredient.setId(id); 
        ingredientRepository.save(formIngredient);
        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Ingredient ingredientToDelete = ingredientRepository.findById(id).orElseThrow();
        for (Pizza linkedPizza : ingredientToDelete.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredientToDelete);
        }
        ingredientRepository.deleteById(id);
        return "redirect:/ingredients";
    }
}
