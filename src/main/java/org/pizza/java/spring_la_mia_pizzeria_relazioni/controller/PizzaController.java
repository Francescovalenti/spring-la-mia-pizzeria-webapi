package org.pizza.java.spring_la_mia_pizzeria_relazioni.controller;

import org.pizza.java.spring_la_mia_pizzeria_relazioni.model.Pizza;
import org.pizza.java.spring_la_mia_pizzeria_relazioni.repository.PizzaRepository;
import org.pizza.java.spring_la_mia_pizzeria_relazioni.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(id);
        if (pizzaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata");
        }

        Pizza pizza = pizzaOptional.get();
        model.addAttribute("pizza", pizza);
        model.addAttribute("offers", pizza.getSpecialOffers());

        return "pizzas/show";
    }

    @GetMapping
    public String index(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Pizza> pizzas;

        if (keyword != null && !keyword.isEmpty()) {
            pizzas = pizzaRepository.findByNameContainingIgnoreCase(keyword);
        } else {
            pizzas = pizzaRepository.findAll();
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("keyword", keyword);

        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza pizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/create";
        }

        pizzaRepository.save(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata"));
        model.addAttribute("pizza", pizza);
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientRepository.findAll());
            return "pizzas/edit";
        }

        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        pizzaRepository.deleteById(id);
        return "redirect:/pizzas";
    }
}
