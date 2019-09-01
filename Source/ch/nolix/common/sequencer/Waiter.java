//package declaration
package ch.nolix.common.sequencer;

import ch.nolix.common.constants.TimeUnitCatalogue;
import ch.nolix.common.validator.Validator;

//package-visibel class
/**
 * This class provides methods to wait for specific durations.
 * Of this class an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 50
 */
final class Waiter {

	//static method
	/**
	 * Waits for the given number of seconds.
	 * 
	 * @param seconds
	 * @throws NegativeArgumentException if the given seconds is negative.
	 */
	public static void waitForSeconds(int seconds) {
		
		//Checks if the given seconds is not negative.
		Validator.suppose(seconds).thatIsNamed("seconds").isNotNegative();
		
		waitForMilliseconds(TimeUnitCatalogue.MILLISECONDS_PER_SECOND * seconds);
	}
	
	//static method
	/**
	 * Waits for the given number of milliseconds.
	 * 
	 * @param milliseconds
	 * @throws NegativeArgumentException if the given milliseconds is negative.
	 */
	public static void waitForMilliseconds(final int milliseconds) {
		
		//Checks if the given milliseconds is not negative.
		Validator.suppose(milliseconds).thatIsNamed("milliseconds").isNotNegative();
		
		try {
			Thread.sleep(milliseconds);
		}
		catch (final InterruptedException ie) {
			throw new RuntimeException(ie);
		}
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Waiter() {}
}
