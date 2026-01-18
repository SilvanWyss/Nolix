package ch.nolix.system.time.moment;

import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.time.TimeUnitConversionCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.time.moment.ITime;
import ch.nolix.systemapi.time.timestructure.Month;
import ch.nolix.systemapi.time.timestructure.Weekday;

/**
 * A {@link Time} is not mutable. Technically, a {@link Time} is a wrapper
 * around a JDK's {@link ZonedDateTime}.
 * 
 * @author Silvan Wyss
 */
public final class Time //NOSONAR: A Time is a principal object thus it has many methods.
extends AbstractElement implements ITime {
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
    TimeUnitConversionCatalog.MILLISECONDS_PER_SECOND * DEFAULT_MILLISECOND_OF_SECOND,
    ZoneId.systemDefault()));

  private final ZonedDateTime internalZonedDateTime;

  /**
   * Creates a new {@link Time} with the given zonedDateTime.
   * 
   * @param zonedDateTime
   * @throws ArgumentIsNullException if the given zonedDateTime is null.
   */
  private Time(final ZonedDateTime zonedDateTime) {
    Validator.assertThat(zonedDateTime).thatIsNamed(ZonedDateTime.class).isNotNull();

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
   * @throws NullPointerException     if the given specification is null.
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
    Validator.assertThat(string).thatIsNamed("string").isNotNull();

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
    final var nanoSecondsOfSecond = TimeUnitConversionCatalog.NANOSECONDS_PER_MILLISECOND * millisecondOfSecond;

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
    TimeUnitConversionCatalog.NANOSECONDS_PER_MILLISECOND * millisecondOfSecond
    + TimeUnitConversionCatalog.NANOSECONDS_PER_MICROSECOND * microsecondsOfMilliSecond;

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
      getYear(),
      getMonthOfYearAsInt(),
      getDayOfMonth(),
      getHourOfDay(),
      getMinuteOfHour(),
      getSecondOfMinute(),
      getMillisecondOfSecond(),
      getMicrosecondOfMillisecond());

    final var attribute = Node.withHeader(timeCode);

    return LinkedList.withElement(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDayOfMonth() {
    return internalZonedDateTime.getDayOfMonth();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getHourOfDay() {
    return internalZonedDateTime.getHour();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMicrosecondOfMillisecond() {
    return //
    (internalZonedDateTime.getNano() / TimeUnitConversionCatalog.NANOSECONDS_PER_MICROSECOND)
    % TimeUnitConversionCatalog.MICROSECONDS_PER_MILLISECOND;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMillisecondOfSecond() {
    return (internalZonedDateTime.getNano() / TimeUnitConversionCatalog.NANOSECONDS_PER_MILLISECOND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMinuteOfHour() {
    return internalZonedDateTime.getMinute();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Month getMonthOfYear() {
    return Month.fromJavaMonth(internalZonedDateTime.getMonth());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMonthOfYearAsInt() {
    return internalZonedDateTime.getMonth().getValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getSecondOfMinute() {
    return internalZonedDateTime.getSecond();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Weekday getWeekday() {
    return Weekday.fromDayOfWeek(internalZonedDateTime.getDayOfWeek());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getWeekOfYear() {
    return ((internalZonedDateTime.getDayOfYear() / 7) + 1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getYear() {
    return internalZonedDateTime.getYear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAfter(final ITime time) {
    return (toSeconds() > time.toSeconds());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isBefore(final ITime time) {
    return (toSeconds() < time.toSeconds());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isInLeapYear() {
    return Year.isLeap(getYear());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long toSeconds() {
    return internalZonedDateTime.toEpochSecond();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Time withAddedOrSubtractedDays(final int days) {
    return new Time(internalZonedDateTime.plusDays(days));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Time withAddedOrSubtractedHours(final int hours) {
    return new Time(internalZonedDateTime.plusHours(hours));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ITime withAddedOrSubtractedMicroseconds(final long microseconds) {
    final var nanoseconds = TimeUnitConversionCatalog.NANOSECONDS_PER_MICROSECOND * microseconds;

    return forZonedDateTime(internalZonedDateTime.plusNanos(nanoseconds));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Time withAddedOrSubtractedMilliseconds(final int milliseconds) {
    final var nanoSeconds = TimeUnitConversionCatalog.NANOSECONDS_PER_MILLISECOND * milliseconds;

    return forZonedDateTime(internalZonedDateTime.plusNanos(nanoSeconds));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Time withAddedOrSubtractedMinutes(final int minutes) {
    return forZonedDateTime(internalZonedDateTime.plusMinutes(minutes));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Time withAddedOrSubtractedSeconds(final int seconds) {
    return forZonedDateTime(internalZonedDateTime.plusSeconds(seconds));
  }
}
