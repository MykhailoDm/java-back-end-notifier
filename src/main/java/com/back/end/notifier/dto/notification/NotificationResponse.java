package com.back.end.notifier.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotificationResponse {

    private final Long id;

    private final String title;

    private final String name;
}
