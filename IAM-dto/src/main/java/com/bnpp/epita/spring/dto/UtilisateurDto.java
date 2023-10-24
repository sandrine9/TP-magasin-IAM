package com.bnpp.epita.spring.dto;

import java.util.List;

public class UtilisateurDto {
    private String email;
    private String password;
    private List<RoleDto> roles;

    public UtilisateurDto(String email, String password, List<RoleDto> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UtilisateurDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
