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
        notificationDto.setNotification(notification.getNotification());
        notificationDto.setEmail(notification.getEmail());
        return notificationDto;
    }

    public Notification toModel(NotificationDto notificationDto){
        Notification notification = new Notification();
        notification.setNotification(notificationDto.getNotification());
        notification.setEmail(notificationDto.getEmail());
        return notification;
    }
}
