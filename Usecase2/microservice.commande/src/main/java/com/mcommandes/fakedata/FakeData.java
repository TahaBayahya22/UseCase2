package com.mcommandes.fakedata;


import com.mcommandes.dao.CommandeRepository;
import com.mcommandes.model.Commande;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@Service
public class FakeData {

    @Autowired
    CommandeRepository commandeRepository;

    @Bean
    public CommandLineRunner initializeTestData() {
        return args -> {
            // Generate and save 30 fake Commande entities
            for (int i = 0; i < 30; i++) {
                Commande commande = new Commande();
                commande.setDescription("Description " + (i + 1));
                commande.setQuantite(ThreadLocalRandom.current().nextInt(1, 10));
                commande.setDate(generateRandomDate());
                commande.setMontant(ThreadLocalRandom.current().nextFloat() * 100);
                commande.setIdProduits(i);

                commandeRepository.save(commande);
            }
        };
    }

    public static LocalDate generateRandomDate() {
        // Define the date range (e.g., last year)
        LocalDate startDate = LocalDate.now().minusYears(1);
        LocalDate endDate = LocalDate.now();

        // Generate a random number of days within the range
        long randomDay = ThreadLocalRandom.current().nextLong(startDate.toEpochDay(), endDate.toEpochDay());

        // Convert the random day to a LocalDate
        return LocalDate.ofEpochDay(randomDay);
    }}
