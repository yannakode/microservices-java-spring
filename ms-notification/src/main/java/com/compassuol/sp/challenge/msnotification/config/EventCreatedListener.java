package com.compassuol.sp.challenge.msnotification.config;

import com.compassuol.sp.challenge.msnotification.model.Notification;
import com.compassuol.sp.challenge.msnotification.repository.NotificationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventCreatedListener {
    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = "user-event")
    public void handleMessage(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        Notification notification = objectMapper.readValue(message, Notification.class);

        notificationRepository.save(notification);
        System.out.println("Saved Notification: " + notification.getId());
    }

}
