package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EventDaoInMemory implements EventDao {

    private static List<Event> eventList = new ArrayList<>();

    public EventDaoInMemory() {
    }

    public static List<Event> getEventList() {
        return eventList;
    }

    public static void setEventList(List<Event> eventList) {
        eventList = eventList;
    }

    @Override
    public Event save(@Nonnull Event event) {
        eventList.add(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        eventList.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        for (Event event : eventList) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public Collection<Event> getAll() {
        return eventList;
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventList.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
