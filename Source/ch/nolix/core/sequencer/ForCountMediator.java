//package declaration
package ch.nolix.core.sequencer;

//own imports
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 60
 */
public final class ForCountMediator {

	//attribute
	private final int count;
	
	//package-visible constructor
	/**
	 * Creates new doer with the given count.
	 * 
	 * @param count
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	ForCountMediator(final int count) {
		
		//Checks if the given count is not negative.
		Validator.supposeThat(count).thatIsNamed("count").isNotNegative();
		
		//Sets the count of this count mediator.
		this.count = count;
	}
	
	//method
	/**
	 * Lets this for count mediator run the given job
	 * as many times as the count of this for count mediator defines.
	 * 
	 * @param job
	 */
	public void run(final IRunner job) {
		for (int i = 1; i <= count; i++) {
			job.run();
		}
	}
	
	/**
	 * Lets this for count mediator run the given job in background
	 * as many times as the count of this for count mediator defines.
	 * 
	 * @param job
	 * @return a new Future for the given job.
	 */
	public Future runInBackground(final Runner job) {
		return new Future(
			new BackgroundThread(
				() -> {
					for (int i = 1; i <= count; i++) {
						job.run();
					}
				}
			)
		);
	}
}
