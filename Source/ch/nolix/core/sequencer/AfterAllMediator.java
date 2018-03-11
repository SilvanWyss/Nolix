//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * An after all mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 200
 */
public final class AfterAllMediator {

	//attributes
	final int timeIntervalInMilliseconds;
	
	//optional attributes
	private final Integer maxRunCount;
	private final IBooleanGetter condition;
	
	//package-visible constructor
	/**
	 * Creates new after all mediator with the given condition and time interval in milliseconds.
	 * 
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws NullArgumentException if the given condition is null.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	AfterAllMediator(final IBooleanGetter condition, final int timeIntervalInMilliseconds) {
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed("condition").isNotNull();
		
		//Checks if the given time interval in milliseconds is not negative.
		Validator
		.suppose(timeIntervalInMilliseconds)
		.thatIsNamed("time interval in milliseconds")
		.isNotNegative();
		
		maxRunCount = null;
		this.condition = condition;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//package-visible constructor
	/**
	 * Creates new after all mediator with the given time interval in milliseconds.
	 * 
	 * @param timeIntervalInMilliseconds
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	AfterAllMediator(final int timeIntervalInMilliseconds) {
	
		//Checks if the given time interval in milliseconds is not negative.
		Validator
		.suppose(timeIntervalInMilliseconds)
		.thatIsNamed("time interval in milliseconds")
		.isNotNegative();
		
		maxRunCount = null;
		condition = null;
		this.timeIntervalInMilliseconds = timeIntervalInMilliseconds;
	}
	
	//package-visible constructor
	/**
	 * Creates new after all mediator with the given max run count, condition and time interval in milliseconds.
	 * 
	 * @param maxRunCount
	 * @param condition
	 * @param timeIntervalInMilliseconds
	 * @throws NegativeArgumentException if the given max run count is negative.
	 * @throws NullArgumentException if the given condition is null.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	AfterAllMediator(
		final int maxRunCount,
		final IBooleanGetter condition,
		final int timeIntervalInMilliseconds
	) {
		//Checks if the given max run count is not negative.
		Validator.suppose(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Checks if the given condition is not null.
		Validator.suppose(condition).thatIsNamed("condition").isNotNull();
		
		//Checks if the given time interval in milliseconds is not negative.
		Validator
		.suppose(timeIntervalInMilliseconds)
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
	public void run(final IFunction job) {
		
		//Handles the case that this after all mediator has no max run count.
		if (!hasMaxRunCount()) {
			
			//Handles the case that this after all mediator has no condition.
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
		
		//Handles the case that this after all mediator has a max run count.
		else {
			
			//Handles the case that this after all mediator has no condition.
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
	
	/**
	 * Lets this after all mediator run the given job in background.
	 * 
	 * @param job
	 * @return a new future.
	 * @throws NullArgumentException if the given job is null.
	 */
	public final Future runInBackground(final IFunction job) {
		
		//Handles the case that this after all mediator has no max count.
		if (!hasMaxRunCount()) {
			
			//Handles the case that this after all mediator has no condition.
			if (!hasCondition()) {
				return new Future(new JobRunner(job, timeIntervalInMilliseconds, false /*pseudo value*/));
			}
			
			//Handles the case that this after all mediator has a condition.
			return new Future(new JobRunner(job, condition, timeIntervalInMilliseconds));
		}
		
		//Handles the case that this after all mediator has a max count.

			//Handles the case that this after all mediator has no condition.
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
}
