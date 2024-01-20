package com.mcommandes.controller;

import com.mcommandes.configurations.ApplicationPropretiesConfigurations;
import com.mcommandes.dao.CommandeRepository;
import com.mcommandes.model.Commande;

import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController

public class CommandeController implements HealthIndicator {
    @Autowired
    CommandeRepository commandeRepository;
    @Autowired
    ApplicationPropretiesConfigurations appProperties;
   // @Convert(converter = LocalDateTimeConverter.class)
    // Affiche la liste de tous les produits disponibles
    @GetMapping("/commandes")
    public List<Commande> listeDesProduits() {
        System.out.println(" ********* CommandeController listeDesCommandes() ");
        List<Commande> commandes = commandeRepository.findAll();

        System.out.println("#### value : " +  appProperties.getCommandes_last());
       /* if (commandes.isEmpty())
            throw new CommandeNotFoundException("Aucune commande disponible pour l'afficher");*/
//        List<Commande> listeLimitee = commandes.subList(0, appProperties.getCommandes_last());
        LocalDate startDate = LocalDate.now().minusDays(appProperties.getCommandes_last());

        List<Commande> lastCommandes = commandeRepository.findByDateGreaterThan(startDate);
        return lastCommandes;
    }
    // Récuperer un produit par son id
    @GetMapping(value = "/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable Long id) {
        System.out.println(" ********* CommandeController recupererUneCommande(@PathVariable Long id) ");
        Optional<Commande> commande = commandeRepository.findById(id);
        /*if (!commande.isPresent())
            throw new CommandNotFoundException("La commande correspondante à l'id " + id + " n'existe pas");*/
        return commande;
    }

    @Override
    public Health health() {
        System.out.println("****** Actuator : Commande" +
                "Controller health() ");
        List<Commande> commandes = commandeRepository.findAll();
        if (commandes.isEmpty()) {
            return Health.down().build();
        }
        return Health.up().build();
    }
}