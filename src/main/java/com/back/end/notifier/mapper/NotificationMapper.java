package com.back.end.notifier.mapper;

import com.back.end.notifier.dto.notification.NotificationRequest;
import com.back.end.notifier.dto.notification.NotificationResponse;
import com.back.end.notifier.entity.Notification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NotificationMapper {

    public NotificationResponse notificationToNotificationResponse(Notification notification) {
        return new NotificationResponse(notification.getId(), notification.getTitle(), notification.getName());
    }
}
