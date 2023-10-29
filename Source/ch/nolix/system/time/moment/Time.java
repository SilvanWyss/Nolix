//package declaration
package ch.nolix.system.time.moment;

//Java imports
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.time.TimeUnitCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.timeapi.momentapi.ITime;
import ch.nolix.systemapi.timeapi.timestructure.Month;
import ch.nolix.systemapi.timeapi.timestructure.Weekday;

//class
/**
 * {@link Time} stores a point in time with a precision of 1 millisecond. A
 * {@link Time} is not mutable. Technically, a {@link Time} is a wrapper around
 * a JDK's {@link ZonedDateTime}.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class Time extends Element implements ITime {

  //constant
  public static final int DEFAULT_YEAR = 2000;

  //constant
  public static final int DEFAULT_MONTH_OF_YEAR = 1;

  //constant
  public static final int DEFAULT_DAY_OF_MONTH = 1;

  //constant
  public static final int DEFAULT_HOUR_OF_DAY = 0;

  //constant
  public static final int DEFAULT_MINUTE_OF_HOUR = 0;

  //constant
  public static final int DEFAULT_SECOND_OF_MINUTE = 0;

  //constant
  public static final int DEFAULT_MILLISECOND_OF_SECOND = 0;

  //constant
  private static final Time DEFAULT_TIME = new Time();

  //attribute
  private final ZonedDateTime zonedDateTime;

  //constructor
  /**
   * Creates a new {@link Time} with default values.
   */
  private Time() {
    this(
      ZonedDateTime.of(
        DEFAULT_YEAR,
        DEFAULT_MONTH_OF_YEAR,
        DEFAULT_DAY_OF_MONTH,
        DEFAULT_HOUR_OF_DAY,
        DEFAULT_MINUTE_OF_HOUR,
        DEFAULT_SECOND_OF_MINUTE,
        1000 * DEFAULT_MILLISECOND_OF_SECOND,
        ZoneId.systemDefault()));
  }

  private Time(final ZonedDateTime zonedDateTime) {
    this.zonedDateTime = zonedDateTime;
  }

  //static method
  /**
   * @param specification
   * @return a new {@link Time} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static Time fromSpecification(final INode<?> specification) {
    return fromString(specification.getSingleChildNodeHeader());
  }

  //static method
  /**
   * @param string
   * @return a new {@link Time} from the given string.
   * @throws ArgumentIsNullException if the given string is null.
   */
  public static Time fromString(final String string) {

    //Asserts that the given string is not null.
    GlobalValidator.assertThat(string).thatIsNamed("string").isNotNull();

    //Creates array of values of the given string.
    final String[] array = string.split("-");

    //Enumerates the length of the array.
    return switch (array.length) {
      case 3 ->
        fromArrayWith3Values(array);
      case 5 ->
        fromArrayWith5Values(array);
      case 6 ->
        fromArrayWith6Values(array);
      case 7 ->
        fromArrayWith7Values(array);
      default ->
        throw UnrepresentingArgumentException.forArgumentAndType(string, Time.class);
    };
  }

  //static method
  /**
   * @return a new {@link Time} that represents the current time on the local
   *         computer.
   */
  public static Time ofNow() {
    return new Time(ZonedDateTime.now());
  }

  //static method
  /**
   * @param year
   * @return a new {@link Time} with the given year.
   */
  public static Time withYear(final int year) {
    return new Time(DEFAULT_TIME.zonedDateTime.withYear(year));
  }

  //static method
  /**
   * @param year
   * @param monthOfYear
   * @return a new {@link Time} with the given year and monthOfYear.
   */
  public static Time withYearAndMonthOfYear(final int year, final int monthOfYear) {
    return new Time(DEFAULT_TIME.zonedDateTime.withYear(year).withMonth(monthOfYear));
  }

  //static method
  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @return a new {@link Time} with the given year, monthOfYear and dayOfMonth.
   */
  public static Time withYearAndMonthOfYearAndDayOfMonth(
    final int year,
    final int monthOfYear,
    final int dayOfMonth) {
    return new Time(DEFAULT_TIME.zonedDateTime.withYear(year).withMonth(monthOfYear).withDayOfMonth(dayOfMonth));
  }

  //static method
  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @param hourOfDay
   * @return a new {@link Time} with the given year, monthOfYear, dayOfMonth and
   *         hourOfDay.
   */
  public static Time withYearAndMonthOfYearAndDayOfMonthAndHourOfDay(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay) {
    return new Time(
      DEFAULT_TIME.zonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay));
  }

  //static method
  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @param hourOfDay
   * @param minuteOfHour
   * @return a new {@link Time} with the given year, monthOfYear, dayOfMonth,
   *         hourOfDay and minuteOfHour.
   */
  public static Time withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour) {
    return new Time(
      DEFAULT_TIME.zonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour));
  }

  //static method
  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @param hourOfDay
   * @param minuteOfHour
   * @param secondOfMinute
   * @return a new {@link Time} with the given year, monthOfYear, dayOfMonth,
   *         hourOfDay, minuteOfHour and secondOfMinute.
   */
  public static Time withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinute(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour,
    final int secondOfMinute) {
    return new Time(
      DEFAULT_TIME.zonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour)
        .withSecond(secondOfMinute));
  }

  //static method
  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @param hourOfDay
   * @param minuteOfHour
   * @param secondOfMinute
   * @param millisecondOfSecond
   * @return a new {@link Time} with the given year, monthOfYear, dayOfMonth,
   *         hourOfDay, minuteOfHour, secondOfMinute and millisecondOfSecond.
   */
  public static Time //
  withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour,
    final int secondOfMinute,
    final int millisecondOfSecond) {
    return new Time(
      DEFAULT_TIME.zonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour)
        .withSecond(secondOfMinute)
        .withNano(1_000_000 * millisecondOfSecond));
  }

  //static method
  /**
   * @param array with 3 values
   * @return a new {@link Time} from the given array.
   */
  private static Time fromArrayWith3Values(final String[] array) {
    return withYearAndMonthOfYearAndDayOfMonth(
      Integer.valueOf(array[0]),
      Integer.valueOf(array[1]),
      Integer.valueOf(array[2]));
  }

  //static method
  /**
   * @param array with 5 values
   * @return a new {@link Time} from the given array.
   */
  private static Time fromArrayWith5Values(final String[] array) {
    return withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(
      Integer.valueOf(array[0]),
      Integer.valueOf(array[1]),
      Integer.valueOf(array[2]),
      Integer.valueOf(array[3]),
      Integer.valueOf(array[4]));
  }

  //static method
  /**
   * @param array with 6 values
   * @return a new {@link Time} from the given array.
   */
  private static Time fromArrayWith6Values(final String[] array) {
    return withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinute(
      Integer.valueOf(array[0]),
      Integer.valueOf(array[1]),
      Integer.valueOf(array[2]),
      Integer.valueOf(array[3]),
      Integer.valueOf(array[4]),
      Integer.valueOf(array[5]));
  }

  //static method
  /**
   * @param array with 7 values
   * @return a new {@link Time} from the given array.
   */
  private static Time fromArrayWith7Values(final String[] array) {
    return withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinuteAndMillisecondOfSecond(
      Integer.valueOf(array[0]),
      Integer.valueOf(array[1]),
      Integer.valueOf(array[2]),
      Integer.valueOf(array[3]),
      Integer.valueOf(array[4]),
      Integer.valueOf(array[5]),
      Integer.valueOf(array[6]));
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    return LinkedList.withElement(
      Node.fromString(String.format(
        "%04d-%02d-%02d-%02d-%02d-%02d-%03d",
        getYearAsInt(),
        getMonthOfYearAsInt(),
        getDayOfMonth(),
        getHourOfDay(),
        getMinuteOfHour(),
        getSecondOfMinute(),
        getMillisecondOfSecond())));
  }

  //method
  /**
   * @return the day of the current {@link Time}.
   */
  public Time getDay() {
    return Time.withYearAndMonthOfYearAndDayOfMonth(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth());
  }

  //method
  /**
   * @return the day of the month of the current {@link Time}.
   */
  public int getDayOfMonth() {
    return zonedDateTime.getDayOfMonth();
  }

  //method
  /**
   * This method returns a negative value if the current {@link Time} is after the
   * given time.
   * 
   * @param time
   * @return the number of days from the current {@link Time} to the given time.
   */
  public int getDaysTo(final Time time) {
    return (int) (getMillisecondsTo(time) / TimeUnitCatalogue.MILLISECONDS_PER_DAY);
  }

  //method
  /**
   * @return the hour of the current {@link Time}.
   */
  public Time getHour() {
    return Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDay(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay());
  }

  //method
  /**
   * @return the hour of the month of the current {@link Time}.
   */
  public int getHourOfDay() {
    return zonedDateTime.getHour();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public long getMilliseconds() {
    return zonedDateTime.toInstant().toEpochMilli();
  }

  //method
  /**
   * @return the millisecond of the second of the current {@link Time}.
   */
  public int getMillisecondOfSecond() {
    return (zonedDateTime.getNano() / 1_000_000);
  }

  //method
  /**
   * This method returns a negative value if the current {@link Time} is after the
   * given time.
   * 
   * @param time
   * @return the number of milliseconds from the current {@link Time} to the given
   *         time.
   */
  public long getMillisecondsTo(final Time time) {
    return (time.getMilliseconds() - this.getMilliseconds());
  }

  //method
  /**
   * @return the minute of the current {@link Time}.
   */
  public Time getMinute() {
    return Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHour(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay(),
      getMinuteOfHour());
  }

  //method
  /**
   * @return the minute of the hour of the current {@link Time}.
   */
  public int getMinuteOfHour() {
    return zonedDateTime.getMinute();
  }

  //method
  /**
   * @return the month of the current {@link Time}.
   */
  public Time getMonth() {
    return Time.withYearAndMonthOfYear(getYearAsInt(), getMonthOfYearAsInt());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public Month getMonthOfYear() {
    return Month.fromJavaMonth(zonedDateTime.getMonth());
  }

  //method
  /**
   * @return the month of the year of the current {@link Time}.
   */
  public int getMonthOfYearAsInt() {
    return zonedDateTime.getMonth().getValue();
  }

  //method
  /**
   * @return the next day of the current {@link Time}.
   */
  public Time getNextDay() {
    return getWithAddedOrSubtractedDays(1).getDay();
  }

  //method
  /**
   * @return the next hour of the current {@link Time}.
   */
  public Time getNextHour() {
    return getWithAddedOrSubtractedHours(1).getHour();
  }

  //method
  /**
   * @return the next minute of the current {@link Time}.
   */
  public Time getNextMinute() {
    return getWithAddedOrSubtractedMinutes(1).getMinute();
  }

  //method
  /**
   * @return the next month of the current {@link Time}.
   */
  public Time getNextMonth() {

    //Handles the case that the month of the year of the current {@link Time} is
    //not December.
    if (getMonthOfYearAsInt() < 12) {
      return Time.withYearAndMonthOfYear(getYearAsInt(), getMonthOfYearAsInt() + 1);
    }

    //Handles the case that the month of the year of the current {@link Time} is
    //December.
    return Time.withYearAndMonthOfYear(getYearAsInt() + 1, 1);
  }

  //method
  /**
   * @return the next second of the current {@link Time}.
   */
  public Time getNextSecond() {
    return getWithAddedOrSubtractedSeconds(1).getSecond();
  }

  //method
  /**
   * @return the next year of the current {@link Time}.
   */
  public Time getNextYear() {
    return Time.withYear(getYearAsInt() + 1);
  }

  //method
  /**
   * @return the second of the current {@link Time}.
   */
  public Time getSecond() {
    return Time.withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinuteOfHourAndSecondOfMinute(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay(),
      getMinuteOfHour(),
      getSecondOfMinute());
  }

  //method
  /**
   * @return the second of the minute of the current {@link Time}.
   */
  public int getSecondOfMinute() {
    return zonedDateTime.getSecond();
  }

  //method
  @Override
  public Weekday getWeekday() {
    return Weekday.fromDayOfWeek(zonedDateTime.getDayOfWeek());
  }

  //method
  /**
   * @param days
   * @return a new {@link Time} with the given days added or subtracted to the
   *         current {@link Time}.
   */
  public Time getWithAddedOrSubtractedDays(final int days) {
    return new Time(zonedDateTime.plusDays(days));
  }

  //method
  /**
   * @param hours
   * @return a new {@link Time} with the given hours added or subtracted to the
   *         current {@link Time}.
   */
  public Time getWithAddedOrSubtractedHours(final int hours) {
    return new Time(zonedDateTime.plusHours(hours));
  }

  //method
  /**
   * @param milliseconds
   * @return a new {@link Time} with the given milliseconds added or subtracted to
   *         the current {@link Time}.
   */
  public Time getWithAddedOrSubtractedMilliseconds(final int milliseconds) {
    return new Time(zonedDateTime.plusNanos(1000L * milliseconds));
  }

  //method
  /**
   * @param minutes
   * @return a new {@link Time} with the given minutes added or subtracted to the
   *         current {@link Time}.
   */
  public Time getWithAddedOrSubtractedMinutes(final int minutes) {
    return new Time(zonedDateTime.plusMinutes(minutes));
  }

  //method
  /**
   * @param seconds
   * @return a new {@link Time} with the given seconds added or subtracted to the
   *         current {@link Time}.
   */
  public Time getWithAddedOrSubtractedSeconds(final int seconds) {
    return new Time(zonedDateTime.plusSeconds(seconds));
  }

  //method
  /**
   * @return the year of the current {@link Time}.
   */
  public Time getYear() {
    return Time.withYear(getYearAsInt());
  }

  //method
  /**
   * @return the year of the current {@link Time}.
   */
  public int getYearAsInt() {
    return zonedDateTime.getYear();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAfter(final ITime time) {
    return (getMilliseconds() > time.getMilliseconds());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBefore(final ITime time) {
    return (getMilliseconds() < time.getMilliseconds());
  }

  //method
  /**
   * @return true if the current {@link Time} is in a leap year.
   */
  public boolean isInLeapYear() {
    return Year.isLeap(getYearAsInt());
  }
}
