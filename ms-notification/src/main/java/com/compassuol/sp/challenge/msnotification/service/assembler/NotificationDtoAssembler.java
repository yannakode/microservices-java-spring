package com.compassuol.sp.challenge.msnotification.service.assembler;

import com.compassuol.sp.challenge.msnotification.model.Notification;
import com.compassuol.sp.challenge.msnotification.model.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationDtoAssembler {
    public NotificationDto toDto(Notification notification){
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEvent(notification.getEvent());
        notificationDto.setEmail(notification.getEmail());
        notificationDto.setDate(notification.getDate());
        return notificationDto;
    }

    public Notification toModel(NotificationDto notificationDto){
        Notification notification = new Notification();
        notification.setEvent(notificationDto.getEvent());
        notification.setEmail(notificationDto.getEmail());
        notificationDto.setDate(notificationDto.getDate());
        return notification;
    }
}
