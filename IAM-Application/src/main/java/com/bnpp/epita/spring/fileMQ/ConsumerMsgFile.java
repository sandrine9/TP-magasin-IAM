package com.bnpp.epita.spring.fileMQ;

import com.bnpp.epita.spring.converter.ClientConverter;
import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.domaine.RoleClient;
import com.bnpp.epita.spring.dto.AdresseDTO;
import com.bnpp.epita.spring.dto.ClientDTO;
import com.bnpp.epita.spring.infraBDD.data.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ConsumerMsgFile {

    @Autowired
    ClientConverter clientConverter;
    @Autowired
    IClientRepository repository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @JmsListener(destination = "QUEUE_IAM_POST_CLIENT")
    public void consumeAndCreateClient(String message) {
        if (!(message==null)){
            //reformater message String en objet ClientDto
            String[] tableauString = message.split(";");
            ClientDTO clientDTO = new ClientDTO();
            //clientDTO.setId(Integer.parseInt(tableauString[0]));
            clientDTO.setNom(tableauString[1]);
            clientDTO.setPrenom(tableauString[2]);
            clientDTO.setDateNaissance(LocalDate.parse(tableauString[3]));
            clientDTO.setEmail(tableauString[4]);

            AdresseDTO adresseDTO = new AdresseDTO();
            //adresseDTO.setId(Integer.parseInt(tableauString[5]));
            adresseDTO.setRue(tableauString[6]);
            adresseDTO.setCodePostal(tableauString[7]);
            adresseDTO.setVille(tableauString[8]);
            clientDTO.setAdresseDTO(adresseDTO);

            //encodage du password
            clientDTO.setPassword(passwordEncoder.encode(tableauString[9]));

            RoleClient roleClient = new RoleClient();
            roleClient.setId(Integer.parseInt(tableauString[10]));
            List<RoleClient> listeRole= new ArrayList<>();
            listeRole.add(roleClient);
            clientDTO.setRoles(listeRole);

            //créer en base de donnée
            Client client = clientConverter.convertirDTOenClient(clientDTO);
            repository.save(client);
        }

    }
}
