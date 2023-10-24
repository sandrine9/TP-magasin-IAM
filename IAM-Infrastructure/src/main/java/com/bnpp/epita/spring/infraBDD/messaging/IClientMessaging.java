package com.bnpp.epita.spring.infraBDD.messaging;

import com.bnpp.epita.spring.infraBDD.messaging.dto.MessageIamDto;

public interface IClientMessaging {

    public void comsumeMessage(String msg);
}
