package ua.epam.spring.hometask.aspect;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.DiscountAspectDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.EventService;
import ua.epam.spring.hometask.service.impl.DefaultDiscountService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-test.xml"})
public class DiscountAspectTest {

    @Autowired
    DiscountAspectDao discountAspectDao;

    @Autowired
    EventService eventService;

    @Autowired
    DiscountService discountService;


    @Before
    public void init() {
        User user1 = new User(1L, "Yuliya", "Yuliya", "test@test.com");
        User user2 = new User(2L, "Yury", "Cesar", "testNew@test.com");
        LocalDateTime airDateTime = LocalDateTime.of(1992, Month.NOVEMBER, 13, 0, 0);
        user1.setBirthday(airDateTime);
        user2.setBirthday(airDateTime);
        Event event = eventService.getByName("New_Year_Party");
        discountService.getDiscount(user1, event, airDateTime, 99);
        discountService.getDiscount(user1, event, airDateTime, 200);
        discountService.getDiscount(user2, event, airDateTime, 100);
    }

    @Test
    public void getDiscountTest() {
        Map<Class<?>, Long> map = discountAspectDao.getDiscountCounterStorage();
        Long result = map.get(DefaultDiscountService.class);
        Assert.assertTrue(result.equals(3L));
    }
}
