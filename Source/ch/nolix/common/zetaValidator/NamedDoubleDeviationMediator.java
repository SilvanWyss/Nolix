/*
 * file:	NamedMaxDeviationMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	50
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own import
import ch.nolix.common.exception.UnequalArgumentException;

//class
public final class NamedDoubleDeviationMediator extends NamedArgumentMediator {

	//attributes
	private final double argument;
	private final double maxDeviation;
	
	//constructor
	/**
	 * Creates new named 
	 * 
	 * @param argumentName
	 * @param argument
	 * @param maxDeviation
	 */
	public NamedDoubleDeviationMediator(
		final String argumentName,
		final Double argument, 
		final double maxDeviation) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Checks the given max deviation.
		ZetaValidator.supposeThat(maxDeviation).thatIsNamed("max deviation").isNotNegative();
		
		this.argument = argument;
		this.maxDeviation = maxDeviation;
	}
	
	//method
	/**
	 * @param value		The value the argument of this named double deviation mediator is supposed to equals to with a deviation that is not bigger than the max deviation of this named double deviation mediator.
	 * @throws UnequalArgumentException if the argument of this named double deviation mediator does not equal the given value with a deviation that is not bigger than the max deviation of this named double deviation mediator
	 */
	public void equals(final double value) {
		
		//Checks the argument of this named double deviation mediator.
		if (Math.abs(value - argument) > maxDeviation) {
			throw new UnequalArgumentException(getArgumentName(), value, argument);
		}
	}
	
	//method
	public void isZero() {
		equals(0.0);
	}
}
