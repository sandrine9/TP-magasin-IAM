package com.bnpp.epita.spring.infraBDD.messaging.dto;

public class BlocTypeFull extends BlocType{

    private String prenom;

    public BlocTypeFull() {
    }

    public BlocTypeFull(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
