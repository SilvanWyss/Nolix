package ch.nolix.system.time.moment;

import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.timeunitapi.TimeUnitConversionCatalogue;
import ch.nolix.system.element.base.Element;
import ch.nolix.systemapi.timeapi.momentapi.ITime;
import ch.nolix.systemapi.timeapi.timestructureapi.Month;
import ch.nolix.systemapi.timeapi.timestructureapi.Weekday;

/**
 * A {@link Time} is not mutable. A {@link Time} stores a time with a precision
 * of 1 microsecond. Technically, a {@link Time} is a wrapper around a JDK's
 * {@link ZonedDateTime}.
 * 
 * @author Silvan Wyss
 * @version 2016-09-01
 */
public final class Time //NOSONAR: A Time is a principal object thus it has many methods.
extends Element implements ITime {

  public static final int DEFAULT_YEAR = 2000;

  public static final int DEFAULT_MONTH_OF_YEAR = 1;

  public static final int DEFAULT_DAY_OF_MONTH = 1;

  public static final int DEFAULT_HOUR_OF_DAY = 0;

  public static final int DEFAULT_MINUTE_OF_HOUR = 0;

  public static final int DEFAULT_SECOND_OF_MINUTE = 0;

  public static final int DEFAULT_MILLISECOND_OF_SECOND = 0;

  public static final int DEFAULT_MICROSECOND_OF_MILLISECOND = 0;

  private static final Time DEFAULT_INSTANCE = forZonedDateTime(ZonedDateTime.of(
    DEFAULT_YEAR,
    DEFAULT_MONTH_OF_YEAR,
    DEFAULT_DAY_OF_MONTH,
    DEFAULT_HOUR_OF_DAY,
    DEFAULT_MINUTE_OF_HOUR,
    DEFAULT_SECOND_OF_MINUTE,
    TimeUnitConversionCatalogue.MILLISECONDS_PER_SECOND * DEFAULT_MILLISECOND_OF_SECOND,
    ZoneId.systemDefault()));

  private final ZonedDateTime internalZonedDateTime;

  /**
   * Creates a new {@link Time} with the given zonedDateTime.
   * 
   * @param zonedDateTime
   * @throws ArgumentIsNullException if the given zonedDateTime is null.
   */
  private Time(final ZonedDateTime zonedDateTime) {

    GlobalValidator.assertThat(zonedDateTime).thatIsNamed(ZonedDateTime.class).isNotNull();

    internalZonedDateTime = zonedDateTime;
  }

  /**
   * @return a new {@link Time} for the given zonedDateTime.
   * 
   * @param zonedDateTime
   * @throws ArgumentIsNullException if the given zonedDateTime is null.
   */
  public static Time forZonedDateTime(final ZonedDateTime zonedDateTime) {
    return new Time(zonedDateTime);
  }

  /**
   * @param specification
   * @return a new {@link Time} from the given specification.
   * @NullPointerException if the given specification is null.
   * @throws InvalidArgumentException if the given specification does not
   *                                  represent a {@link Time}.
   */
  public static Time fromSpecification(final INode<?> specification) {
    return fromString(specification.getSingleChildNodeHeader());
  }

  /**
   * @param string
   * @return a new {@link Time} from the given string.
   * @throws InvalidArgumentException if the given string does not represent a
   *                                  {@link Time}.
   */
  public static Time fromString(final String string) {

    GlobalValidator.assertThat(string).thatIsNamed("string").isNotNull();

    final var values = string.split("-");

    return switch (values.length) {
      case 3 ->
        fromArrayWith3Values(values);
      case 5 ->
        fromArrayWith5Values(values);
      case 6 ->
        fromArrayWith6Values(values);
      case 7 ->
        fromArrayWith7Values(values);
      case 8 ->
        fromArrayWith8Values(values);
      default ->
        throw UnrepresentingArgumentException.forArgumentAndType(string, Time.class);
    };
  }

  /**
   * @return a new {@link Time} that represents the current time.
   */
  public static Time ofNow() {
    return new Time(ZonedDateTime.now());
  }

  /**
   * @param year
   * @return a new {@link Time} with the given year.
   */
  public static Time withYear(final int year) {
    return new Time(DEFAULT_INSTANCE.internalZonedDateTime.withYear(year));
  }

  /**
   * @param year
   * @param monthOfYear
   * @return a new {@link Time} with the given year and monthOfYear.
   */
  public static Time withYearAndMonthOfYear(final int year, final int monthOfYear) {
    return new Time(DEFAULT_INSTANCE.internalZonedDateTime.withYear(year).withMonth(monthOfYear));
  }

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
    return //
    new Time(
      DEFAULT_INSTANCE.internalZonedDateTime.withYear(year).withMonth(monthOfYear).withDayOfMonth(dayOfMonth));
  }

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
    return //
    new Time(
      DEFAULT_INSTANCE.internalZonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay));
  }

  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @param hourOfDay
   * @param minuteOfHour
   * @return a new {@link Time} with the given year, monthOfYear, dayOfMonth,
   *         hourOfDay and minuteOfHour.
   */
  public static Time withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHour(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour) {
    return //
    new Time(
      DEFAULT_INSTANCE.internalZonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour));
  }

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
  public static Time withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMin(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour,
    final int secondOfMinute) {
    return //
    new Time(
      DEFAULT_INSTANCE.internalZonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour)
        .withSecond(secondOfMinute));
  }

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
  withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour,
    final int secondOfMinute,
    final int millisecondOfSecond) {

    final var nanoSecondsOfSecond = TimeUnitConversionCatalogue.NANOSECONDS_PER_MILLISECOND * millisecondOfSecond;

    return //
    new Time(
      DEFAULT_INSTANCE.internalZonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour)
        .withSecond(secondOfMinute)
        .withNano(nanoSecondsOfSecond));
  }

  /**
   * @param year
   * @param monthOfYear
   * @param dayOfMonth
   * @param hourOfDay
   * @param minuteOfHour
   * @param secondOfMinute
   * @param millisecondOfSecond
   * @param microsecondsOfMilliSecond
   * @return a new {@link Time} with the given year, monthOfYear, dayOfMonth,
   *         hourOfDay, minuteOfHour, secondOfMinute and millisecondOfSecond.
   */
  public static Time //
  withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSecAndMicrosecOfMillisec( //NOSONAR: A Time has many parameters.
    final int year,
    final int monthOfYear,
    final int dayOfMonth,
    final int hourOfDay,
    final int minuteOfHour,
    final int secondOfMinute,
    final int millisecondOfSecond,
    final int microsecondsOfMilliSecond) {

    final var nanoSecondsOfSecond = //
    TimeUnitConversionCatalogue.NANOSECONDS_PER_MILLISECOND * millisecondOfSecond
    + TimeUnitConversionCatalogue.NANOSECONDS_PER_MICROSECOND * microsecondsOfMilliSecond;

    return //
    new Time(
      DEFAULT_INSTANCE.internalZonedDateTime
        .withYear(year)
        .withMonth(monthOfYear)
        .withDayOfMonth(dayOfMonth)
        .withHour(hourOfDay)
        .withMinute(minuteOfHour)
        .withSecond(secondOfMinute)
        .withNano(nanoSecondsOfSecond));
  }

  /**
   * @param arrayWith3Values
   * @return a new {@link Time} from the given arrayWith3Values.
   */
  private static Time fromArrayWith3Values(final String[] arrayWith3Values) {
    return //
    withYearAndMonthOfYearAndDayOfMonth(
      Integer.valueOf(arrayWith3Values[0]),
      Integer.valueOf(arrayWith3Values[1]),
      Integer.valueOf(arrayWith3Values[2]));
  }

  /**
   * @param arrayWith5Values
   * @return a new {@link Time} from the given arrayWith5Values.
   */
  private static Time fromArrayWith5Values(final String[] arrayWith5Values) {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHour(
      Integer.valueOf(arrayWith5Values[0]),
      Integer.valueOf(arrayWith5Values[1]),
      Integer.valueOf(arrayWith5Values[2]),
      Integer.valueOf(arrayWith5Values[3]),
      Integer.valueOf(arrayWith5Values[4]));
  }

  /**
   * @param arrayWith6Values
   * @return a new {@link Time} from the given arrayWith6Values.
   */
  private static Time fromArrayWith6Values(final String[] arrayWith6Values) {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMin(
      Integer.valueOf(arrayWith6Values[0]),
      Integer.valueOf(arrayWith6Values[1]),
      Integer.valueOf(arrayWith6Values[2]),
      Integer.valueOf(arrayWith6Values[3]),
      Integer.valueOf(arrayWith6Values[4]),
      Integer.valueOf(arrayWith6Values[5]));
  }

  /**
   * @param arrayWith7Values
   * @return a new {@link Time} from the given arrayWith7Values.
   */
  private static Time fromArrayWith7Values(final String[] arrayWith7Values) {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSec(
      Integer.valueOf(arrayWith7Values[0]),
      Integer.valueOf(arrayWith7Values[1]),
      Integer.valueOf(arrayWith7Values[2]),
      Integer.valueOf(arrayWith7Values[3]),
      Integer.valueOf(arrayWith7Values[4]),
      Integer.valueOf(arrayWith7Values[5]),
      Integer.valueOf(arrayWith7Values[6]));
  }

  /**
   * @param arrayWith8Values
   * @return a new {@link Time} from the given arrayWith8Values.
   */
  private static Time fromArrayWith8Values(final String[] arrayWith8Values) {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMinAndMillisecOfSecAndMicrosecOfMillisec(
      Integer.valueOf(arrayWith8Values[0]),
      Integer.valueOf(arrayWith8Values[1]),
      Integer.valueOf(arrayWith8Values[2]),
      Integer.valueOf(arrayWith8Values[3]),
      Integer.valueOf(arrayWith8Values[4]),
      Integer.valueOf(arrayWith8Values[5]),
      Integer.valueOf(arrayWith8Values[6]),
      Integer.valueOf(arrayWith8Values[7]));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {

    final var timeCode = //
    String.format(
      "%04d-%02d-%02d-%02d-%02d-%02d-%03d-%03d",
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay(),
      getMinuteOfHour(),
      getSecondOfMinute(),
      getMillisecondOfSecond(),
      getMicrosecondsOfMillisecond());

    final var attribute = Node.withHeader(timeCode);

    return LinkedList.withElement(attribute);
  }

  /**
   * @return the day of the current {@link Time}.
   */
  public Time getDay() {
    return //
    withYearAndMonthOfYearAndDayOfMonth(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth());
  }

  /**
   * @return the day of the month of the current {@link Time}.
   */
  public int getDayOfMonth() {
    return internalZonedDateTime.getDayOfMonth();
  }

  /**
   * This method returns a negative value if the current {@link Time} is after the
   * given time.
   * 
   * @param time
   * @return the number of days from the current {@link Time} to the given time.
   */
  public int getDaysTo(final Time time) {
    return (int) (getMillisecondsTo(time) / TimeUnitConversionCatalogue.MILLISECONDS_PER_DAY);
  }

  /**
   * @return the hour of the current {@link Time}.
   */
  public Time getHour() {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDay(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay());
  }

  /**
   * @return the hour of the month of the current {@link Time}.
   */
  public int getHourOfDay() {
    return internalZonedDateTime.getHour();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMicrosecondsOfMillisecond() {
    return //
    (internalZonedDateTime.getNano() / TimeUnitConversionCatalogue.NANOSECONDS_PER_MICROSECOND)
    % TimeUnitConversionCatalogue.MICROSECONDS_PER_MILLISECOND;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getMilliseconds() {
    return internalZonedDateTime.toInstant().toEpochMilli();
  }

  /**
   * @return the millisecond of the second of the current {@link Time}.
   */
  public int getMillisecondOfSecond() {
    return (internalZonedDateTime.getNano() / TimeUnitConversionCatalogue.NANOSECONDS_PER_MILLISECOND);
  }

  /**
   * This method returns a negative value if the current {@link Time} is after the
   * given time.
   * 
   * @param time
   * @return the number of milliseconds from the current {@link Time} to the given
   *         time.
   */
  public long getMillisecondsTo(final ITime time) {
    return (time.getMilliseconds() - this.getMilliseconds());
  }

  /**
   * @return the minute of the current {@link Time}.
   */
  public Time getMinute() {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHour(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay(),
      getMinuteOfHour());
  }

  /**
   * @return the minute of the hour of the current {@link Time}.
   */
  public int getMinuteOfHour() {
    return internalZonedDateTime.getMinute();
  }

  /**
   * @return the month of the current {@link Time}.
   */
  public Time getMonth() {
    return withYearAndMonthOfYear(getYearAsInt(), getMonthOfYearAsInt());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Month getMonthOfYear() {
    return Month.fromJavaMonth(internalZonedDateTime.getMonth());
  }

  /**
   * @return the month of the year of the current {@link Time}.
   */
  public int getMonthOfYearAsInt() {
    return internalZonedDateTime.getMonth().getValue();
  }

  /**
   * @return the next day of the current {@link Time}.
   */
  public Time getNextDay() {
    return withAddedOrSubtractedDays(1).getDay();
  }

  /**
   * @return the next hour of the current {@link Time}.
   */
  public Time getNextHour() {
    return withAddedOrSubtractedHours(1).getHour();
  }

  /**
   * @return the next minute of the current {@link Time}.
   */
  public Time getNextMinute() {
    return withAddedOrSubtractedMinutes(1).getMinute();
  }

  /**
   * @return the next month of the current {@link Time}.
   */
  public Time getNextMonth() {

    //Handles the case that the month of the year of the current {@link Time} is not December.
    if (getMonthOfYearAsInt() < 12) {
      return withYearAndMonthOfYear(getYearAsInt(), getMonthOfYearAsInt() + 1);
    }

    //Handles the case that the month of the year of the current {@link Time} is December.
    return withYearAndMonthOfYear(getYearAsInt() + 1, 1);
  }

  /**
   * @return the next second of the current {@link Time}.
   */
  public Time getNextSecond() {
    return withAddedOrSubtractedSeconds(1).getSecond();
  }

  /**
   * @return the next year of the current {@link Time}.
   */
  public Time getNextYear() {
    return withYear(getYearAsInt() + 1);
  }

  /**
   * @return the second of the current {@link Time}.
   */
  public Time getSecond() {
    return //
    withYearAndMonthOfYearAndDayOfMonthAndHourOfDayAndMinOfHourAndSecOfMin(
      getYearAsInt(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay(),
      getMinuteOfHour(),
      getSecondOfMinute());
  }

  /**
   * @return the second of the minute of the current {@link Time}.
   */
  public int getSecondOfMinute() {
    return internalZonedDateTime.getSecond();
  }

  @Override
  public Weekday getWeekday() {
    return Weekday.fromDayOfWeek(internalZonedDateTime.getDayOfWeek());
  }

  /**
   * @return the year of the current {@link Time}.
   */
  public Time getYear() {
    return withYear(getYearAsInt());
  }

  /**
   * @return the year of the current {@link Time}.
   */
  public int getYearAsInt() {
    return internalZonedDateTime.getYear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAfter(final ITime time) {
    return (getMilliseconds() > time.getMilliseconds());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBefore(final ITime time) {
    return (getMilliseconds() < time.getMilliseconds());
  }

  /**
   * @return true if the current {@link Time} is in a leap year.
   */
  public boolean isInLeapYear() {
    return Year.isLeap(getYearAsInt());
  }

  /**
   * @param days
   * @return a new {@link Time} with the given days added or subtracted to the
   *         current {@link Time}.
   */
  public Time withAddedOrSubtractedDays(final int days) {
    return new Time(internalZonedDateTime.plusDays(days));
  }

  /**
   * @param hours
   * @return a new {@link Time} with the given hours added or subtracted to the
   *         current {@link Time}.
   */
  public Time withAddedOrSubtractedHours(final int hours) {
    return new Time(internalZonedDateTime.plusHours(hours));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITime withAddedOrSubtractedMicroseconds(final long microseconds) {
  
    final var nanoseconds = TimeUnitConversionCatalogue.NANOSECONDS_PER_MICROSECOND * microseconds;
  
    return forZonedDateTime(internalZonedDateTime.plusNanos(nanoseconds));
  }

  /**
   * @param milliseconds
   * @return a new {@link Time} with the given milliseconds added or subtracted to
   *         the current {@link Time}.
   */
  public Time withAddedOrSubtractedMilliseconds(final int milliseconds) {
  
    final var nanoSeconds = TimeUnitConversionCatalogue.NANOSECONDS_PER_MILLISECOND * milliseconds;
  
    return forZonedDateTime(internalZonedDateTime.plusNanos(nanoSeconds));
  }

  /**
   * @param minutes
   * @return a new {@link Time} with the given minutes added or subtracted to the
   *         current {@link Time}.
   */
  public Time withAddedOrSubtractedMinutes(final int minutes) {
    return forZonedDateTime(internalZonedDateTime.plusMinutes(minutes));
  }

  /**
   * @param seconds
   * @return a new {@link Time} with the given seconds added or subtracted to the
   *         current {@link Time}.
   */
  public Time withAddedOrSubtractedSeconds(final int seconds) {
    return forZonedDateTime(internalZonedDateTime.plusSeconds(seconds));
  }
}
