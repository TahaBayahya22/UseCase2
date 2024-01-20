package com.example.microserviceproduits.fakedata;

import com.example.microserviceproduits.dao.DbContext;
import com.example.microserviceproduits.models.Produit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@Service
public class FakeData {

    @Autowired
    DbContext _context;

    @Bean
    public CommandLineRunner initializeTestData() {
        return args -> {
            // Generate and save 30 fake Commande entities
            for (int i = 0; i < 30; i++) {
                Produit produit = new Produit();
                produit.setDescription("Description " + (i + 1));
                produit.setPrixU(ThreadLocalRandom.current().nextInt(1, 100));

                _context.getProduitsDAO().save(produit);
            }
        };
    }
}
