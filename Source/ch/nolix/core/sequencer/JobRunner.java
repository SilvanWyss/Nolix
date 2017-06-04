//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 320
 */
final class JobRunner extends Thread {

	//attribute
	private final IRunner job;
	private int finishedJobRunCount = 0;
	private boolean running = true;
	private boolean caughtError = true;
	
	//optional attributes
	private final Integer maxRunCount;
	private final IBooleanGetter condition;
	private final Integer timeIntervalInMilliseconds;	
	
	//package-visible constructor
	/**
	 * Creates new runner with the given job.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @throws NullArgumentException if the given job is null.
	 */
	JobRunner(final IRunner job) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
			
		this.job = job;
		maxRunCount = null;
		condition = null;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//package-visible constructor
	/**
	 * Creates new runner with the given job and condition.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param condition
	 * @throws NullArgumentException if the given job is null.
	 * @throws NullArgumentException if the given condition is null.
	 */
	JobRunner(final IRunner job, final IBooleanGetter condition) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Checks if the given condition is not null.
		Validator.supposeThat(condition).thatIsNamed("condition").isNotNull();
			
		this.job = job;
		maxRunCount = null;
		this.condition = condition;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	JobRunner(
		final IRunner job,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		Validator.supposeThat(condition).thatIsNamed("condition").isNotNull();
		
		Validator
		.supposeThat(timeIntervalInMilliseconds)
		.thatIsNamed("time interval in milliseoconds")
		.isNotNegative();
			
		this.job = job;
		this.maxRunCount = null;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
	}
	
	//package-visible constructor
	/**
	 * Creates new runner with the given job and max count.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @throws NullArgumentException if the given job is null.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	JobRunner(final IRunner job, final int maxRunCount) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Checks if the given max run count is not negative.
		Validator.supposeThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
			
		this.job = job;
		this.maxRunCount = maxRunCount;
		condition = null;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//package-visible constructor
	/**
	 * Creates new runner with the given job and max count.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @param timeIntervalInMilliseonds
	 * @throws NullArgumentException if the given job is null.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	JobRunner(final IRunner job, final int maxRunCount, final int timeIntervalInMilliseconds) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Checks if the given max run count is not negative.
		Validator.supposeThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given time interval in milliseconds is not negative.
		Validator.supposeThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
			
		this.job = job;
		this.maxRunCount = maxRunCount;
		condition = null;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
	}
	
	//package-visible constructor
	/**
	 * Creates new runner with the given job, max run count and condition.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @param condition
	 * @throws NullArgumentException if the given job is null.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 * @throws NullArgumentException if the given condition is null.
	 */
	JobRunner(final IRunner job, final int maxRunCount, final IBooleanGetter condition) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Checks if the given max run count is not negative.
		Validator.supposeThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given condition is not null.
		Validator.supposeThat(condition).thatIsNamed("condition").isNotNull();
		
		this.job = job;
		this.maxRunCount = maxRunCount;
		this.condition = condition;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//package-visible constructor
	/**
	 * Creates new job runner with the given job, max run count, condition and time interval in milliseconds.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws NullArgumentException if the given job is null.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 * @throws NullArgumentException if the given condition is null.
	 * @throws NegativeArgumenteException if the given time interval in milliseconds is negative.
	 */
	JobRunner(
		final IRunner job,
		final int maxRunCount,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Checks if the given max run count is not negative.
		Validator.supposeThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given condition is not null.
		Validator.supposeThat(condition).thatIsNamed("condition").isNotNull();
		
		//Checks if the given time interval in milliseconds is not negative.
		Validator.supposeThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
		
		this.job = job;
		this.maxRunCount = maxRunCount;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
	}

	JobRunner(IRunner job, int timeIntervalInMilliseconds, boolean pseudoValue) {
		this.job = job;
		maxRunCount = null;
		condition = null;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//method
	/**
	 * @return true if this job runner has caught an error while running the jobs.
	 */
	public boolean caughtError() {
		return caughtError;
	}
	
	//method
	/**
	 * @return the number of finished job runs of this job runner.
	 */
	public int getFinishedJobRunCount() {
		return finishedJobRunCount;
	}
	
	//method
	/**
	 * @return true if the job runs of this job runner are finished.
	 */
	public boolean hasFinishedJobs() {
		return !running;
	}
	
	//method
	/**
	 * @return true if the job runs of this job runner are running.
	 */
	public boolean isRunningJobs(){
		return running;
	}
	
	//method
	/**
	 * Lets this runner run.
	 */
	public void run() {
				
		//main loop
		while (true) {
					
			if (!hasMaxRunCount()) {		
				if (hasCondition() && !condition.getOutput()) {
					break;
				}
			}
			else {
				
				if (finishedJobRunCount >= maxRunCount) {
					break;
				}
				
				if (hasCondition() && !condition.getOutput()) {
					break;
				}
			}
			
			try {
				job.run();
				finishedJobRunCount++;
				
				if (hasTimeInterval()) {
					Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
				}
			}
			catch (final Exception exception) {
				caughtError = true;
			}
		}
		
		running = false;
	}
	
	//method
	/**
	 * @return true if this runner has a condition.
	 */
	private boolean hasCondition() {
		return (condition != null);
	}
	
	//method
	/**
	 * @return true if this job runner has a max run count.
	 */
	private boolean hasMaxRunCount() {
		return (maxRunCount != null);
	}
	
	//method
	/**
	 * @return true if this runner has a time interval.
	 */
	private boolean hasTimeInterval() {
		return (timeIntervalInMilliseconds != null);
	}
}
