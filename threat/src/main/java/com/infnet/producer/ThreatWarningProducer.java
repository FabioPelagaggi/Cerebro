package com.infnet.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.model.ThreatNotificationModel;

@Component
public class ThreatWarningProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendWarning(ThreatNotificationModel threatNotificationModel) throws JsonProcessingException, AmqpException {
        amqpTemplate.convertAndSend("threat-warning-exchange", "threat-warning", objectMapper.writeValueAsString(threatNotificationModel));
    }

}
