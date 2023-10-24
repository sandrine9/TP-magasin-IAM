package com.bnpp.epita.spring.infraBDD.messaging.dto;

public class BlocTypeIdentity extends BlocType{

    private String email;

    public BlocTypeIdentity(String email) {
        this.email = email;
    }

    public BlocTypeIdentity() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
