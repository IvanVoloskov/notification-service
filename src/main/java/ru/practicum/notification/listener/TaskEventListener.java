package ru.practicum.notification.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.practicum.notification.event.TaskEvent;
import ru.practicum.notification.strategy.NotificationDispatcher;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskEventListener {

    private final NotificationDispatcher dispatcher;

    @KafkaListener(topics = "${app.kafka.topic.task-events}")
    public void onTaskEvent(@Payload TaskEvent event,
                            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Получено событие {} (partition={}, offset={})", event.eventType(), partition, offset);

        dispatcher.dispatch(event);
    }
}