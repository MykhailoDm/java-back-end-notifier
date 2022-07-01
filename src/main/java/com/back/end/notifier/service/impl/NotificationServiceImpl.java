package com.back.end.notifier.service.impl;

import com.back.end.notifier.dto.notification.NotificationRequest;
import com.back.end.notifier.dto.notification.NotificationResponse;
import com.back.end.notifier.entity.Notification;
import com.back.end.notifier.entity.User;
import com.back.end.notifier.exception.BadRequestException;
import com.back.end.notifier.exception.NotFoundException;
import com.back.end.notifier.exception.UserNotFoundException;
import com.back.end.notifier.mapper.NotificationMapper;
import com.back.end.notifier.repo.NotificationRepository;
import com.back.end.notifier.repo.UserRepository;
import com.back.end.notifier.service.NotificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    private static final String NO_NOTIFICATION_FOUND_BY_ID_TEMPLATE = "No notification was found with id %s";

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<NotificationResponse> getNotificationsForUser(Long id) {
        return notificationRepository.findAllByUserId(id).stream()
                .map(NotificationMapper::notificationToNotificationResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponse getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NO_NOTIFICATION_FOUND_BY_ID_TEMPLATE, id)));
        return NotificationMapper.notificationToNotificationResponse(notification);
    }

    @Override
    public NotificationResponse createNotification(NotificationRequest notificationRequest, Long userId) {
        if (StringUtils.isEmpty(notificationRequest.getName()) && StringUtils.isEmpty(notificationRequest.getTitle())) {
            throw new BadRequestException("Both name and title cannot be empty.");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                "Could not find mentioned user to link him to notification. User id: " + userId));

        Notification notification = new Notification(null, notificationRequest.getTitle(), notificationRequest.getName(), user);
        Notification savedNotification = notificationRepository.save(notification);
        return NotificationMapper.notificationToNotificationResponse(savedNotification);
    }

    @Override
    public void deleteNotification(Long id) {
        try {
            notificationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(String.format(NO_NOTIFICATION_FOUND_BY_ID_TEMPLATE, id));
        }
    }

    @Override
    public NotificationResponse updateNotification(NotificationRequest notification, Long id) {
        Notification notificationEntity = notificationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NO_NOTIFICATION_FOUND_BY_ID_TEMPLATE, id)));
        notificationEntity.setName(notification.getName());
        notificationEntity.setTitle(notification.getTitle());
        return NotificationMapper.notificationToNotificationResponse(notificationRepository.save(notificationEntity));
    }
}
