package com.bnpp.epita.spring.converter;

import com.bnpp.epita.spring.domaine.Adresse;
import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.dto.AdresseDTO;
import com.bnpp.epita.spring.dto.ClientCourtDTO;
import com.bnpp.epita.spring.dto.ClientDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    //clients
    public ClientDTO convertirClientEnDTO (Client c){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(c.getId());
        clientDTO.setNom(c.getNom());
        clientDTO.setPrenom(c.getPrenom());
        clientDTO.setDateNaissance(c.getDateNaissance());
        clientDTO.setEmail(c.getEmail());
        clientDTO.setAdresseDTO(convertirAdresseEnDTO(c.getAdresse()));
        return clientDTO;
    }
    public ClientCourtDTO convertirClientEnClientCourtDTO (Client c){
        ClientCourtDTO clientCourtDTO = new ClientCourtDTO();
        clientCourtDTO.setId(c.getId());
        clientCourtDTO.setNom(c.getNom());
        clientCourtDTO.setPrenom(c.getPrenom());
        clientCourtDTO.setEmail(c.getEmail());
        clientCourtDTO.setCodePostal(c.getAdresse().getCodePostal());
        clientCourtDTO.setVille(c.getAdresse().getVille());
        return clientCourtDTO;
    }
    public Client convertirDTOenClient (ClientDTO dto){
        Client client = new Client();
        if(dto.getId() != null){
            client.setId(dto.getId());
        }
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setDateNaissance(dto.getDateNaissance());
        client.setEmail(dto.getEmail());
        client.setAdresse(convertirDTOenAdresse(dto.getAdresseDTO()));
        client.setPassword(dto.getPassword());
        client.setRoles(dto.getRoles());
        return client;
    }
    public Client convertirDTOenClientEntityViaModelMapper (ClientDTO dto){
        ModelMapper mapper = new ModelMapper();
        //TypeMap<ClientDTO, Client> propertyMapper = mapper.createTypeMap(ClientDTO.class, Client.class);
        //propertyMapper.addMapping(ClientDTO::getAdresseDTO, Client::setAdresse);
                      // besoin car adresse n'est pas nommée de la même façon dans dto et client
                      // il a fallu ajouter un addmapping .... TypeMap, etc ...
                      // mais il a compris de lui même grâce au @JsonProperty
        return mapper.map(dto,Client.class);
        // ModelMapper bien quand pas de modif entre entity et dto
    }

    private AdresseDTO convertirAdresseEnDTO (Adresse a){
        AdresseDTO dto = new AdresseDTO();
        dto.setId(a.getId());
        dto.setRue(a.getRue());
        dto.setCodePostal(a.getCodePostal());
        dto.setVille(a.getVille());
        return dto;
    }
    private Adresse convertirDTOenAdresse (AdresseDTO dto){
        Adresse adr = new Adresse();
        if(dto.getId() != null){
            adr.setId(dto.getId());
        }
        adr.setRue(dto.getRue());
        adr.setCodePostal(dto.getCodePostal());
        adr.setVille(dto.getVille());
        return adr;
    }



}
