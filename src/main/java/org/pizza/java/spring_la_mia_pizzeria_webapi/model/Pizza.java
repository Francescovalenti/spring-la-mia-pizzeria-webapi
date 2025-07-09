package org.pizza.java.spring_la_mia_pizzeria_webapi.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizze")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Il nome è obbligatorio")
    @Size(max = 100, message = "Il nome non può superare 100 caratteri")
    @Column(name = "nome")
    private String name;
    
    @Size(max = 255, message = "La descrizione non può superare 255 caratteri")
    @Column(name = "descrizione")
    private String description;

    @Column(name = "foto_url")
    @NotBlank(message = "immagine è obbligatoria")
    private String pic;
    @NotNull(message = "Il prezzo è obbligatorio")
    @DecimalMin(value = "0.01", message = "Il prezzo deve essere maggiore di zero")
    @Column(name = "prezzo")
    private BigDecimal price;

    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> SpecialOffers;

    @ManyToMany
    @JoinTable(
        name="pizza_ingredients",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private Set<Ingredient> ingredients;
    
  

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<SpecialOffer> getSpecialOffers() {
        return this.SpecialOffers;
    }

    public void setSpecialOffer(List<SpecialOffer> SpecialOffers) {
        this.SpecialOffers = SpecialOffers;
    }



    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

}
