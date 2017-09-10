//package declaration
package ch.nolix.element.basic;

//Java import
import java.util.GregorianCalendar;

//own imports
import ch.nolix.core.constants.TimeUnitCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A time stores a point in time with a precision of 1 millisecond.
 * A time stores a point in time according to the Gregorian calendar.
 * Since the Gregorian calendar is not proleptic a time can only store a point in time after 1600-01-01 00:00:000.
 * Not proleptic means that the Greogioan calender does officially not allow calculations backwards behind the point of its release, that was in 1582.
 * A time is not mutable.
 * Technically, a time is a wrapper around JDK's GregorianCalendar.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 560
 */
public final class Time extends Element {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Time";
	
	//default values
	public static final int DEFAULT_YEAR = 2000;
	public static final int DEFAULT_MONTH_OF_YEAR = 1;
	public static final int DEFAULT_DAY_OF_MONTH = 1;
	public static final int DEFAULT_HOUR_OF_DAY = 0;
	public static final int DEFAULT_MINUTE_OF_HOUR = 0;
	public static final int DEFAULT_SECOND_OF_MINUTE = 0;
	public static final int DEFAULT_MILLISECOND_OF_SECOND = 0;
	
	//attribute
	private final GregorianCalendar time = new GregorianCalendar();
	
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
	 * @param attributes
	 * @return a new time with the given attributes.
	 */
	public static Time createTime(final Iterable<StandardSpecification> attributes) {

		//Extracts the values.
		final String attribute = attributes.iterator().next().toString();
		
		return new Time(attribute);
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
	 * @throws NullArgumentException if the given string is null.
	 */
	public Time(final String string) {
		
		//Calls other constructor.
		this();
		
		//Checks if the given string is not null.
		Validator.supposeThat(string).thatIsNamed("string").isNotNull();
		
		//Creates array of values of the given string.
		final String[] array = string.split("-");
		
		Validator
		.supposeThat(array.length)
		.thatIsNamed("numer of values of '" + string + "'")
		.equalsAny(3, 5, 6, 7);
		
		if (array.length >= 3) {
			setYear(StringHelper.toInt(array[0]));
			setMonthOfYear(StringHelper.toInt(array[1]));
			setDayOfMonth(StringHelper.toInt(array[2]));
		}
		if (array.length >= 5) {
			setMinuteOfHour(StringHelper.toInt(array[4]));
		}
		if (array.length >= 6) {
			setSecondOfMinute(StringHelper.toInt(array[5]));
		}
		if (array.length >= 7) {
			setMillisecondOfSecond(StringHelper.toInt(array[6]));
		}
	}	
	
	//method
	/**
	 * @return the attributes of this time.
	 */
	public List<StandardSpecification> getAttributes() {
		return new List<StandardSpecification>(
			new StandardSpecification(String.format(
				"%04d-%02d-%02d-%02d-%02d-%02d-%03d",
				getYear(),
				getMonthOfYear(),
				getDayOfMonth(),
				getHourOfDay(),
				getMinuteOfHour(),
				getSecondOfMinute(),
				getMillisecondOfSecond()
			))
		);
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
		return (int)(getMillisecondsTo(time) / TimeUnitCatalogue.MILLISECONDS_PER_DAY);
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
	 * @param days
	 * @return a new time with the given days added to this time.
	 */
	public Time getTimeWithAddedDays(final int days) {
		final Time time = getCopy();
		time.addDays(days);
		return time;
	}
	
	//method
	/**
	 * @param hours
	 * @return a new time with the given hours added to this time.
	 */
	public Time getTimeWithAddedHours(final int hours) {
		final Time time = getCopy();
		time.addHours(hours);
		return time;
	}
	
	//method
	/**
	 * @param milliseconds
	 * @return a new time with the given milliseconds added to this time.
	 */
	public Time getTimeWithAddedMilliseconds(final int milliseconds) {
		final Time time = getCopy();
		time.addMilliseconds(milliseconds);
		return time;
	}
	
	//method
	/**
	 * @param minutes
	 * @return a new time with the given minutes added to this time.
	 */
	public Time getTimeWithAddedMinutes(final int minutes) {
		final Time time = getCopy();
		time.addMinutes(minutes);
		return time;
	}
	
	//method
	/**
	 * @param seconds
	 * @return a new time with the given seconds added to this time.
	 */
	public Time getTimeWithAddedSeconds(final int seconds) {
		final Time time = getCopy();
		time.addSeconds(seconds);
		return time;
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
		return time.isLeapYear(getYear());
	}
	
	//method
	/**
	 * Adds the given days to this time.
	 * 
	 * @param days
	 */
	private void addDays(final int days) {
		addMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_DAY * days);
	}
	
	//method
	/**
	 * Adds the given hours to this time.
	 * 
	 * @param hours
	 */
	private void addHours(final int hours) {
		addMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_HOUR * hours);
	}
	
	//method
	/**
	 * Adds the given milliseconds to this time.
	 * 
	 * @param milliseconds
	 */
	private void addMilliseconds(final int millisceconds) {
		time.setTimeInMillis(getMilliseconds() + millisceconds);
	}

	//method
	/**
	 * Adds the given minutes to this time.
	 * 
	 * @param minutes
	 */
	private void addMinutes(final int minutes) {
		addMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_MINUTE * minutes);
	}
	
	//method
	/**
	 * Adds the given seconds to this time.
	 * 
	 * @param seconds
	 */
	private void addSeconds(final int seconds) {
		addMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_SECOND * seconds);
	}
	
	//method
	/**
	 * @return a copy of this time.
	 */
	private Time getCopy() {
		final Time time = new Time();
		time.time.setTimeInMillis(this.time.getTimeInMillis());
		return time;
	}
	
	//method
	/**
	 * Resets this time.
	 * 
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	private void reset() {		
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
	private void setDayOfMonth(final int dayOfMonth) {
		time.set(GregorianCalendar.DAY_OF_MONTH, dayOfMonth);
	}
	
	//method
	/**
	 * Sets the hour of the day of this time.
	 * 
	 * @param hourOfDay
	 * @return this time.
	 */
	private void setHourOfDay(final int hourOfDay) {
		time.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
	}
	
	//method
	/**
	 * Sets the millisecond of the second of this time.
	 * 
	 * @param millisecondOfSecond
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	private void setMillisecondOfSecond(final int millisecondOfSecond) {
		time.set(GregorianCalendar.MILLISECOND, millisecondOfSecond);
	}
	
	//method
	/**
	 * Sets the minute of the hour of this time
	 * 
	 * @param minuteOfHour
	 * @return this time.
	 */
	private void setMinuteOfHour(final int minuteOfHour) {	
		time.set(GregorianCalendar.MINUTE, minuteOfHour);
	}
	
	//method
	/**
	 * Sets the month of the year of this time.
	 * 
	 * @param monthOfYear
	 * @return this time.
	 * @throws InvalidArgumentException if this time is frozen.
	 */
	private void setMonthOfYear(final int monthOfYear) {
		time.set(GregorianCalendar.MONTH, monthOfYear - 1);
	}
	
	//method
	/**
	 * Sets the second of the minute of this time.
	 * 
	 * @param secondOfMinute
	 * @return this time.
	 */
	private void setSecondOfMinute(final int secondOfMinute) {	
		time.set(GregorianCalendar.SECOND, secondOfMinute);
	}
	
	//method
	/**
	 * Sets the year of this time.
	 * 
	 * @param year
	 * @return this time.
	 */
	private void setYear(final int year) {
		time.set(GregorianCalendar.YEAR, year);
	}
}
