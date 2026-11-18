package ru.practicum.notification.strategy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.notification.event.TaskEvent;
import ru.practicum.notification.event.TaskEventType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Контекст паттерна: выбирает стратегию по типу события.
 * Spring сам собирает ВСЕ бины NotificationStrategy в список, поэтому
 * новая стратегия подключается добавлением класса, без правок диспетчера (принцип открыт/закрыт).
 */
@Slf4j
@Component
public class NotificationDispatcher {

    private final Map<TaskEventType, NotificationStrategy> strategies = new EnumMap<>(TaskEventType.class);

    public NotificationDispatcher(List<NotificationStrategy> strategyList) {
        for (NotificationStrategy strategy : strategyList) {
            var previous = strategies.put(strategy.supportedType(), strategy);
            if (previous != null) {
                // Две стратегии на один тип — ошибка конфигурации, лучше упасть при старте
                throw new IllegalStateException("Несколько стратегий для типа " + strategy.supportedType());
            }
        }
    }

    public void dispatch(TaskEvent event) {
        var strategy = strategies.get(event.eventType());
        if (strategy == null) {
            log.warn("Нет стратегии для события {}", event.eventType());
            return;
        }
        strategy.notify(event);
    }
}
