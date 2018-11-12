package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.statistics.DiscountStatistics;

import java.util.Map;

public interface DiscountAspectDao {

    public DiscountStatistics getStatisticsById(Long id);
    public void updateStatistics(DiscountStatistics cointerStatisticsEvent);
    public void insertStatistics(DiscountStatistics discountStatistics);

   void count(Class clazz);

    Map<Class<?>, Long> getDiscountCounterStorage();
}
