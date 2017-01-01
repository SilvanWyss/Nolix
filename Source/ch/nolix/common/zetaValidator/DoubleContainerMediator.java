/*
 * file:	DoubleContainerMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	10
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.NonNegativeArgumentException;
import ch.nolix.common.exception.NonPositiveArgumentException;

//class
public final class DoubleContainerMediator {

	//attribute
	private final Iterable<Double> arguments;
	
	//constructor
	/**
	 * Creates new double container mediator with the given arguments.
	 * 
	 * @param arguments
	 */
	public DoubleContainerMediator(Iterable<Double> arguments) {
		this.arguments = arguments;
	}
	
	public final void areNegative() {
		
		//Handles the case if the arguments of this double container mediator are null.
		throwExceptionIfArguemntsAreNull();
		
		//Handles the case if the arguments of this double container mediator are not null.
		for (double a: arguments) {
			
			//Checks the current argument.
			if (a > 0) {
				throw new NonNegativeArgumentException(a);
			}
		}
	}

	public final void arePositive() {
		
		//Handles the case if the arguments of this double container mediator are null.
		throwExceptionIfArguemntsAreNull();
		
		//Handles the case if the arguments of this double container mediator are not null.
		for (double a: arguments) {
			
			//Checks the current argument.
			if (a <= 0) {
				throw new NonPositiveArgumentException(a);
			}
		}
	}
	
	//method
	/**
	 * @throws RuntimeException if the arguemtns of this double container mediator are null
	 */
	private final void throwExceptionIfArguemntsAreNull() {
		
		//Checks the arguments of this double container mediator.
		if (arguments == null) {
			throw new RuntimeException("The given arguments are null.");
		}
	}
}
