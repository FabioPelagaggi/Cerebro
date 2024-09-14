package com.infnet.comsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.model.ThreatNotificationPayload;
import com.infnet.service.MutantService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ThreatWarningComsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final MutantService mutantService;

    @RabbitListener(queues = { "threat-warning-queue" })
    public void getMessange(@Payload Message<String> message) {

        String messageBody = message.getPayload();
        ThreatNotificationPayload threatNotificationPayload = new ThreatNotificationPayload();

        try {
            threatNotificationPayload = objectMapper.readValue(messageBody, ThreatNotificationPayload.class);

            System.out.println("Threat Warning Received: " + threatNotificationPayload.toString());

            mutantService.updateThreat(threatNotificationPayload.getMutantId(), true);

        } catch (JsonProcessingException e) {
            System.out.println("Error processing JSON: " + e.getMessage());
        }

    }

}
