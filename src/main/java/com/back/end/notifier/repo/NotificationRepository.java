package com.back.end.notifier.repo;

import com.back.end.notifier.entity.Notification;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

    List<Notification> findAllByUserId(Long userId);
}
