package ua.epam.spring.hometask.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
@TestExecutionListeners()
public class BookingServiceTest {

    @Autowired
    @Qualifier(value = "eventDao")
    public EventDao eventDao;

    @Autowired
    public TicketDao ticketDao;

    @Autowired
    public BookingService bookingService;

    private Event currentEvent;
    private LocalDateTime dateTime;
    private User currentUser;
    private Set<Long> seats;
    private Ticket ticket1;
    private Ticket ticket2;

    @Before
    public void init() {
        List<Event> eventList = new ArrayList<>(eventDao.getAll());
        currentEvent = eventList.get(0);
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(currentEvent.getAirDates().iterator().next());
        dateTime = currentEvent.getAuditoriums().keySet().iterator().next();
        ticket1 = new Ticket();
        ticket2 = new Ticket();
        ticket1.setId(1L);
        ticket2.setId(2L);
        ticket1.setSeat(10L);
        ticket2.setSeat(50L);
        ticket1.setDateTime(dateTime);
        ticket2.setDateTime(LocalDateTime.now().plusDays(1));
        ticket1.setEvent(currentEvent);
        ticket2.setEvent(currentEvent);

        ticketDao.save(ticket1);
        ticketDao.save(ticket2);
        currentUser = new User(1L, "Yuliya", "Hancharenka", "test@test.com");
        seats = new HashSet<>(Arrays.asList(10L, 70L));
    }

    @After
    public void tearDown() {
        ticketDao.remove(ticket1);
        ticketDao.remove(ticket2);
    }

    @Test
    public void bookTicketsTest() {
        Set<Ticket> ticketsList = new HashSet<>();
        ticketsList.add(ticket1);
        ticketsList.add(ticket2);
        int beforeSize = ticketDao.getAll().size();
        bookingService.bookTickets(ticketsList);
        int afterSize = ticketDao.getAll().size();
        Assert.assertEquals(beforeSize, afterSize - ticketsList.size());

        ticketsList.remove(ticket1);
        ticketsList.remove(ticket2);
    }

    @Test
    public void getTicketsPriceTest() {
        double price = bookingService.getTicketsPrice(currentEvent, dateTime, currentUser, seats);
        Assert.assertEquals(price,359.64);
    }

    @Test
    public void getPurchasedTicketsForEventTest() {
        Set<Ticket> set = bookingService.getPurchasedTicketsForEvent(currentEvent, dateTime);
        Assert.assertNotNull(set);
    }
}
