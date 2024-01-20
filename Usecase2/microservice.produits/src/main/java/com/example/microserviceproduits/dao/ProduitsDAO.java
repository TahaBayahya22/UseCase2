package com.example.microserviceproduits.dao;

import com.example.microserviceproduits.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitsDAO extends JpaRepository<Produit, Integer> {
}
