package ua.epam.spring.hometask.service.discount_strategies;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public interface DiscountStrategy {

    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets);
}
