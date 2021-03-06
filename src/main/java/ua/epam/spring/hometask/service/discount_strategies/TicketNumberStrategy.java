package ua.epam.spring.hometask.service.discount_strategies;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;

public class TicketNumberStrategy implements DiscountStrategy {

    private byte discount;

    public TicketNumberStrategy() {
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    @Override
    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        long quantity = (numberOfTickets / 10);
        return (byte) (quantity * discount / numberOfTickets);
    }
}
