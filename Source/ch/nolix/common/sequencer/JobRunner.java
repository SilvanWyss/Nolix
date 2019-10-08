//package declaration
package ch.nolix.common.sequencer;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IBooleanGetter;
import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.logger.Logger;
import ch.nolix.common.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 410
 */
final class JobRunner extends Thread {

	//attribute
	private final IFunction job;
	private int finishedJobCount = 0;
	private boolean running = true;
	
	//optional attributes
	private final Integer maxRunCount;
	private final IBooleanGetter condition;
	private final Integer timeIntervalInMilliseconds;
	private Throwable error;
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public JobRunner(final IFunction job) {
		
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
			
		this.job = job;
		maxRunCount = null;
		condition = null;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job and condition.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @param condition
	 * @throws ArgumentIsNullException if the given job is null.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public JobRunner(final IFunction job, final IBooleanGetter condition) {
		
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed("condition").isNotNull();
			
		this.job = job;
		maxRunCount = null;
		this.condition = condition;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job, condition and timeIntervalInMilliseconds.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws ArgumentIsNullException if the given job is null.
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws NegativeArgumentException if the given timeIntervalInMilliseconds is negative.
	 */
	public JobRunner(
		final IFunction job,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
		
		//Checks if the given timeIntervalInMilliseconds is not negative.
		Validator.suppose(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseoconds").isNotNegative();
			
		this.job = job;
		this.maxRunCount = null;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job and maxRunCount.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @throws ArgumentIsNullException if the given job is null.
	 * @throws NegativeArgumentException if the given maxRunCount is negative.
	 */
	public JobRunner(
		final IFunction job,
		final int maxRunCount
	) {
		
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		//Checks if the given maxRunCount is not negative.
		Validator.suppose(maxRunCount).thatIsNamed("max run count").isNotNegative();
			
		this.job = job;
		this.maxRunCount = maxRunCount;
		condition = null;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job, maxRunCount and condition.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @param condition
	 * @throws ArgumentIsNullException if the given job is null.
	 * @throws NegativeArgumentException if the given maxRunCount is negative.
	 * @throws ArgumentIsNullException if the given condition is null.
	 */
	public JobRunner(
		final IFunction job,
		final int maxRunCount,
		final IBooleanGetter condition
	) {
		
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		//Checks if the given maxRunCount is not negative.
		Validator.suppose(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
		
		this.job = job;
		this.maxRunCount = maxRunCount;
		this.condition = condition;
		timeIntervalInMilliseconds = null;
		
		start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job, maxRunCount, condition and timeIntervalInMilliseconds.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws ArgumentIsNullException if the given job is null.
	 * @throws NegativeArgumentException if the given maxRunCount is negative.
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws NegativeArgumenteException if the given timeIntervalInMilliseconds is negative.
	 */
	public JobRunner(
		final IFunction job,
		final int maxRunCount,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		//Checks if the given max run count is not negative.
		Validator.suppose(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed(VariableNameCatalogue.CONDITION).isNotNull();
		
		//Checks if the given timeIntervalInMilliseconds is not negative.
		Validator.suppose(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
		
		this.job = job;
		this.maxRunCount = maxRunCount;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
	}
	
	//constructor
	/**
	 * Creates a new {@link JobRunner} with the given job, maxRunCount and timeIntervalInMilliseconds.
	 * The {@link JobRunner} will start automatically.
	 * 
	 * @param job
	 * @param maxRunCount
	 * @param timeIntervalInMilliseonds
	 * @throws ArgumentIsNullException if the given job is null.
	 * @throws NegativeArgumentException if the given maxRunCount is negative.
	 * @throws NegativeArgumenteException if the given timeIntervalInMilliseconds is negative.
	 */
	public JobRunner(
		final IFunction job,
		final int maxRunCount,
		final int timeIntervalInMilliseconds
	) {
		
		//Checks if the given job is not null.
		Validator.suppose(job).thatIsNamed(VariableNameCatalogue.JOB).isNotNull();
		
		//Checks if the given maxRunCount is not negative.
		Validator.suppose(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given timeIntervalInMilliseconds is not negative.
		Validator.suppose(timeIntervalInMilliseconds).thatIsNamed("time interval in milliseconds").isNotNegative();
			
		this.job = job;
		this.maxRunCount = maxRunCount;
		condition = null;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
		
		start();
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} has caught an error.
	 */
	public boolean caughtError() {
		return (error != null);
	}
	
	//method
	/**
	 * @return the error of the current {@link JobRunner}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link JobRunner} does not have an error.
	 */
	public Throwable getError() {
		
		//Checks if the current JobRunner has an error.
		//For a better performance, this implementation does not use all comfortable methods.
		if (error == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ERROR);
		}
		
		return error;
	}
	
	//method
	/**
	 * @return the number of finished jobs of the current {@link JobRunner}.
	 */
	public int getFinishedJobCount() {
		return finishedJobCount;
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} has a condition.
	 */
	public boolean hasCondition() {
		return (condition != null);
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} has a max run count.
	 */
	public boolean hasMaxRunCount() {
		return (maxRunCount != null);
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} has a time interval.
	 */
	public boolean hasTimeInterval() {
		return (timeIntervalInMilliseconds != null);
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} is finished.
	 */
	public boolean isFinished() {
		return !isRunning();
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} is finished successfully.
	 */
	public boolean isFinishedSuccessfully() {
		return (isFinished() && !caughtError());
	}
	
	//method
	/**
	 * @return true if the current {@link JobRunner} is running.
	 */
	public boolean isRunning(){
		return running;
	}
	
	//method
	/**
	 * Lets the current {@link JobRunner} run.
	 */
	@Override
	public void run() {
		
		//main loop
		while (true) {
			
			//Handles the case that the current {@link JobRunner} does not have a max run count.
			if (!hasMaxRunCount()) {
				
				//Handles the case that the current {@link JobRunner} has a condition that is fulfilled.
				if (hasCondition() && !condition.getOutput()) {
					break;
				}
			}
			
			//Handles the case that the current {@link JobRunner} has a max run count.
			else {
				
				//Handles the case that the current {@link JobRunner} has reached its max run count.
				if (finishedJobCount >= maxRunCount) {
					break;
				}
				
				//Handles the case that the current {@link JobRunner} has a condition that is fulfilled.
				if (hasCondition() && !condition.getOutput()) {
					break;
				}
			}
			
			try {
				finishedJobCount++;
				job.run();
				
				//Handles the case that the current {@link JobRunner} has a time interval.
				if (hasTimeInterval()) {
					Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
				}
			}
			catch (final Throwable error) {
				this.error = error;
				Logger.logError(error);
				break;
			}
		}
		
		running = false;
	}
}
