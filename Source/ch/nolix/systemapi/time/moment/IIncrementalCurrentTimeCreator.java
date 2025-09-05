package ch.nolix.systemapi.time.moment;

import java.time.ZonedDateTime;

/**
 * The {@link ZonedDateTime#now()} method delivers {@link ZonedDateTime} with a
 * limited nanosecond accuracy. The nanoseconds of such a {@link ZonedDateTime}
 * are rounded to hundreds. Through this, {@link ZonedDateTime#now()} that are
 * created just some few nanoseconds after each other, are equal.
 * 
 * A {@link IIncrementalCurrentTimeCreator} guarantees that each
 * {@link ZonedDateTime} the {@link IIncrementalCurrentTimeCreator} creates is
 * at least 1 microsecond bigger then the previously created
 * {@link ZonedDateTime}.
 * 
 * @author Silvan Wyss
 * @version 2024-12-14
 */
public interface IIncrementalCurrentTimeCreator {
  /**
   * @return a new {@link ITime} that represents the current time on the local
   *         computer.
   */
  ITime getCurrentTime();
}
