//package declaration
package ch.nolix.core.programcontrol.sequencer;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.time.TimeUnitCatalogue;

//class
/**
 * The {@link Waiter} provides methods to wait for specific durations. Of the
 * {@link Waiter} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-06-04
 */
final class Waiter {

  //constructor
  /**
   * Prevents that an instance of the {@link Waiter} can be created.
   */
  private Waiter() {
  }

  //static method
  /**
   * Waits for the given duractionInSeconds.
   * 
   * @param duractionInSeconds
   * @throws NegativeArgumentException if the given duractionInSeconds is
   *                                   negative.
   */
  public static void waitForSeconds(final int duractionInSeconds) {

    //Asserts that the given duractionInSeconds is not negative.
    GlobalValidator.assertThat(duractionInSeconds).thatIsNamed("duration in seconds").isNotNegative();

    waitForMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_SECOND * duractionInSeconds);
  }

  //static method
  /**
   * Waits for the given durationInMilliseconds.
   * 
   * @param durationInMilliseconds
   * @throws NegativeArgumentException if the given durationInMilliseconds is
   *                                   negative.
   */
  public static void waitForMilliseconds(final int durationInMilliseconds) {

    //Asserts that the given durationInMilliseconds is not negative.
    GlobalValidator.assertThat(durationInMilliseconds).thatIsNamed("duration in milliseconds").isNotNegative();

    try {
      Thread.sleep(durationInMilliseconds);
    } catch (final InterruptedException interruptedException) {
      Thread.currentThread().interrupt();
      throw WrapperException.forError(interruptedException);
    }
  }
}
