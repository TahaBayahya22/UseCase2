package com.example.microserviceproduits.services;

import com.example.microserviceproduits.models.Produit;

import java.util.List;

public interface IProduitsService {
    boolean Add(Produit produit);
    Produit getProduit(Integer id);
    List<Produit> getAllProduits();
    boolean UpdateProduit(Produit produit);
    boolean DeleteProduit(Integer id);
}
