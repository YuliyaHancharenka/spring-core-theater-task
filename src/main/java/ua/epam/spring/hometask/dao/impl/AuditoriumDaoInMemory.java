package ua.epam.spring.hometask.dao.impl;

import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class AuditoriumDaoInMemory implements AuditoriumDao {

    private static Set<Auditorium> auditoriumSet = new HashSet<>();

    public AuditoriumDaoInMemory() {
    }

    public static void setAuditoriumSet(Set<Auditorium> auditoriumSet) {
        auditoriumSet = auditoriumSet;
    }

    @Override
    public Auditorium getByName(@Nonnull String name) {
        for (Auditorium auditorium : auditoriumSet) {
            if (auditorium.getName().equals(name)) {
                return auditorium;
            }
        }
        return null;
    }

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumSet;
    }

}
