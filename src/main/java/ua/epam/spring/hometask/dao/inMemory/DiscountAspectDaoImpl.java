package ua.epam.spring.hometask.dao.inMemory;

import ua.epam.spring.hometask.dao.DiscountAspectDao;
import ua.epam.spring.hometask.domain.statistics.DiscountStatistics;

import java.util.HashMap;
import java.util.Map;


public class DiscountAspectDaoImpl implements DiscountAspectDao {

    private Map<Class<?>, Long> discountCounterStorage = new HashMap<>();


    @Override
    public DiscountStatistics getStatisticsById(Long id) {
        return null;
    }

    @Override
    public void updateStatistics(DiscountStatistics cointerStatisticsEvent) {

    }

    @Override
    public void insertStatistics(DiscountStatistics discountStatistics) {

    }

    @Override
    public void count(Class clazz) {
        if (!discountCounterStorage.containsKey(clazz)) {
            discountCounterStorage.put(clazz, 0L);
        }
        discountCounterStorage.put(clazz, discountCounterStorage.get(clazz) + 1L);
    }


    public Map<Class<?>, Long> getDiscountCounterStorage() {
        return discountCounterStorage;
    }
}
