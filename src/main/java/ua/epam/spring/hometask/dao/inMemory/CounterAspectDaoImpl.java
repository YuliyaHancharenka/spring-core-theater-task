package ua.epam.spring.hometask.dao.inMemory;

import ua.epam.spring.hometask.dao.CounterAspectDao;
import ua.epam.spring.hometask.domain.statistics.CounterStatisticsEvent;

import java.util.HashMap;
import java.util.Map;

public class CounterAspectDaoImpl implements CounterAspectDao {

    private static Map<String, Long> eventsByNameStorage = new HashMap<>();
    private static Map<String, Long> eventsByPriceStorage = new HashMap<>();
    private static Map<String, Long> eventsByTicketsStorage = new HashMap<>();

    @Override
    public CounterStatisticsEvent getStatisticsById(Long id) {
        return null;
    }

    @Override
    public void updateStatistics(CounterStatisticsEvent cointerStatisticsEvent) {

    }

    @Override
    public void insertStatistics(CounterStatisticsEvent cointerStatisticsEvent) {

    }

    @Override
    public void saveEventValueCouterByName(String eventName) {
        if (eventsByNameStorage.containsKey(eventName)) {
            eventsByNameStorage.put(eventName, (eventsByNameStorage.get(eventName) + 1L));
        } else {
            eventsByNameStorage.put(eventName, 1L);
        }
    }


    @Override
    public Map<String, Long> eventsByNameStorageGetAll() {
        return eventsByNameStorage;
    }

    @Override
    public void saveEventValueCouterByPrice(String eventName) {
        if (eventsByPriceStorage.containsKey(eventName)) {
            eventsByPriceStorage.put(eventName, (eventsByPriceStorage.get(eventName) + 1L));
        } else {
            eventsByPriceStorage.put(eventName, 1L);
        }
    }

    @Override
    public Map<String, Long> eventsByPriceStorageGetAll() {
        return eventsByPriceStorage;
    }


    @Override
    public void saveEventValueCouterByTicket(String eventName) {
        if (eventsByTicketsStorage.containsKey(eventName)) {
            eventsByTicketsStorage.put(eventName, (eventsByTicketsStorage.get(eventName) + 1L));
        } else {
            eventsByTicketsStorage.put(eventName, 1L);
        }
    }

    @Override
    public Map<String, Long> eventsByTicketsStorageGetAll() {
        return eventsByTicketsStorage;
    }
}
