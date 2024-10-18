package ch.nolix.coreapi.programatomapi.timeunitapi;

/**
 * Of the {@link TimeUnitConversionCatalogue} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2017-02-05
 */
public final class TimeUnitConversionCatalogue {

  public static final int NANOSECONDS_PER_MICROSECOND = 1000;

  public static final int NANOSECONDS_PER_MILLISECOND = 1_000_000;

  public static final int NANOSECONDS_PER_SECOND = 1_000_000_000;

  public static final long NANOSECONDS_PER_MINUTE = 60_000_000_000L;

  public static final long NANOSECONDS_PER_HOUR = 3_600_000_000_000L;

  public static final long NANOSECONDS_PER_DAY = 86_400_000_000_000L;

  public static final int MICROSECONDS_PER_MILLISECOND = 1000;

  public static final int MICROSECONDS_PER_SECOND = 1_000_000;

  public static final int MICROSECONDS_PER_MINUTE = 60_000_000;

  public static final long MICROSECONDS_PER_HOUR = 3_600_000_000L;

  public static final long MICROSECONDS_PER_DAY = 86_400_000_000L;

  public static final int MILLISECONDS_PER_SECOND = 1000;

  public static final int MILLISECONDS_PER_MINUTE = 60000;

  public static final int MILLISECONDS_PER_HOUR = 3_600_000;

  public static final int MILLISECONDS_PER_DAY = 86_400_000;

  public static final int SECONDS_PER_MINUTE = 60;

  public static final int SECONDS_PER_HOUR = 3600;

  public static final int SECONDS_PER_DAY = 86400;

  public static final int MINUTES_PER_HOUR = 60;

  public static final int MINUTES_PER_DAY = 1440;

  public static final int HOURS_PER_DAY = 24;

  /**
   * Prevents that an instance of the {@link TimeUnitConversionCatalogue} can be
   * created.
   */
  private TimeUnitConversionCatalogue() {
  }
}
