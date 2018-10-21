package ua.epam.spring.hometask.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Before
    public void initUsersMap() {
        User user1 = new User(1L, "Yuliya", "Yuliya", "test@test.com");
        User user2 = new User(2L, "Yury", "Cesar", "testNew@test.com");
        userDao.save(user1);
        userDao.save(user2);
    }

    @Test
    public void getUserByEmailTest(){
        User user = userService.getUserByEmail("test@test.com");
        Assert.assertEquals(user.getEmail(),"test@test.com");
    }
}
