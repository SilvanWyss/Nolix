//package declaration
package ch.nolix.common.sequencer;

import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.functionAPI.IIntTaker;
import ch.nolix.common.validator.Validator;

//class
/**
 * A for count mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 70
 */
public final class ForCountMediator {

	//attribute
	private final int maxRunCount;
	
	//constructor
	/**
	 * Creates a new for count mediator with the given max run count.
	 * 
	 * @param maxRunCount
	 * @throws NegativeArgumentException if the given max run count is negative.
	 */
	ForCountMediator(final int maxRunCount) {
		
		//Checks if the given max run count is not negative.
		Validator.suppose(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Sets the max run count of this for count mediator.
		this.maxRunCount = maxRunCount;
	}
	
	//method
	/**
	 * Lets this for count mediator run the given job.
	 * 
	 * @param job
	 */
	public void run(final IFunction job) {
		for (int i = 1; i <= maxRunCount; i++) {
			job.run();
		}
	}
	
	//method
	/**
	 * Lets this for count mediator run the given job.
	 * 
	 * @param job
	 */
	public void run(final IIntTaker job) {
		for (var i = 1; i <= maxRunCount; i++) {
			job.run(i);
		}
	}
	
	//method
	/**
	 * Lets this for count mediator run the given job in background.
	 * 
	 * @param job
	 * @return a new future.
	 */
	public Future runInBackground(final IFunction job) {
		return new Future(new JobRunner(job, maxRunCount));
	}
}
