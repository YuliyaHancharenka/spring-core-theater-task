package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.TicketDao;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NavigableMap;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultBookingService implements BookingService {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private EventDao eventDao;


    public DefaultBookingService() {
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        Event currentEvent = eventDao.getByName(event.getName());
        double basePrice = currentEvent.getBasePrice();
        EventRating rating = currentEvent.getRating();
        NavigableMap<LocalDateTime, Auditorium> auditoriums = currentEvent.getAuditoriums();
        Auditorium aud = auditoriums.get(dateTime);

        int counter = 0;
        Set<Long> vipSeats = aud.getVipSeats();
        for (Long vipSeat : vipSeats) {
            for (Long seat : seats) {
                if (vipSeat.equals(seat)) {
                    counter++;
                }
            }
        }

        double vipSeatCost = basePrice * 2;
        double hightRateSeatCost = 1.2;
        double resultCost;
        resultCost = (seats.size() - counter) * basePrice + counter * vipSeatCost;

        if (String.valueOf(rating).equals("HIGH")) {
            resultCost = hightRateSeatCost * resultCost;
        }
        DecimalFormat df2 = new DecimalFormat(".##");
        return Double.valueOf(df2.format(resultCost));
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        tickets.forEach(ticket -> ticketDao.save(ticket));
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        Collection<Ticket> ticketsList = ticketDao.getAll();
        Set<Ticket> tickets = ticketsList.stream().filter(t -> t.getEvent().equals(event)).collect(Collectors.toSet());
        return tickets.stream().filter(t -> t.getDateTime().equals(dateTime)).collect(Collectors.toSet());
    }
}
