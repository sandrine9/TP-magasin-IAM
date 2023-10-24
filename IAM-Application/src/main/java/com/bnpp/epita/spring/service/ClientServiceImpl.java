package com.bnpp.epita.spring.service;

import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.data.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService{

    @Autowired
    IClientRepository repository;

    @Override
    public void createClient(Client c) {
          repository.save(c);
    }

    @Override
    public void updateClient(Client c) {
        repository.save(c);
    }

    @Override
    public void deleteClient(Client c) {
        repository.delete(c);
    }

    @Override
    public Client findClientById(Integer id) {
        Optional<Client> clientOptional=repository.findById(id);
        return clientOptional.isPresent() ? clientOptional.get() : null ;
        /*   écriture avec opérations ternaires
              ? si vrai     : si faux

        //équivaut à :
        if(clientOptional.isPresent()){
            return clientOptional.get();
        }
        return null;
        */
    }

    @Override
    public Client findClientByEmail(String email) {
        return repository.findByEmail(email);

    }

}
