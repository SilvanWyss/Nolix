//package declaration
package ch.nolix.common.util;

//Java import
import java.util.GregorianCalendar;

//own imports
import ch.nolix.common.constants.TimeUnitManager;
import ch.nolix.common.exception.Argument;
import ch.nolix.common.exception.ErrorPredicate;
import ch.nolix.common.exception.InvalidArgumentException;
import ch.nolix.common.interfaces.Freezable;
import ch.nolix.common.interfaces.Resettable;

//class
/**
 * A time stores a point in time with a precision of 1 millisecond.
 * A time stores a point in time according to the Gregorian calendar.
 * Since the Gregorian calendar is not proleptic a time can only store a point in time after 1600-01-01 00:00:000.
 * Not proleptic means that the Greogioan calender does officially not allow calculations backwards behind the point of its release, that was in 1582.
 * A time can be freezed so that it cannot be changed anymore.
 * Technically, a time is a wrapper around JDK's Gregorian calendar class.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 590
 */
public final class Time implements Freezable, Resettable {
	
	//default values
	public static final int DEFAULT_YEAR = 2000;
	public static final int DEFAULT_MONTH_OF_YEAR = 1;
	public static final int DEFAULT_DAY_OF_MONTH = 0;
	public static final int DEFAULT_HOUR_OF_DAY = 0;
	public static final int DEFAULT_MINUTE_OF_HOUR = 0;
	public static final int DEFAULT_SECOND_OF_MINUTE = 0;
	public static final int DEFAULT_MILLISECOND_OF_SECOND = 0;
	
	//attributes
	private final GregorianCalendar time = new GregorianCalendar();
	private boolean frozen = false;
	
	//static method
	/**
	 * @return a new time that represents the current time on the machine it is created on.
	 */
	public static Time createCurrentTime() {
		final Time time = new Time();
		time.time.setTimeInMillis(new GregorianCalendar().getTimeInMillis());
		return time;
	}
	
	//static method
	/**
	 * @param unixTimeStamp
	 * @return a new time from the given unix time stamp.
	 */
	public static Time createTimeFromUnixTimeStamp(final long unixTimeStamp) {
		final Time time = new Time();
		time.time.setTimeInMillis(1000 * unixTimeStamp);
		return time;
	}
	
	//constructor
	/**
	 * Creates new time with default values.
	 */
	public Time() {
		time.setLenient(true);
		reset();
	}

	//constructor
	/**
	 * Creates new time with the given year, month of year and day of month.
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 */
	public Time(
		final int year,
		final int monthOfYear,
		final int dayOfMonth
	) {
		
		//Calls other constructor.
		this();
		
		setYear(year);
		setMonthOfYear(monthOfYear);
		setDayOfMonth(dayOfMonth);
	}
	
	//constructor
	/**
	 * Creates new time with the given year, month of year, day of month, hour of day and minute of hour.
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 * @param hourOfDay
	 * @param minuteOfHour
	 */
	public Time(
		final int year,
		final int monthOfYear,
		final int dayOfMonth,
		final int hourOfDay,
		final int minuteOfHour
	) {
		
		//Calls other constructor.
		this(year, monthOfYear, dayOfMonth);

		setHourOfDay(hourOfDay);
		setMinuteOfHour(minuteOfHour);
	}
	
	//constructor
	/**
	 * Creates new time with the given year, month of year, day of month, hour of day, minute of hour and second and of minute.
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 * @param hourOfDay
	 * @param minuteOfHour
	 * @param secondOfMinute
	 */
	public Time(
		final int year,
		final int monthOfYear,
		final int dayOfMonth,
		final int hourOfDay,
		final int minuteOfHour,
		final int secondOfMinute
	) {
		
		//Calls other constructor.
		this(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour);
		
		setSecondOfMinute(secondOfMinute);
	}
	
	//constructor
	/**
	 * Creates new time with the given year, month of year, day of month, hour of day, minute of hour, second and of minute and millisecond of second.
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 * @param hourOfDay
	 * @param minuteOfHour
	 * @param secondOfMinute
	 * @param millisecondOfSecond
	 */
	public Time(
		final int year,
		final int monthOfYear,
		final int dayOfMonth,
		final int hourOfDay,
		final int minuteOfHour,
		final int secondOfMinute,
		final int millisecondOfSecond
	) {
		
		//Calls other constructor.
		this(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
		
		setMillisecondOfSecond(millisecondOfSecond);
	}
	
	//constructor
	/**
	 * Creates new time the given string represents.
	 * 
	 * @param string
	 */
	public Time(final String string) {
		
		//Calls other constructor.
		this();
		
		//Creates array of values of the given string.
		//TODO
		final String[] array = string.split("-");
		
		//Checks if the array has a valid number of values.
		//TODO: Add method to zeta validator.
		//ZetaValidator.supposeThat(array.length).thatIsNamed("number of values of '" + string + "'").isBetween(3, 6).andIsNot(4);
		
		if (array.length >= 3) {
			setYear(Integer.valueOf(array[0]));
			setMonthOfYear(Integer.valueOf(array[1]));
			setDayOfMonth(Integer.valueOf(array[2]));
		}
		if (array.length >= 5) {
			setMinuteOfHour(Integer.valueOf(array[4]));
		}
		if (array.length >= 6) {
			setSecondOfMinute(Integer.valueOf(array[5]));
		}
		if (array.length >= 7) {
			setMillisecondOfSecond(Integer.valueOf(array[6]));
		}
	}
	
	/**
	 * Adds the given milliseconds to this time.
	 * 
	 * @param milliseconds
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time addMilliseconds(final int milliseconds) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.add(GregorianCalendar.MILLISECOND, milliseconds);
		
		return this;
	}

	//method
	/**
	 * Adds the given minutes to this time.
	 * 
	 * @param minutes
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time addMinutes(final int minutes) {
		return addMilliseconds(TimeUnitManager.MILLISECONDS_PER_MINUTE * minutes);
	}
	
	//method
	/**
	 * Adds the given seconds to this time.
	 * 
	 * @param seconds
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time addSeconds(final int seconds) {
		return addMilliseconds(TimeUnitManager.MILLISECONDS_PER_SECOND * seconds);
	}
	
	//method
	/**
	 * @param object
	 * @return true if this time equals the given object.
	 */
	public boolean equals(final Object object) {
		
		//Handles the case if the given object is no time.
		if (!(object instanceof Time)) {
			return false;
		}
		
		//Handles the case if the given object is a time.
		final Time time = (Time)object;
		return (
			getYear() == time.getYear()
			&& getMonthOfYear() == time.getMonthOfYear()
			&& getDayOfMonth() == time.getDayOfMonth()
			&& getHourOfDay() == time.getHourOfDay()
			&& getMinuteOfHour() == time.getMinuteOfHour()
			&& getSecondOfMinute() == time.getSecondOfMinute()
			&& getMillisecondOfSecond() == time.getMillisecondOfSecond()
		);
	}
	
	//method
	/**
	 * Freezes this time.
	 */
	public void freeze() {
		frozen = true;
	}
	
	//method
	/**
	 * @return a copy of this time.
	 */
	public Time getCopy() {
		
		final Time time = new Time();
		time.setYear(getYear());
		time.setMonthOfYear(getMonthOfYear());
		time.setDayOfMonth(getDayOfMonth());
		time.setHourOfDay(getHourOfDay());
		time.setMinuteOfHour(getMinuteOfHour());
		time.setSecondOfMinute(getSecondOfMinute());
		time.setMillisecondOfSecond(getMillisecondOfSecond());
		
		return time;
	}
	
	//method
	/**
	 * @return the day of the month of this time.
	 */
	public int getDayOfMonth() {
		return time.get(GregorianCalendar.DAY_OF_MONTH);
	}
	
	//method
	/**
	 * This method returns a negative value if this time is after the given time.
	 * 
	 * @param time
	 * @return the number of days from this time to the given time.
	 */
	public int getDaysTo(final Time time) {
		return (int)(getMillisecondsTo(time) / TimeUnitManager.MILLISECONDS_PER_DAY);
	}

	//method
	/**
	 * @return the hour of the month of this time.
	 */
	public int getHourOfDay() {
		return time.get(GregorianCalendar.HOUR_OF_DAY);
	}
	
	//method
	/**
	 * @return the millisecond of the second of this time.
	 */
	public int getMillisecondOfSecond() {
		return time.get(GregorianCalendar.MILLISECOND);
	}
	
	//method
	/**
	 * @return the milliseconds of this time.
	 */
	public long getMilliseconds() {
		return time.getTimeInMillis();
	}
	
	//method
	/**
	 * This method returns a negative value if this time is after the given time.
	 * 
	 * @param time
	 * @return the number of milliseconds from this time to the given time.
	 */
	public long getMillisecondsTo(final Time time) {
		return (time.getMilliseconds() - this.getMilliseconds());
	}
	
	//method
	/**
	 * @return the minute of the hour of this time.
	 */
	public int getMinuteOfHour() {
		return time.get(GregorianCalendar.MINUTE);
	}
	
	//method
	/**
	 * @return the month of the year of this time.
	 */
	public int getMonthOfYear() {
		return (time.get(GregorianCalendar.MONTH) + 1);
	}
	
	//method
	/**
	 * @return the second of the minute of this time.
	 */
	public int getSecondOfMinute() {
		return time.get(GregorianCalendar.SECOND);
	}
	
	//method
	/**
	 * @return the year of this time.
	 */
	public int getYear() {
		return time.get(GregorianCalendar.YEAR);
	}
	
	//method
	/**
	 * @return true if this time is frozen.
	 */
	public boolean isFrozen() {
		return frozen;
	}
	
	//method
	/**
	 * @param time
	 * @return true if this time is after the given time.
	 */
	public boolean isAfter(final Time time) {
		return (getMilliseconds() > time.getMilliseconds());
	}
	
	//method
	/**
	 * @param time
	 * @return true if this time is before the given time.
	 */
	public boolean isBefore(final Time time) {
		return (getMilliseconds() < time.getMilliseconds());
	}
	
	//method
	/**
	 * @return true if this time is in a leap year.
	 */
	public boolean isInLeapYear() {
		return ((getYear() % 4 == 0 && getYear() % 100 != 0) || getYear() % 400 == 0);
	}
	
	//method
	/**
	 * Resets this time.
	 * 
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public void reset() {		
		setYear(DEFAULT_YEAR);
		setMonthOfYear(DEFAULT_MONTH_OF_YEAR);
		setDayOfMonth(DEFAULT_DAY_OF_MONTH);
		setHourOfDay(DEFAULT_HOUR_OF_DAY);
		setMinuteOfHour(DEFAULT_MINUTE_OF_HOUR);
		setSecondOfMinute(DEFAULT_SECOND_OF_MINUTE);
		setMillisecondOfSecond(DEFAULT_MILLISECOND_OF_SECOND);
	}
	
	//method
	/**
	 * Sets the day of the month of this time.
	 * 
	 * @param dayOfMonth
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setDayOfMonth(final int dayOfMonth) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
		
		return this;
	}
	
	//method
	/**
	 * Sets the hour of the day of this time.
	 * 
	 * @param hourOfDay
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setHourOfDay(final int hourOfDay) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
		
		return this;
	}
	
	//method
	/**
	 * Sets the millisecond of the second of this time.
	 * 
	 * @param millisecondOfSecond
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setMillisecondOfSecond(final int millisecondOfSecond) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.set(GregorianCalendar.MILLISECOND, millisecondOfSecond);
		
		return this;
	}
	
	//method
	/**
	 * Sets the minute of the hour of this time
	 * 
	 * @param minuteOfHour
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setMinuteOfHour(final int minuteOfHour) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
			
		time.set(GregorianCalendar.MINUTE, minuteOfHour);
		
		return this;
	}
	
	//method
	/**
	 * Sets the month of the year of this time.
	 * 
	 * @param monthOfYear
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setMonthOfYear(final int monthOfYear) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.set(GregorianCalendar.MONTH, monthOfYear - 1);
		
		return this;
	}
	
	//method
	/**
	 * Sets the second of the minute of this time.
	 * 
	 * @param secondOfMinute
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setSecondOfMinute(final int secondOfMinute) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.set(GregorianCalendar.SECOND, secondOfMinute);
		
		return this;
	}
	
	//method
	/**
	 * Sets the year of this time.
	 * 
	 * @param year
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	public Time setYear(final int year) {
		
		//Checks if this time is frozen.
		throwsExceptionIfFrozen();
		
		time.set(GregorianCalendar.YEAR, year);
		
		return this;
	}
	
	//method
	/**
	 * @return a string representation of this time.
	 */
	public String toString() {
		return String.format(
			"%04d-%02d-%02d-%02d-%02d-%02d-%03d",
			getYear(),
			getMonthOfYear(),
			getDayOfMonth(),
			getHourOfDay(),
			getMinuteOfHour(),
			getSecondOfMinute(),
			getMillisecondOfSecond()
		);
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	private void throwsExceptionIfFrozen() {
		if (frozen) {
			new InvalidArgumentException(
				new Argument(this),
				new ErrorPredicate("is frozen")
			);
		}
	}
}
