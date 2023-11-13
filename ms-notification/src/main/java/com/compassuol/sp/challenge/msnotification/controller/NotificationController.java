package com.compassuol.sp.challenge.msnotification.controller;

import com.compassuol.sp.challenge.msnotification.model.dto.NotificationDto;
import com.compassuol.sp.challenge.msnotification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @GetMapping("notifications")
    public List<NotificationDto> getNotification(){
        return notificationService.getNotifications();
    }
}
