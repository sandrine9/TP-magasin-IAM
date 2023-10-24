package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.domaine.Client;
import com.bnpp.epita.spring.infraBDD.data.IClientRepository;
import com.bnpp.epita.spring.infraBDD.messaging.dto.BlocType;
import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageIamDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

public class ClientMessagingImpl implements IClientMessaging {

    @Autowired
    IClientRepository repository;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    ObjectMapper mapper;
    @Override
    @JmsListener(destination = "QUEUE_ERP_TO_IAM")
    public void comsumeMessage(String msg) throws JsonProcessingException {
        MessageIamDto dto = mapper.readValue(msg, MessageIamDto.class);
        if(dto.getAction().equals("GET")){

            //rech en bdd
            Client client=repository.findById(dto.getIdClient()).get();
            //regarder le bloc type souhaité
            final BlocType bloc=BlocTypeFactory.createBlocType(dto.getBlocType(),client);
            //poster msg
            String blocToJson = mapper.writeValueAsString(bloc);
            jmsTemplate.send("QUEUE_IAM_TO_ERP", session -> session.createTextMessage(blocToJson));

        } else if (dto.getAction().equals("CREATE")) {

                //recuprer payload en objet
                Client clientFromErp = mapper.readValue(dto.getPayload(), Client.class);
                //save objet
                repository.save(clientFromErp)

        }
    }
}
