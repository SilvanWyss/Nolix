//package declaration
package ch.nolix.core.sequencer;

import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 70
 */
public final class Future {

	//attribute
	private final JobRunner jobRunner;
	
	//package-visible constructor
	/**
	 * Creates a new future with the given job runner.
	 * 
	 * @param jobRunner
	 * @throws NullArgumentException if the given job runner is null.
	 */
	Future(final JobRunner jobRunner) {
		
		//Checks if the given job runner is not null.
		Validator
		.suppose(jobRunner)
		.thatIsOfType(JobRunner.class)
		.isNotNull();
		
		//Sets the job runner of this future.
		this.jobRunner = jobRunner;
	}
	
	//method
	/**
	 * @return true if this future caught an error.
	 */
	public boolean caughtError() {
		return jobRunner.caughtError();
	}
	
	//method
	/**
	 * @return the number of finished jobs of this future.
	 */
	public int getFinishedJobCount() {
		return jobRunner.getFinishedJobCount();
	}
	
	//method
	/**
	 * @return true if this future is finished.
	 */
	public boolean isFinished() {
		return jobRunner.isFinished();
	}
	
	//method
	/**
	 * @return true if this future is finished successfully.
	 */
	public boolean isFinishedSuccessfully() {
		return jobRunner.isFinishedSuccessfully();
	}
	
	//method
	/**
	 * @return true if this future is running.
	 */
	public boolean isRunning() {
		return jobRunner.isRunning();
	}
}
