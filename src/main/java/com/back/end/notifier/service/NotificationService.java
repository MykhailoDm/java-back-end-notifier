package com.back.end.notifier.service;

import com.back.end.notifier.dto.notification.NotificationRequest;
import com.back.end.notifier.dto.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {

    List<NotificationResponse> getNotificationsForUser(Long id);

    NotificationResponse createNotification(NotificationRequest notificationRequest, Long userId);

    NotificationResponse deleteNotification(String username);

    NotificationResponse updateNotification(NotificationRequest notificationRequest);

}
