package com.bnpp.epita.spring.service;

import com.bnpp.epita.spring.domaine.Client;

import java.util.List;

public interface IClientService {
    void createClient(Client c);

    void updateClient(Client c);

    void deleteClient(Client c);
    Client findClientById (Integer id);

    Client findClientByEmail (String email);
}
