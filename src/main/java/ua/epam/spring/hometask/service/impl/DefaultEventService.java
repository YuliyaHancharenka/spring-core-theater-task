package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service
public class DefaultEventService implements EventService {

    @Autowired
    private EventDao eventDao;

    public DefaultEventService() {
    }

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
       return eventDao.getByName(name);
    }

    @Override
    public Event save(@Nonnull Event object) {
        eventDao.save(object);
        return object;
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDao.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDao.getAll();
    }
}
