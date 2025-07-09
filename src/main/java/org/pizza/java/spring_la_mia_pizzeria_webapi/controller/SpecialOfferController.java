package org.pizza.java.spring_la_mia_pizzeria_webapi.controller;

import java.util.Optional;

import org.pizza.java.spring_la_mia_pizzeria_webapi.model.Pizza;
import org.pizza.java.spring_la_mia_pizzeria_webapi.model.SpecialOffer;
import org.pizza.java.spring_la_mia_pizzeria_webapi.repository.PizzaRepository;
import org.pizza.java.spring_la_mia_pizzeria_webapi.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/specialoffers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping("/create")
    public String create(@RequestParam("pizzaId") Integer pizzaId, Model model) {
        Optional<Pizza> pizzaOptional = pizzaRepository.findById(pizzaId);

        if (pizzaOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata");
        }

        Pizza pizza = pizzaOptional.get();

        SpecialOffer offer = new SpecialOffer();
        offer.setPizza(pizza);

        model.addAttribute("specialOffer", offer);
        return "specialoffers/create";
    }

   @PostMapping("/store")
public String store(
        @Valid @ModelAttribute("specialOffer") SpecialOffer offer,
        BindingResult bindingResult,
        Model model
) {
   
    if (offer.getPizza() == null || offer.getPizza().getId() == null) {
        bindingResult.reject("pizza", "Pizza mancante. Ricarica la pagina.");
        return "specialoffers/create";
    }

   
    Pizza pizza = pizzaRepository.findById(offer.getPizza().getId()).orElse(null);
    if (pizza == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata");
    }
    offer.setPizza(pizza); 

    
    if (offer.getEnDate().isBefore(offer.getStartDate())) {
        bindingResult.rejectValue("enDate", "invalidDate", "La data di fine non può essere prima della data di inizio.");
    }

    if (bindingResult.hasErrors()) {
        return "specialoffers/create";
    }

    specialOfferRepository.save(offer);
    return "redirect:/pizzas/" + pizza.getId();
}

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<SpecialOffer> optionalOffer = specialOfferRepository.findById(id);

        if (optionalOffer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta non trovata");
        }

        SpecialOffer offer = optionalOffer.get();
        model.addAttribute("specialOffer", offer);
        return "specialoffers/edit";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Integer id,
            @Valid @ModelAttribute("specialOffer") SpecialOffer offer,
            BindingResult bindingResult,
            Model model) {

        if (offer.getEnDate().isBefore(offer.getStartDate())) {
            bindingResult.rejectValue("enDate", "invalidDate",
                    "La data di fine non può essere prima della data di inizio.");
        }

        if (bindingResult.hasErrors()) {
            return "specialoffers/edit";
        }

        specialOfferRepository.save(offer);
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }

    @PostMapping("/{id}/delete")
public String delete(@PathVariable Integer id) {
    Optional<SpecialOffer> offerOpt = specialOfferRepository.findById(id);

    if (offerOpt.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta non trovata");
    }

    Integer pizzaId = offerOpt.get().getPizza().getId(); // recupera ID della pizza prima di cancellare
    specialOfferRepository.deleteById(id);
    return "redirect:/pizzas/" + pizzaId;
}

}
