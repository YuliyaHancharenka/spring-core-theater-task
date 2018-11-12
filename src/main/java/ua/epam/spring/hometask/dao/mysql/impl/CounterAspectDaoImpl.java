package ua.epam.spring.hometask.dao.mysql.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.CounterAspectDao;
import ua.epam.spring.hometask.dao.mysql.mappers.CounterStatisticsEventMapper;
import ua.epam.spring.hometask.domain.statistics.CounterStatisticsEvent;

import java.util.List;
import java.util.Map;

@Repository
public class CounterAspectDaoImpl implements CounterAspectDao {

	private static final String SELECT_EVENT_STATISTICS_BY_ID = "SELECT * FROM statistics_event WHERE event_id = ?";
    private static final String UPDATE_EVENT_STATISTICS_BY_ID = "UPDATE statistics_event SET number_access_by_name = ?, number_access_by_price = ?, number_of_booked_tickets = ? WHERE event_id = ?";
    private static final String INSERT_EVENT_STATISTICS_BY_ID = "INSERT INTO statistics_event (number_access_by_name, number_access_by_price, number_of_booked_tickets, event_id) VALUES (?, ?, ?, ?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CounterStatisticsEvent getStatisticsById(Long id) {
		List<CounterStatisticsEvent> statisticsEvent = null;
		try {
			statisticsEvent = jdbcTemplate.query(SELECT_EVENT_STATISTICS_BY_ID, new Object[] {id}, new CounterStatisticsEventMapper());
		} catch(DataAccessException e) {
			String errorMsg = String.format("Unable to obtain event statistics from DB by id: ", id);
			System.out.println(errorMsg);
		}
		if (!statisticsEvent.isEmpty()) {
			statisticsEvent.get(0);
		}
		return null;
	}

	@Override
	public void updateStatistics(CounterStatisticsEvent counterStatisticsEvent) {
		try {
			jdbcTemplate.update(UPDATE_EVENT_STATISTICS_BY_ID, 
					counterStatisticsEvent.getNumberOfAccessByName(), counterStatisticsEvent.getNumberOfAccessByPrice(),
					counterStatisticsEvent.getNumberOfAccessBookedTickets(), counterStatisticsEvent.getId());
		} catch (DataAccessException e) {
			String errorMsg = String.format("Unable to update event statistics from DB by id: ", counterStatisticsEvent.getId());
			System.out.println(errorMsg);
		}	
	}

	@Override
	public void insertStatistics(CounterStatisticsEvent counterStatisticsEvent) {
		try {
			jdbcTemplate.update(INSERT_EVENT_STATISTICS_BY_ID, 
					counterStatisticsEvent.getNumberOfAccessByName(), counterStatisticsEvent.getNumberOfAccessByPrice(),
					counterStatisticsEvent.getNumberOfAccessBookedTickets(), counterStatisticsEvent.getId());
		} catch (DataAccessException e) {
			String errorMsg = String.format("Unable to insert event statistics from DB by id: ", counterStatisticsEvent.getId());
			System.out.println(errorMsg);
		}	
	}

	@Override
	public void saveEventValueCouterByName(String eventName) {

	}

	@Override
	public Map<String, Long> eventsByNameStorageGetAll() {
		return null;
	}

	@Override
	public Map<String, Long> eventsByPriceStorageGetAll() {
		return null;
	}

	@Override
	public void saveEventValueCouterByPrice(String eventName) {

	}

	@Override
	public void saveEventValueCouterByTicket(String eventName) {

	}

	@Override
	public Map<String, Long> eventsByTicketsStorageGetAll() {
		return null;
	}
}
