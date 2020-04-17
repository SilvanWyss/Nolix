//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.constants.TimeUnitCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.wrapperException.WrapperException;

//class
/**
 * The {@link Waiter} provides methods to wait for specific durations.
 * Of the {@link Waiter} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 50
 */
final class Waiter {
	
	//static method
	/**
	 * Waits for the given duractionInSeconds.
	 * 
	 * @param duractionInSeconds
	 * @throws NegativeArgumentException if the given duractionInSeconds is negative.
	 */
	public static void waitForSeconds(final int duractionInSeconds) {
		
		//Asserts that the given seconds is not negative.
		Validator.assertThat(duractionInSeconds).thatIsNamed("duration in seconds").isNotNegative();
		
		waitForMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_SECOND * duractionInSeconds);
	}
	
	//static method
	/**
	 * Waits for the given durationInMilliseconds.
	 * 
	 * @param durationInMilliseconds
	 * @throws NegativeArgumentException if the given durationInMilliseconds is negative.
	 */
	public static void waitForMilliseconds(final int durationInMilliseconds) {
		
		//Asserts that the given milliseconds is not negative.
		Validator.assertThat(durationInMilliseconds).thatIsNamed("duration in milliseconds").isNotNegative();
		
		try {
			Thread.sleep(durationInMilliseconds);
		}
		catch (final InterruptedException interruptedException) {
			throw new WrapperException(interruptedException);
		}
	}
	
	//visibility-reducing constructor
	/**
	 * Avoids that an instance of the {@link Waiter} can be created.
	 */
	private Waiter() {}
}
