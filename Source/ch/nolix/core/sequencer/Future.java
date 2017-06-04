//package declaration
package ch.nolix.core.sequencer;

//own import
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 60
 */
public final class Future {

	//attribute
	private final JobRunner jobRunner;
	
	//package-visible constructor
	/**
	 * Creates new future with the given job runner.
	 * 
	 * @param jobRunner
	 * @throws NullArgumentException if the given job runner is null.
	 */
	Future(final JobRunner jobRunner) {
		
		//Checks if the given job runner is not null.
		Validator.supposeThat(jobRunner).thatIsInstanceOf(JobRunner.class).isNotNull();
		
		//Sets the job runner of this future.
		this.jobRunner = jobRunner;
	}
	
	//method
	/**
	 * @return true if this future has caught an error while running the jobs.
	 */
	public boolean caughtError() {
		return jobRunner.caughtError();
	}
	
	//method
	/**
	 * @return the number of finished job runs of this future.
	 */
	public int getFinishedJobRunCount() {
		return jobRunner.getFinishedJobRunCount();
	}
	
	//method
	/**
	 * @return true if this future has finished the jobs.
	 */
	public boolean hasFinishedJobs() {
		return jobRunner.hasFinishedJobs();
	}
	
	//method
	/**
	 * @return true if this future has finished the jobs successfully.
	 */
	public boolean hasFinishedJobsSuccessfully() {
		return (hasFinishedJobs() && !caughtError());
	}
	
	//method
	/**
	 * @return true if this future is running the jobs.
	 */
	public boolean isRunningJobs() {
		return jobRunner.isRunningJobs();
	}
}
