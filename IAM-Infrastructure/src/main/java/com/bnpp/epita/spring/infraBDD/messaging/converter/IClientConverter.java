package com.bnpp.epita.spring.infraBDD.messaging.converter;

import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.messaging.dto.BlocTypeFull;
import com.bnpp.epita.spring.infraBDD.messaging.dto.BlocTypeIdentity;

@Mapper
public interface IClientConverter {


    BlocTypeIdentity clientEntityToIdentityDto(Client c);


    BlocTypeFull clientToFullDto(Client client);
}
