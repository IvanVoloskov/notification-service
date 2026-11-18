package ru.practicum.notification.strategy;

import ru.practicum.notification.event.TaskEvent;
import ru.practicum.notification.event.TaskEventType;

/**
 * Паттерн «Стратегия»: один интерфейс — несколько взаимозаменяемых алгоритмов.
 * Каждая реализация отвечает за свой тип события.
 */
public interface NotificationStrategy {

    TaskEventType supportedType();

    void notify(TaskEvent event);
}
