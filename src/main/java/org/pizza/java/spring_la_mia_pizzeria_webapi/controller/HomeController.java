package org.pizza.java.spring_la_mia_pizzeria_webapi.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/")
public class HomeController {
    @GetMapping
    public String homePage(){
    return "pages/home";}
}
