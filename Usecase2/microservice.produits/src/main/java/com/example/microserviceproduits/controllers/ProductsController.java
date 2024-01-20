package com.example.microserviceproduits.controllers;

import com.example.microserviceproduits.configurations.ApplicationPropertiesConfiguration;
import com.example.microserviceproduits.models.Produit;
import com.example.microserviceproduits.services.v1.ProduitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Produits")
public class ProductsController implements HealthIndicator {
    @Autowired
    ProduitsService produitsService;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;


    @GetMapping
    public List<Produit> getAllProduits(){
        System.out.println("### Max price to return : " + appProperties.getMaxPrice());

        var maxPrice = appProperties.getMaxPrice();

        var allCom = produitsService.getAllProduits();


        List<Produit> produits = allCom.stream()
                .filter(c -> c.getPrixU() <= maxPrice)
                .sorted(Comparator.comparing(Produit::getPrixU).reversed())
                .collect(Collectors.toList());

        return produits;
    }

    @GetMapping( "/{id}")
    public Produit getProduit(@PathVariable(name = "id") Integer id){
        return produitsService.getProduit(id);
    }

    @PostMapping()
    public Produit postProduit(@RequestBody Produit produit){
        produitsService.Add(produit);
        return produit;
    }

    @DeleteMapping("/{id}")
    public boolean deleteProduit(@PathVariable Integer id){
        return produitsService.DeleteProduit(id);
    }

    @Override
    public Health health() {
        var produits = produitsService.getAllProduits();
        return produits.isEmpty() ? Health.down().build() : Health.up().build();
    }
}
