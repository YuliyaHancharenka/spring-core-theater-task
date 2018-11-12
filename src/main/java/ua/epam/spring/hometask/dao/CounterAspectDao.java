package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.statistics.CounterStatisticsEvent;

import java.util.Map;

public interface CounterAspectDao {

    public CounterStatisticsEvent getStatisticsById(Long id);
    public void updateStatistics(CounterStatisticsEvent cointerStatisticsEvent);
    public void insertStatistics(CounterStatisticsEvent cointerStatisticsEvent);

    void saveEventValueCouterByName(String eventName);

    Map<String, Long> eventsByNameStorageGetAll();

    Map<String, Long> eventsByPriceStorageGetAll();

    void saveEventValueCouterByPrice(String eventName);

    void saveEventValueCouterByTicket(String eventName);

    Map<String, Long> eventsByTicketsStorageGetAll();
}
