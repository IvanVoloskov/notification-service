package ru.practicum.notification.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.notification.event.TaskEvent;
import ru.practicum.notification.event.TaskEventType;

@Slf4j
@Component
public class TaskUpdatedStrategy implements NotificationStrategy {

    @Override
    public TaskEventType supportedType() {
        return TaskEventType.UPDATED;
    }

    @Override
    public void notify(TaskEvent event) {
        log.info("[УВЕДОМЛЕНИЕ] Задача {} \"{}\" обновлена, статус: {}",
                event.taskId(), event.title(), event.status());
    }
}
