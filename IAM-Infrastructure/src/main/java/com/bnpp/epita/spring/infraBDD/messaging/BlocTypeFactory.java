package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.messaging.converter.IClientConverter;
import com.bnpp.epita.spring.infraBDD.messaging.dto.BlocType;

public class BlocTypeFactory {

    public static BlocType createBlocType(String discriminant, Client client){
        IClientConverter mapper = Mappers.getMapper(IClientConverter.class);
        switch (discriminant){
            case "IDENTITY":
                    ///return blocTypeIdentity
                return mapper.clientEntityToIdentityDto(client);
                break;
            case "FULL":
                ///return blocTypeFull
                return mapper.clientToFullDto(client);
                break;
            default:
                return null;
        }

    }
}
