/*
 * file:	Doer.java
 * author:	Silvan Wyss
 * month:	2016-05
 * lines:	40
 */

//package declaration
package ch.nolix.core.sequencer;

//own import
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.validator2.Validator;

//class
public final class ForCountMediator {

	//attribute
	private final int count;
	
	//constructor
	/**
	 * Creates new doer with the given count.
	 * 
	 * @param count
	 * @throws NegativeArgumentException if the given count is negative.
	 */
	public ForCountMediator(final int count) {
		
		//Checks if the given count is not negative.
		Validator.supposeThat(count).thatIsNamed("count").isNotNegative();
		
		//Sets the count of this count mediator.
		this.count = count;
	}
	
	//method
	/**
	 * Lets the given doer do something as many times as the count of this doer.
	 * 
	 * @param doer
	 */
	public final void run(IRunner doer) {
		for (int i = 1; i <= count; i++) {
			doer.run();
		}
	}
}
