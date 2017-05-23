//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 60
 */
final class BackgroundThread extends Thread {

	//attributes
	private final IRunner job;
	private boolean finished = false;
	
	//constructor
	/**
	 * Creates new background thread with the given job.
	 * This background thread will start automatically.
	 * 
	 * @param job
	 * @throws NullArgumentException if the given job is null.
	 */
	public BackgroundThread(final IRunner job) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Sets the job of this background thread.
		this.job = job;
		
		//Starts this background thread.
		start();
	}
	
	//method
	/**
	 * @return true if the job of this background thread is finished.
	 */
	public boolean isFinished() {
		return finished;
	}
	
	//method
	/**
	 * @return true if the job of this background thread is running.
	 */
	public boolean isRunning() {
		return !finished;
	}
	
	//method
	/**
	 * Runs this background thread.
	 */
	public void run() {
		
		job.run();
		
		finished = true;
	}
}
