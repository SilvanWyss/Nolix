/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.programcontrol.flowcontrol;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.time.TimeUnitConversionCatalog;

/**
 * The {@link Waiter} provides methods to wait for specific durations. Of the
 * {@link Waiter} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
final class Waiter {
  /**
   * Prevents that an instance of the {@link Waiter} can be created.
   */
  private Waiter() {
  }

  /**
   * Waits for the given duractionInSeconds.
   * 
   * @param duractionInSeconds
   * @throws NegativeArgumentException if the given duractionInSeconds is
   *                                   negative.
   */
  public static void waitForSeconds(final int duractionInSeconds) {
    //Asserts that the given duractionInSeconds is not negative.
    Validator.assertThat(duractionInSeconds).thatIsNamed("duration in seconds").isNotNegative();

    waitForMilliseconds(TimeUnitConversionCatalog.MILLISECONDS_PER_SECOND * duractionInSeconds);
  }

  /**
   * Waits for the given durationInMilliseconds.
   * 
   * @param durationInMilliseconds
   * @throws NegativeArgumentException if the given durationInMilliseconds is
   *                                   negative.
   */
  public static void waitForMilliseconds(final int durationInMilliseconds) {
    //Asserts that the given durationInMilliseconds is not negative.
    Validator.assertThat(durationInMilliseconds).thatIsNamed("duration in milliseconds").isNotNegative();

    try {
      Thread.sleep(durationInMilliseconds);
    } catch (final InterruptedException interruptedException) {
      Thread.currentThread().interrupt();
      throw WrapperException.forError(interruptedException);
    }
  }
}
