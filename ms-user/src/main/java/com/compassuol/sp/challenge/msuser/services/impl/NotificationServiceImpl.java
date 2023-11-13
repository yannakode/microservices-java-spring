package com.compassuol.sp.challenge.msuser.services.impl;

import com.compassuol.sp.challenge.msuser.model.dto.UserRequestDto;
import com.compassuol.sp.challenge.msuser.model.entity.EventNotification;
import com.compassuol.sp.challenge.msuser.services.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Override
    public EventNotification sendMessage(String email, String event) throws JsonProcessingException {
        EventNotification eventNotification = new EventNotification();
        eventNotification.setEvent(event);
        eventNotification.setEmail(email);
        eventNotification.setDate(LocalDateTime.now().toString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        String jsonMessage = objectMapper.writeValueAsString(eventNotification);

        rabbitTemplate.convertAndSend("user-event-exchange", "user-event", jsonMessage);
        return eventNotification;
    }
}
