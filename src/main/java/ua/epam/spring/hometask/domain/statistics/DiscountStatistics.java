package ua.epam.spring.hometask.domain.statistics;

import ua.epam.spring.hometask.domain.DomainObject;

public class DiscountStatistics extends DomainObject {

	private Long birthdayDiscount;
	private Long luckyDiscount;
	
	public DiscountStatistics() {
		this.birthdayDiscount = 0l;
		this.luckyDiscount = 0l;
	}
	
	public Long getBirthdayDiscount() {
		return birthdayDiscount;
	}
	
	public void setBirthdayDiscount(Long birthdayDiscount) {
		this.birthdayDiscount = birthdayDiscount;
	}
	
	public Long getLuckyDiscount() {
		return luckyDiscount;
	}
	
	public void setLuckyDiscount(Long luckyDiscount) {
		this.luckyDiscount = luckyDiscount;
	}
	
	public void increaseNumberOfLuckyDiscount() {
		++luckyDiscount;
	}
	
	public void increaseNumberOfBirthdayDiscount() {
		++birthdayDiscount;
	}
}
