//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.util.PopupWindowProvider;
import ch.nolix.core.validator2.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 330
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
	
	//constructor
	/**
	 * Creates new runner with the given job.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @throws NullArgumentException if the given job is null.
	 */
	public JobRunner(final IRunner job) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
			
		this.job = job;
		maxRunCount = null;
		condition = null;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//constructor
	/**
	 * Creates new runner with the given job and condition.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param condition
	 * @throws NullArgumentException if the given job is null.
	 * @throws NullArgumentException if the given condition is null.
	 */
	public JobRunner(final IRunner job, final IBooleanGetter condition) {
		
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
	
	public JobRunner(
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
	
	//constructor
	/**
	 * Creates new runner with the given job and max count.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @throws NullArgumentException if the given job is null.
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	public JobRunner(final IRunner job, final int maxRunCount) {
		
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
	
	//constructor
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
	public JobRunner(final IRunner job, final int maxRunCount, final int timeIntervalInMilliseconds) {
		
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
	
	//constructor
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
	public JobRunner(final IRunner job, final int maxRunCount, final IBooleanGetter condition) {
		
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
	
	//constructor
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
	public JobRunner(
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

	//constructor
	/**
	 * Creates new job runner with the given job and time interval in milliseconds.
	 * The runner will start automatically.
	 * 
	 * @param job
	 * @param timeIntervalInMilliseconds
	 * @throws NullArgumentException if the given job is null.
	 * @throws NegativeArgumenteException if the given time interval in milliseconds is negative.
	 */
	public JobRunner(IRunner job, int timeIntervalInMilliseconds, boolean pseudoValue) {
		
		//Checks if the given job is not null.
		Validator.supposeThat(job).thatIsNamed("job").isNotNull();
		
		//Checks if the given time interval in milliseconds is not negative.
		Validator.supposeThat(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
		
		this.job = job;
		maxRunCount = null;
		condition = null;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
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
			
			//Handles the case if this job runner has no max run count.
			if (!hasMaxRunCount()) {			
				if (hasCondition() && !condition.getOutput()) {
					break;
				}
			}
			
			//Handles the case if this job runner has a max run count.
			else {
				
				if (finishedJobRunCount >= maxRunCount) {
					break;
				}
				
				if (hasCondition() && !condition.getOutput()) {
					break;
				}
			}
			
			try {
				finishedJobRunCount++;
				job.run();				
				
				//Handles the option that this job runner has a time interval.
				if (hasTimeInterval()) {
					Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
				}
			}
			catch (final Exception exception) {
				caughtError = true;
				PopupWindowProvider.showExceptionWindow(exception);
				break;
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
