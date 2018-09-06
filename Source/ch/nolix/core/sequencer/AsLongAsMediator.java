//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IBooleanGetter;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * An as long as mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 110
 */
public final class AsLongAsMediator {

	//attribute
	private final IBooleanGetter condition;
	
	//optional attribute
	private final Integer maxRunCount;
	
	//package-visible constructor
	/**
	 * Creates a new as long as mediator with the given condition.
	 * 
	 * @param condition
	 * @throws NullArgumentException if the given condition is not an instance.
	 */
	AsLongAsMediator(final IBooleanGetter condition) {
		
		//Checks if the given condition is an instance.
		Validator.suppose(condition).thatIsNamed("condition").isInstance();
		
		maxRunCount = null;
		this.condition = condition;
	}
	
	//method
	/**
	 * @param timeIntervalInMilliseconds
	 * @return a new after all mediator with the given time interval in milliseconds.
	 * @throws NegativeArgumentException if the given time interval in milliseconds is negative.
	 */
	public AfterAllMediator afterAllMilliseconds(final int timeIntervalInMilliseconds) {
		
		//Handles the case that this as long as mediator has no max run count.
		if (!hasMaxRunCount()) {
			return new AfterAllMediator(condition, timeIntervalInMilliseconds);
		}
		
		//Handles the case that this as long as mediator has a max run count.
		return new AfterAllMediator(maxRunCount, condition, timeIntervalInMilliseconds);
	}
	
	//method
	/**
	 * Lets this as long as mediator run the given job.
	 * 
	 * @param job
	 */
	public final void run(final IFunction job) {
		
		//Handles the case that this as long as mediator has no max run count.
		if (!hasMaxRunCount()) {
			while (condition.getOutput()) {
				job.run();
			}
		}
		
		//Handles the case that this as long as mediator has a max run count.
		else {
			for (int i = 1; i <= maxRunCount; i++) {
				
				if (!condition.getOutput()) {
					break;
				}
				
				job.run();
			}
		}
	}
	
	/**
	 * Lets this as long as mediator run the given job in background.
	 * 
	 * @param job
	 * @return a new future.
	 * @throws NullArgumentException if the given job is not an instance.
	 */
	public Future runInBackground(final IFunction job) {
		
		//Handles the case that this as long as mediator has no max run count.
		if (!hasMaxRunCount()) {
			return new Future(new JobRunner(job, condition));
		}
		
		//Handles the case that this as long as mediator has a max run count.
		return new Future(new JobRunner(job, maxRunCount, condition));
	}
	
	//method
	/**
	 * @return true if this as long as mediator has max run count.
	 */
	private boolean hasMaxRunCount() {
		return (maxRunCount != null);
	}
}
