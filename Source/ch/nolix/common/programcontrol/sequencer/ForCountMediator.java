//package declaration
package ch.nolix.common.programcontrol.sequencer;

import ch.nolix.common.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.functionapi.IIntTaker;

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
		
		//Asserts that the given max run count is not negative.
		Validator.assertThat(maxRunCount).thatIsNamed("max run count").isNotNegative();
		
		//Sets the max run count of this for count mediator.
		this.maxRunCount = maxRunCount;
	}
	
	//method
	/**
	 * Lets this for count mediator run the given job.
	 * 
	 * @param job
	 */
	public void run(final IAction job) {
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
	public Future runInBackground(final IAction job) {
		return new Future(new JobRunner(job, maxRunCount));
	}
}
