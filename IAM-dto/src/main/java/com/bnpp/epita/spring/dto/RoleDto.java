package com.bnpp.epita.spring.dto;

public class RoleDto {
    private String nom;

    public RoleDto(String nom) {
        this.nom = nom;
    }

    public RoleDto() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
