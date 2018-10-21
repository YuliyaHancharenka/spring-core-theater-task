package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author Yuriy_Tkach
 */
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao userDao;

    public DefaultUserService() {
    }

    @Override
    public @Nullable User getUserByEmail(@Nonnull String email){
        return userDao.getUserByEmail(email);
    }

    @Override
    public User save(@Nonnull User object) {
        return userDao.save(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        userDao.remove(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }

}
