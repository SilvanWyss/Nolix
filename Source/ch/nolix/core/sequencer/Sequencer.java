//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.constants.TimeUnitCatalogue;
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IRunner;

//class
/**
 * This class provides methods to control program flows.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 100
 */
public final class Sequencer {

	//static method
	/**
	 * @param timeIntervalInMilliseconds
	 * @return a new after all mediator with the given time interval in milliseconds.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	public static AfterAllMediator afterAllMilliseconds(final int timeIntervalInMilliseconds) {
		return new AfterAllMediator(timeIntervalInMilliseconds);
	}
	
	//static method
	/**
	 * @return a new after all mediator with 1 second as time interval.
	 */
	public static AfterAllMediator afterAllSeconds() {
		return new AfterAllMediator(TimeUnitCatalogue.MILLISECONDS_PER_SECOND);
	}
	
	//static method
	/**
	 * @param timeIntervalInSeconds
	 * @return a new after all mediator with the given time interval in seconds.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	public static AfterAllMediator afterAllSeconds(final int timeIntervalInSeconds) {
		return new AfterAllMediator(TimeUnitCatalogue.MILLISECONDS_PER_HOUR * timeIntervalInSeconds);
	}
	
	//static method
	/**
	 * @param condition
	 * @return a new as long as mediator with the given condition.
	 * @throws NullArgumentException if the given condition is null.
	 */
	public static AsLongAsMediator asLongAs(final IBooleanGetter condition) {
		return new AsLongAsMediator(condition);
	}
	
	//static method
	/**
	 * @param maxRunCount
	 * @return a new for count mediator with the given max run count.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	public static ForCountMediator forCount(final int maxRunCount) {
		return new ForCountMediator(maxRunCount);
	}
	
	//static method
	/**
	 * Runs the given job in background.
	 * 
	 * @param job
	 * @return a new future.
	 * @throws NullArgumentException if the given job is null.
	 */
	public static Future runInBackground(final IRunner job) {
		return new Future(new JobRunner(job, 1));
	}
	
	//static method
	/**
	 * Waits for the given number of milliseconds.
	 * 
	 * @param milliseconds
	 * @throws NegativeArgumentException if the given milliseconds is negative.
	 */
	public static void waitForMilliseconds(final int milliseconds) {
		Waiter.waitForMilliseconds(milliseconds);
	}
	
	//static method
	/**
	 * Waits for the given number of seconds.
	 * 
	 * @param seconds
	 * @throws NegativeArgumentException if the given seconds is negative.
	 */
	public void waitForSeconds(final int seconds) {
		Waiter.waitForSeconds(seconds);
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Sequencer() {}
}
