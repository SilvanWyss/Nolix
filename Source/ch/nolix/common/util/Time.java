/*
 * file:	Time.java
 * author:	Silvan Wyss
 * month:	2016-08
 * lines:	10
 */

//package declaration
package ch.nolix.common.util;

//class
/**
 * A time stores a point in time with a precision of 1 millisecond.
 * A time stores a point in time according to the Gregorian calendar.
 * Since the Gregorian calendar is not proleptic (does officialy not allow calculations backwards from the point of its release, that was in 1582) a time can only store a point in time after 1600-01-01 00:00:000.
 * A time can be freezed so that it cannot be changed anymore.
 */
public final class Time {

	//attributes
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;
	private int millisecond;
	
	public Time(String string) {
		
		String[] stringArray = string.split("-");
		
		setYear(Integer.valueOf(stringArray[0]));
		setMonth(Integer.valueOf(stringArray[1]));
		setDay(Integer.valueOf(stringArray[2]));
	}
	
	public Time(int year, int month, int day) {
		setYear(year);
		setMonth(month);
		setDay(day);
	}
	
	public final int getDay() {
		return day;
	}
	
	public final int getHour() {
		return hour;
	}
	
	public final int getMillisecond() {
		return millisecond;
	}
	
	public final int getMinute() {
		return minute;
	}
	
	public final int getMonth() {
		return month;
	}
	
	public final int getSecond() {
		return second;
	}
	
	public final int getYear() {
		return year;
	}
	
	public final boolean isInLeapYear() {
		return ((getYear() % 4 == 0 && getYear() % 100 != 0) || getYear() % 400 == 0);
	}
	
	public final Time setDay(final int day) {

		switch (getMonth()) {
			case 1:
				setDayOf31DaysMonth(day);
				break;
			case 2:
				setDayOfFebruary(day);
				break;
			case 3:
				setDayOf31DaysMonth(day);
				break;
			case 4:
				setDayOf30DaysMonth(day);
				break;
			case 5:
				setDayOf31DaysMonth(day);
				break;
			case 6:
				setDayOf30DaysMonth(day);
				break;
			case 7:
				setDayOf31DaysMonth(day);
				break;
			case 8:
				setDayOf31DaysMonth(day);
				break;
			case 9:
				setDayOf30DaysMonth(day);
				break;
			case 10:
				setDayOf31DaysMonth(day);
				break;
			case 11:
				setDayOf30DaysMonth(day);
				break;
			case 12:
				setDayOf31DaysMonth(day);
				break;
		}
		
		return this;
	}
	
	public final Time setMinute(final int minute) {
		
		//Checks the given minute.
		Validator.throwExceptionIfValueIsNotInRange("minute", 0, 59, minute);
		
		this.minute = minute;
		
		return this;
	}
	
	public final Time setMonth(final int month) {
		
		//Checks the given month.
		Validator.throwExceptionIfValueIsNotInRange("month", 1, 12, month);
		
		this.month = month;
		
		return this;
	}
	
	public final Time setYear(final int year) {
		
		//Checks the given year.
		Validator.throwExceptionIfValueIsSmaller("year", 1600, year);
		
		this.year = year;
		
		return this;
	}
	
	public final String toString() {
		return String.format(
			"%04d-%02d-%02d",
			getYear(),
			getMonth(),
			getDay()
		);
	}
	
	private final void setDayOf30DaysMonth(final int day) {
		
		//Checks the given day.
		Validator.throwExceptionIfValueIsNotInRange("day", 1, 30, day);
		
		this.day = day;
	}
	
	private final void setDayOf31DaysMonth(final int day) {
		
		//Checks the given day.
		Validator.throwExceptionIfValueIsNotInRange("day", 1, 31, day);
		
		this.day = day;
	}
	
	private final void setDayOfFebruary(final int day) {
		
		//Checks the given day.
		if (isInLeapYear()) {
			Validator.throwExceptionIfValueIsNotInRange("day", 1, 29, day);
		}
		else {
			Validator.throwExceptionIfValueIsNotInRange("day", 1, 28, day);
		}
		
		this.day = day;
	}
}
