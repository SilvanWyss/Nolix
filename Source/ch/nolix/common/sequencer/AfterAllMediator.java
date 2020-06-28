//package declaration
package ch.nolix.common.sequencer;

//own imports
import ch.nolix.common.functionAPI.IBooleanGetter;
import ch.nolix.common.functionAPI.IAction;
import ch.nolix.common.validator.Validator;

//class
/**
 * An after all mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 220
 */
public final class AfterAllMediator {

	//attributes
	final int timeIntervalInMilliseconds;
	
	//optional attributes
	private final Integer maxRunCount;
	private final IBooleanGetter condition;
	
	//constructor
	/**
	 * Creates a new after all mediator with the given condition and time interval in milliseconds.
	 * 
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	AfterAllMediator(final IBooleanGetter condition, final int timeIntervalInMilliseconds) {
		
		//Asserts that the given condition is not null.
		Validator.assertThat(condition).thatIsNamed("condition").isNotNull();
		
		//Asserts that the given time interval in milliseconds is not negative.
		Validator
		.assertThat(timeIntervalInMilliseconds)
		.thatIsNamed("time interval in milliseconds")
		.isNotNegative();
		
		maxRunCount = null;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//constructor
	/**
	 * Creates a new after all mediator with the given time interval in milliseconds.
	 * 
	 * @param timeIntervalInMilliseconds
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	AfterAllMediator(final int timeIntervalInMilliseconds) {
	
		//Asserts that the given time interval in milliseconds is not negative.
		Validator
		.assertThat(timeIntervalInMilliseconds)
		.thatIsNamed("time interval in milliseconds")
		.isNotNegative();
		
		maxRunCount = null;
		condition = null;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//constructor
	/**
	 * Creates a new after all mediator with the given max run count, condition and time interval in milliseconds.
	 * 
	 * @param maxRunCount
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws NegativeArgumentException if the given max run count is negative.
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	AfterAllMediator(
		final int maxRunCount,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		//Asserts that the given max run count is not negative.
		Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Asserts that the given condition is not null.
		Validator.assertThat(condition).thatIsNamed("condition").isNotNull();
		
		//Asserts that the given time interval in milliseconds is not negative.
		Validator
		.assertThat(timeIntervalInMilliseconds)
		.thatIsNamed("time interval in milliseconds")
		.isNotNegative();
		
		this.maxRunCount = maxRunCount;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//method
	/**
	 * Lets this after all mediator run the given job.
	 * 
	 * @param job
	 */
	public void run(final IAction job) {
		
		//Handles the case that this after all mediator does not have a max run count.
		if (!hasMaxRunCount()) {
			runWhenDoesNotHaveRunCount(job);
		}
		
		//Handles the case that this after all mediator has a max run count.
		else {
			runWhenHasRunCount(job);
		}
	}
	
	/**
	 * Lets this after all mediator run the given job in background.
	 * 
	 * @param job
	 * @return a new future.
	 * @throws ArgumentIsNullException if the given job is null.
	 */
	public final Future runInBackground(final IAction job) {
		
		//Handles the case that this after all mediator does not have a max count.
		if (!hasMaxRunCount()) {
			
			//Handles the case that this after all mediator does not have a condition.
			if (!hasCondition()) {
				return new Future(new JobRunner(job, timeIntervalInMilliseconds, () -> true));
			}
			
			//Handles the case that this after all mediator has a condition.
			return new Future(new JobRunner(job, condition, timeIntervalInMilliseconds));
		}
		
		//Handles the case that this after all mediator has a max count.

			//Handles the case that this after all mediator does not have a condition.
			if (!hasCondition()) {
				return new Future(new JobRunner(job, maxRunCount, timeIntervalInMilliseconds));
			}
			
			//Handles the case that this after all mediator has a condition.
			return new Future(new JobRunner(job, maxRunCount, condition, timeIntervalInMilliseconds));
	}
	
	//method
	/**
	 * @return true if this after all mediator has a condition.
	 */
	private boolean hasCondition() {
		return (condition != null);
	}
	
	//method
	/**
	 * @return true if this after all mediator has a max run count.
	 */
	private boolean hasMaxRunCount() {
		return (maxRunCount != null);
	}
	
	//method
	/**
	 * Lets this after all mediator run the given job when this after all mediator does not have a run count.
	 * 
	 * @param job
	 */
	private void runWhenDoesNotHaveRunCount(IAction job) {
					
		//Handles the case that this after all mediator does not have a condition.
		if (!hasCondition()) {
			while (true) {
				job.run();
				Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
			}
		}
		
		//Handles the case of this after all mediator has a condition.
		else {
			while (condition.getOutput()) {
				job.run();
				Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
			}
		}
	}
	
	//method
	/**
	 * Lets this after all mediator run the given job when this after all mediator has a run count.
	 * 
	 * @param job
	 */
	private void runWhenHasRunCount(IAction job) {
		
		//Handles the case that this after all mediator does not have a condition.
		if (!hasCondition()) {
			for (int i = 1; i <= maxRunCount; i++) {
				job.run();
				Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
			}
		}
		
		//Handles the case that this after all mediator has a condition.
		else {
			for (int i = 1; i <= maxRunCount; i++) {
				
				if (!condition.getOutput()) {
					break;
				}
				
				job.run();
				Waiter.waitForMilliseconds(timeIntervalInMilliseconds);
			}
		}
	}
}
