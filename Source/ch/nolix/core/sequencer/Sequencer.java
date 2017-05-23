//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.constants.TimeUnitManager;
import ch.nolix.core.functionInterfaces.IRunner;

//class
/**
 * A sequencer provides methods to control a program flow.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 70
 */
public final class Sequencer {
	
	//static method
	/**
	 * @param timeIntervalInMilliseconds
	 * @return a new after all mediator with the given time interval in milliseconds.
	 */
	public static AfterAllMediator afterAllMilliseconds(
		final int timeIntervalInMilliseconds)
	{
		return new AfterAllMediator(timeIntervalInMilliseconds);
	}
	
	//static method
	/**
	 * @return a new after all mediator with 1 second as time interval.
	 */
	public static AfterAllMediator afterAllSeconds() {
		return new AfterAllMediator(TimeUnitManager.MILLISECONDS_PER_SECOND);
	}
	
	//static method
	/**
	 * @return a new after all mediator with the given time interval in seconds.
	 */
	public static AfterAllMediator afterAllSeconds(final int timeIntervalInSeconds) {
		return new AfterAllMediator(TimeUnitManager.MILLISECONDS_PER_HOUR * timeIntervalInSeconds);
	}
	
	//static method
	/**
	 * @param count
	 * @return new for count mediator with the given count.
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	public static ForCountMediator forCount(final int count) {
		return new ForCountMediator(count);
	}
	
	//static method
	/**
	 * Runs the given job in background.
	 * 
	 * @param job
	 * @return a new Future for the given job.
	 * @throws NullArgumentException if the given job is null.
	 */
	public static Future runInBackground(final IRunner job) {
		return new Future(new BackgroundThread(job));
	}

	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private Sequencer() {}
}
