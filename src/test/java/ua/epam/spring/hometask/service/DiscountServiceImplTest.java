package ua.epam.spring.hometask.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-test.xml"})
public class DiscountServiceImplTest {

    @Autowired
    UserDao userDao;

    @Autowired
    EventService eventService;

    @Autowired
    DiscountService discountService;

    private LocalDateTime airDateTime;
    private Event event;
    private User user1;

    @Before
    public void init() {
        user1 = new User(1L, "Yuliya", "Hancharenka", "test@test.com");
        User user2 = new User(2L, "Yury", "Cesar", "testNew@test.com");
        airDateTime = LocalDateTime.of(1992, Month.MAY, 1, 0, 0);
        user1.setBirthday(airDateTime);
        user2.setBirthday(airDateTime);
        userDao.save(user1);
        userDao.save(user2);
        event = eventService.getByName("New_Year_Party");
    }

    @Test
    public void getDiscount() {
        byte actualDiscount = discountService.getDiscount(user1, event, airDateTime, 99);
        Assert.assertEquals(4, actualDiscount);
    }
}