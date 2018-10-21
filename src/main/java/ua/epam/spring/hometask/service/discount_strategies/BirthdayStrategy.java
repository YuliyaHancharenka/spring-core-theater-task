package ua.epam.spring.hometask.service.discount_strategies;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableSet;

public class BirthdayStrategy implements DiscountStrategy {

    private static byte discount = 0;

    private static int airDays = 0;

    public BirthdayStrategy() {
    }

    public static byte getDiscount() {
        return discount;
    }

    public static void setDiscount(byte discount) {
        BirthdayStrategy.discount = discount;
    }

    public static int getAirDays() {
        return airDays;
    }

    public static void setAirDays(int airDays) {
        BirthdayStrategy.airDays = airDays;
    }

    @Override
    public byte calculateDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        LocalDateTime birthday = user.getBirthday();
        LocalDate currentDay = LocalDate.now();
        LocalDate currentYearBirthday = LocalDate.of(currentDay.getYear(), birthday.getMonth(), birthday.getDayOfMonth());

        NavigableSet<LocalDateTime> airDates = event.getAirDates();
        for (LocalDateTime aDate : airDates) {
            if (currentYearBirthday.isAfter(aDate.toLocalDate())
                    && currentYearBirthday.isBefore(aDate.plusDays(airDays + 1).toLocalDate())) {
                return discount;
            }
        }
        return 0;
    }
}
