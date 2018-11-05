package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class DefaultAuditoriumService implements AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDao;


    public DefaultAuditoriumService() {
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriumDao.getAll());
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        Collection<Auditorium> list = auditoriumDao.getAll();
        return list.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }
}
