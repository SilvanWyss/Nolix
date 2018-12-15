//package declaration
package ch.nolix.element.core;

//own imports
import java.util.Calendar;
//Java import
import java.util.GregorianCalendar;

//own imports
import ch.nolix.core.constants.TimeUnitCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.helper.StringHelper;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
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
 * @lines 740
 */
public final class Time extends Element<Time> {
	
	//type name
	public static final String TYPE_NAME = "Time";
	
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
	 * @param specification
	 * @return a new time from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Time createFromSpecification(final DocumentNodeoid specification) {
		return new Time(specification.getOneAttributeAsString());
	}
	
	//static method
	/**
	 * @param unixTimeStamp
	 * @return a new time from the given unix time stamp.
	 */
	public static Time createFromUnixTimeStamp(final long unixTimeStamp) {
		final Time time = new Time();
		time.time.setTimeInMillis(1000 * unixTimeStamp);
		return time;
	}
	
	//constructor
	/**
	 * Creates a new time with default values.
	 */
	public Time() {
		time.setLenient(true);
		reset();
	}
	
	//constructor
	/**
	 * Creates a new time with the given year.
	 * 
	 * @param year
	 */
	public Time(final int year) {
		
		//Calls other constructor.
		this();
		
		setYear(year);
	}
	
	//constructor
	/**
	 * Creates a new time with the given year and month of year.
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 */
	public Time(
		final int year,
		final int monthOfYear
	) {
		
		//Calls other constructor.
		this(year);
		
		setMonthOfYear(monthOfYear);
	}

	//constructor
	/**
	 * Creates a new time with the given year, month of year and day of month.
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
		this(year, monthOfYear);
		
		setDayOfMonth(dayOfMonth);
	}
	
	//constructor
	/**
	 * Creates a new time with the given year, month of year, day of month and hour of day.
	 * 
	 * @param year
	 * @param monthOfYear
	 * @param dayOfMonth
	 * @param hourOfDay
	 */
	public Time(
		final int year,
		final int monthOfYear,
		final int dayOfMonth,
		final int hourOfDay
	) {
		
		//Calls other constructor.
		this(year, monthOfYear, dayOfMonth);

		setHourOfDay(hourOfDay);
	}
	
	//constructor
	/**
	 * Creates a new time with the given year, month of year, day of month, hour of day and minute of hour.
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
		this(year, monthOfYear, dayOfMonth, hourOfDay);
		
		setMinuteOfHour(minuteOfHour);
	}
	
	//constructor
	/**
	 * Creates a new time with the given year, month of year, day of month, hour of day, minute of hour and second and of minute.
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
	 * Creates a new time with the given year, month of year, day of month, hour of day, minute of hour, second and of minute and millisecond of second.
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
	 * Creates a new time the given string represents.
	 * 
	 * @param string
	 * @throws NullArgumentException if the given string is null.
	 */
	public Time(final String string) {
		
		//Calls other constructor.
		this();
		
		//Checks if the given string is not null.
		Validator.suppose(string).thatIsNamed("string").isInstance();
		
		//Creates array of values of the given string.
		final String[] array = string.split("-");
		
		Validator
		.suppose(array.length)
		.thatIsNamed("numer of values of '" + string + "'")
		.isEqualToAny(3, 5, 6, 7);
		
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
	@Override
	public List<DocumentNode> getAttributes() {
		return new List<DocumentNode>(
			new DocumentNode(String.format(
				"%04d-%02d-%02d-%02d-%02d-%02d-%03d",
				getYearAsInt(),
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
	 * @return the day of this time.
	 */
	public Time getDay() {
		return
		new Time(
			getYearAsInt(),			
			getMonthOfYear(),
			getDayOfMonth()
		);
	}
	
	//method
	/**
	 * @return the day of the month of this time.
	 */
	public int getDayOfMonth() {
		return time.get(Calendar.DAY_OF_MONTH);
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
	 * @return the hour of this time.
	 */
	public Time getHour() {
		return
		new Time(
			getYearAsInt(),			
			getMonthOfYear(),
			getDayOfMonth(),
			getHourOfDay()
		);
	}

	//method
	/**
	 * @return the hour of the month of this time.
	 */
	public int getHourOfDay() {
		return time.get(Calendar.HOUR_OF_DAY);
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
	 * @return the millisecond of the second of this time.
	 */
	public int getMillisecondOfSecond() {
		return time.get(Calendar.MILLISECOND);
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
	 * @return the minute of this time.
	 */
	public Time getMinute() {
		return
		new Time(
			getYearAsInt(),
			getMonthOfYear(),
			getDayOfMonth(),
			getHourOfDay(),
			getMinuteOfHour()
		);
	}
	
	//method
	/**
	 * @return the minute of the hour of this time.
	 */
	public int getMinuteOfHour() {
		return time.get(Calendar.MINUTE);
	}
	
	//method
	/**
	 * @return the month of this time.
	 */
	public Time getMonth() {
		return new Time(getYearAsInt(), getMonthOfYear());
	}
	
	//method
	/**
	 * @return the month of the year of this time.
	 */
	public int getMonthOfYear() {
		return (time.get(Calendar.MONTH) + 1);
	}
		
	//method
	/**
	 * @return the next day of this time.
	 */
	public Time getNextDay() {
		return getWithAddedOrSubtractedDays(1).getDay();
	}
	
	//method
	/**
	 * @return the next hour of this time.
	 */
	public Time getNextHour() {
		return getWithAddedOrSubtractedHours(1).getHour();
	}
	
	//method
	/**
	 * @return the next minute of this time.
	 */
	public Time getNextMinute() {
		return getWithAddedOrSubtractedMinutes(1).getMinute();
	}
	
	//method
	/**
	 * @return the next month of this time.
	 */
	public Time getNextMonth() {
		
		//Handles the case that the month of the year of this time is not December.
		if (getMonthOfYear() < 12) {
			return new Time(getYearAsInt(), getMonthOfYear() + 1);
		}
		
		//Handles the case that the month of the year of this time is December.
		return new Time(getYearAsInt() + 1, 1);
	}
	
	//method
	/**
	 * @return the next second of this time.
	 */
	public Time getNextSecond() {
		return getWithAddedOrSubtractedSeconds(1).getSecond();
	}
	
	//method
	/**
	 * @return the next year of this time.
	 */
	public Time getNextYear() {
		return new Time(getYearAsInt() + 1);
	}
	
	//method
	/**
	 * @return the second of this time.
	 */
	public Time getSecond() {
		return
		new Time(
			getYearAsInt(),
			getMonthOfYear(),
			getDayOfMonth(),
			getHourOfDay(),
			getMinuteOfHour(),
			getSecondOfMinute()
		);
	}
	
	//method
	/**
	 * @return the second of the minute of this time.
	 */
	public int getSecondOfMinute() {
		return time.get(Calendar.SECOND);
	}
	
	//method
	/**
	 * @param days
	 * @return a new time with the given days added or subtracted to this time.
	 */
	public Time getWithAddedOrSubtractedDays(final int days) {
		final Time time = getCopy();
		time.addDays(days);
		return time;
	}
	
	//method
	/**
	 * @param hours
	 * @return a new time with the given hours added or subtracted to this time.
	 */
	public Time getWithAddedOrSubtractedHours(final int hours) {
		final Time time = getCopy();
		time.addHours(hours);
		return time;
	}
	
	//method
	/**
	 * @param milliseconds
	 * @return a new time with the given milliseconds added or subtracted to this time.
	 */
	public Time getWithAddedOrSubtractedMilliseconds(final int milliseconds) {
		final Time time = getCopy();
		time.addMilliseconds(milliseconds);
		return time;
	}
	
	//method
	/**
	 * @param minutes
	 * @return a new time with the given minutes added or subtracted to this time.
	 */
	public Time getWithAddedOrSubtractedMinutes(final int minutes) {
		final Time time = getCopy();
		time.addMinutes(minutes);
		return time;
	}
	
	//method
	/**
	 * @param seconds
	 * @return a new time with the given seconds added or subtracted to this time.
	 */
	public Time getWithAddedOrSubtractedSeconds(final int seconds) {
		final Time time = getCopy();
		time.addSeconds(seconds);
		return time;
	}
	
	public Time getYear() {
		return new Time(getYearAsInt());
	}
	
	//method
	/**
	 * @return the year of this time.
	 */
	public int getYearAsInt() {
		return time.get(Calendar.YEAR);
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
		return time.isLeapYear(getYearAsInt());
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
		time.set(Calendar.DAY_OF_MONTH, dayOfMonth);
	}
	
	//method
	/**
	 * Sets the hour of the day of this time.
	 * 
	 * @param hourOfDay
	 * @return this time.
	 */
	private void setHourOfDay(final int hourOfDay) {
		time.set(Calendar.HOUR_OF_DAY, hourOfDay);
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
		time.set(Calendar.MILLISECOND, millisecondOfSecond);
	}
	
	//method
	/**
	 * Sets the minute of the hour of this time
	 * 
	 * @param minuteOfHour
	 * @return this time.
	 */
	private void setMinuteOfHour(final int minuteOfHour) {
		time.set(Calendar.MINUTE, minuteOfHour);
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
		time.set(Calendar.MONTH, monthOfYear - 1);
	}
	
	//method
	/**
	 * Sets the second of the minute of this time.
	 * 
	 * @param secondOfMinute
	 * @return this time.
	 */
	private void setSecondOfMinute(final int secondOfMinute) {
		time.set(Calendar.SECOND, secondOfMinute);
	}
	
	//method
	/**
	 * Sets the year of this time.
	 * 
	 * @param year
	 * @return this time.
	 */
	private void setYear(final int year) {
		time.set(Calendar.YEAR, year);
	}
}
