package ua.epam.spring.hometask.dao.inMemory;

import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class AuditoriumDaoInMemory implements AuditoriumDao {

    private Set<Auditorium> auditoriumSet = new HashSet<>();

    public AuditoriumDaoInMemory() {
    }

    public void setAuditoriumSet(Set<Auditorium> auditoriumSet) {
        this.auditoriumSet = auditoriumSet;
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
