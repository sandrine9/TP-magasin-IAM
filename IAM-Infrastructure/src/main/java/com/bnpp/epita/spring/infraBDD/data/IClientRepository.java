package com.bnpp.epita.spring.infraBDD.data;

import com.bnpp.epita.spring.domaine.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {

    @Query("select c FROM Client c JOIN FETCH c.roles WHERE c.email=:email")
    Client findByEmail (@Param("email") String email);
}
