package ua.epam.spring.hometask.dao.mysql.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.statistics.CounterStatisticsEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterStatisticsEventMapper implements RowMapper<CounterStatisticsEvent> {

	private static final String EVENT_ID = "event_id";
	private static final String ACCESS_BY_NAME = "number_access_by_name";
	private static final String ACCESS_BY_PRICE = "number_access_by_price";
	private static final String NUMBER_OF_BOOKED_TICKETS = "number_of_booked_tickets";

	@Override
	public CounterStatisticsEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		CounterStatisticsEvent statisticsEvent = new CounterStatisticsEvent();
		statisticsEvent.setId(rs.getLong(EVENT_ID));
		statisticsEvent.setNumberOfAccessByName(rs.getLong(ACCESS_BY_NAME));
		statisticsEvent.setNumberOfAccessByPrice(rs.getLong(ACCESS_BY_PRICE));
		statisticsEvent.setNumberOfAccessBookedTickets(rs.getLong(NUMBER_OF_BOOKED_TICKETS));
		return statisticsEvent;
	}
}
