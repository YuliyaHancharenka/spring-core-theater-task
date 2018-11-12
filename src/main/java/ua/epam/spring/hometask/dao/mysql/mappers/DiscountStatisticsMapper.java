package ua.epam.spring.hometask.dao.mysql.mappers;

import org.springframework.jdbc.core.RowMapper;
import ua.epam.spring.hometask.domain.statistics.DiscountStatistics;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiscountStatisticsMapper implements RowMapper<DiscountStatistics> {

	private static final String USER_ID = "user_id";
	private static final String BIRTHDAY_DISCOUNT = "birthday_discount";
	private static final String LUCKY_DISCOUNT = "lucky_discount";
	
	@Override
	public DiscountStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
		DiscountStatistics discountStatistics = new DiscountStatistics();
		discountStatistics.setId(rs.getLong(USER_ID));
		discountStatistics.setBirthdayDiscount(rs.getLong(BIRTHDAY_DISCOUNT));
		discountStatistics.setLuckyDiscount(rs.getLong(LUCKY_DISCOUNT));
		return discountStatistics;
	}
}
