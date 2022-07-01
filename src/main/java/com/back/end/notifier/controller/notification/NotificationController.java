package com.back.end.notifier.controller.notification;

import com.back.end.notifier.dto.notification.NotificationRequest;
import com.back.end.notifier.dto.notification.NotificationResponse;
import com.back.end.notifier.security.UserDetailsImpl;
import com.back.end.notifier.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationResponse> getNotifications(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return notificationService.getNotificationsForUser(userDetails.getId());
    }

    @GetMapping("/{id}")
    public NotificationResponse getNotifications(@PathVariable Long id) {
        return notificationService.getNotificationById(id);
    }

    @PostMapping
    public NotificationResponse createNotification(@RequestBody NotificationRequest notificationRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return notificationService.createNotification(notificationRequest, userDetails.getId());
    }

    @PutMapping("/{id}")
    public NotificationResponse updateNotification(@RequestBody NotificationRequest notificationRequest, @PathVariable Long id) {
        return notificationService.updateNotification(notificationRequest, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }
}
