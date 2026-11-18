package ru.practicum.notification.event;

import java.time.Instant;

public record TaskEvent(
        TaskEventType eventType,
        Long taskId,
        String title,
        String status,
        Long userId,
        Long categoryId,
        Instant occurredAt
) {
}
