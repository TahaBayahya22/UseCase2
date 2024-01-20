package com.example.microserviceproduits.services.v1;

import com.example.microserviceproduits.dao.DbContext;
import com.example.microserviceproduits.models.Produit;
import com.example.microserviceproduits.services.IProduitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitsService implements IProduitsService {
    @Autowired
    DbContext _context;

    @Override
    public boolean Add(Produit produit) {
        try{
            _context.getProduitsDAO().save(produit);
            return true;
        }
        catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public Produit getProduit(Integer id) {
        var commande = _context.getProduitsDAO().findById(id);
        return commande.orElse(null);
    }

    @Override
    public List<Produit> getAllProduits() {
        var produits = _context.getProduitsDAO().findAll();
        return  produits;
    }

    @Override
    public boolean UpdateProduit(Produit commande) {
        try{
            if(!_context.getProduitsDAO().existsById(commande.getId())){
                return false;
            }
            _context.getProduitsDAO().save(commande);
            return true;
        }
        catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteProduit(Integer id) {
        try{
            var produit = getProduit(id);
            _context.getProduitsDAO().delete(produit);
            return true;
        }
        catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }
}
