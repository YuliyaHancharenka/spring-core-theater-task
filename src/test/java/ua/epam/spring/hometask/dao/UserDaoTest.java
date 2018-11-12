package ua.epam.spring.hometask.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class UserDaoTest extends AbstractJUnit4SpringContextTests {

    private User currentUser;

    @Autowired
    UserDao userDao;

    @Before
    public void initUsersMap() {
        User user1 = new User(1L, "Yuliya", "Yuliya", "test@test.com");
        User user2 = new User(2L, "Yury", "Cesar", "testNew@test.com");
        currentUser = user1;
        userDao.save(user1);
        userDao.save(user2);
    }

    @Test
    public void getUserByEmailTest() {
        User user = userDao.getUserByEmail("test@test.com");
        Assert.assertEquals(user, currentUser);
    }

    @Test
    public void getUserByIdTest() {
        User user = userDao.getById(1L);
        Assert.assertEquals(user, currentUser);
    }

    @Test
    public void getAllTest() {
        Collection<User> users = userDao.getAll();
        Assert.assertNotNull(users);
    }
}
