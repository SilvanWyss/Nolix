//package declaration
package ch.nolix.core.sequencer;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 40
 */
public final class Future {

	//attribute
	private final BackgroundThread backgroundThread;
	
	//package-visible constructor
	/**
	 * Creates new future for the given background thread.
	 * 
	 * @param backgroundThread
	 * @throws NullArgumentException if the given background thread is null.
	 */
	Future(final BackgroundThread backgroundThread) {
		
		//Checks if the given background thread is not null.
		Validator.supposeThat(backgroundThread).thatIsInstanceOf(BackgroundThread.class).isNotNull();
		
		//Sets the background thread of this future.
		this.backgroundThread = backgroundThread;
	}
	
	//method
	/**
	 * @return true if the job of the background thread of this future is finished.
	 */
	public boolean isFinished() {
		return backgroundThread.isFinished();
	}
	
	//method
	/**
	 * @return true if the job of the background thread of htis future is running.
	 */
	public boolean isRunning() {
		return backgroundThread.isRunning();
	}
}
