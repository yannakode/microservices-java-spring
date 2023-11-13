package com.compassuol.sp.challenge.msnotification.service.impl;

import com.compassuol.sp.challenge.msnotification.model.Notification;
import com.compassuol.sp.challenge.msnotification.model.dto.NotificationDto;
import com.compassuol.sp.challenge.msnotification.repository.NotificationRepository;
import com.compassuol.sp.challenge.msnotification.service.NotificationService;
import com.compassuol.sp.challenge.msnotification.service.assembler.NotificationDtoAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationDtoAssembler model;

    @Override
    public List<NotificationDto> getNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(model::toDto)
                .collect(Collectors.toList());
    }
}
