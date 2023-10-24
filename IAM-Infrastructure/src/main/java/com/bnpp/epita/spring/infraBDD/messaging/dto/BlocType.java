package com.bnpp.epita.spring.infraBDD.messaging.dto;

public abstract class BlocType {
    private String nom;

    public BlocType(String nom) {
        this.nom = nom;
    }

    public BlocType() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
