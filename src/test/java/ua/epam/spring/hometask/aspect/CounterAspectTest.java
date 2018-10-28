package ua.epam.spring.hometask.aspect;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.dao.CounterAspectDao;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;

import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring.xml"})
public class CounterAspectTest {

    @Autowired
    EventService eventService;

    @Autowired
    BookingService bookingService;

    @Autowired
    CounterAspectDao counterAspectDao;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    EventDao eventDao;

    private Event currentEvent;
    private LocalDateTime dateTime;
    private User currentUser;
    private Set<Long> seats;
    private Ticket ticket1;
    private Ticket ticket2;


    @Before
    public  void init() {
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
        ticket2.setSeat(20L);
        ticket1.setDateTime(dateTime);
        ticket2.setDateTime(LocalDateTime.now().plusDays(1));
        ticket1.setEvent(currentEvent);
        ticket2.setEvent(currentEvent);

        ticketDao.save(ticket1);
        ticketDao.save(ticket2);
        currentUser = new User(1L, "Yuliya", "Yuliya", "test@test.com");
        seats = new HashSet<>(Arrays.asList(10L, 30L));

    }
    @After
    public void tearDown() {
        ticketDao.remove(ticket1);
        ticketDao.remove(ticket2);
    }


    @Test
    public void saveEventAccessedByNameNumberTest(){
        eventService.getByName("New_Year_Party");
        eventService.getByName("New_Year_Party");

        Long result  = counterAspectDao.eventsByNameStorageGetAll().get("New_Year_Party");
        Assert.assertTrue(result.equals(2L));
    }


    @Test
    public void saveEventQueriedByPriceNumberTest(){
        bookingService.getTicketsPrice(currentEvent, dateTime, currentUser, seats);
        bookingService.getTicketsPrice(currentEvent, dateTime, currentUser, seats);
        bookingService.getTicketsPrice(currentEvent, dateTime, currentUser, seats);

        Long result  = counterAspectDao.eventsByPriceStorageGetAll().get("New_Year_Party") ;
        Assert.assertTrue(result.equals(3L));
    }

    @Test
    public void saveEventBookedTicketNumberTest(){
        Set<Ticket> ticketsList = new HashSet<>();
        ticketsList.add(ticket1);
        ticketsList.add(ticket2);

        bookingService.bookTickets(ticketsList);

        ticketDao.remove(ticket1);
        ticketDao.remove(ticket2);

        Long result  = counterAspectDao.eventsByTicketsStorageGetAll().get("New_Year_Party") ;
        Assert.assertTrue(result.equals(2L));

    }


}
