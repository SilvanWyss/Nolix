//package declaration
package ch.nolix.common.validator;

//own imports
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
* An extended double mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 40
*/
public class ExtendedDoubleMediator extends DoubleMediator {

	//package-visibel constructor
	/**
	 * Creates a new extended double mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedDoubleMediator(final double argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new double mediator with the given argument name for the argument of this extended double mediator.
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public DoubleMediator thatIsNamed(final String argumentName) {
		return new DoubleMediator(argumentName, getArgument());
	}
}
