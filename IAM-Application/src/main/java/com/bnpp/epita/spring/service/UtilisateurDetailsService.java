package com.bnpp.epita.spring.service;

import com.bnpp.epita.spring.domaine.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    @Autowired
    IClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        //récupérer l'identification du User en BDD= email
        Client client = clientService.findClientByEmail(email);
        System.out.println("client by email : "+ client.toString());
        //on alimente la liste des rôles dans authorities
        List<String> listeNomRole = client.getRoles().stream()
                .map(roleClient -> roleClient.getNom())
                .collect(Collectors.toList());
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String nomRole : listeNomRole) {
            authorities.add(new SimpleGrantedAuthority(nomRole));
        }

        //on charge le User pour SpringSecurity
        return new User(client.getEmail(), client.getPassword(), authorities);
    }

}
