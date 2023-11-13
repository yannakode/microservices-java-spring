package com.compassuol.sp.challenge.msnotification.service;

import com.compassuol.sp.challenge.msnotification.model.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    List<NotificationDto> getNotifications();
}
