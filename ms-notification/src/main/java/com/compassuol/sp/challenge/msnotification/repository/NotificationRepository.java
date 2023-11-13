package com.compassuol.sp.challenge.msnotification.repository;

import com.compassuol.sp.challenge.msnotification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}