//package declaration
package ch.nolix.primitive.validator2;

import ch.nolix.primitive.invalidArgumentException.EmptyArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

//class
/**
* An extended generic argument mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 50
* @param <A> The type of the argument of an extended generic argument mediator.
*/
public class ExtendedArgumentMediator<A> extends ArgumentMediator<A> {

	//package-visible constructor
	/**
	 * Creates a new extended generic argument mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedArgumentMediator(final A argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param type
	 * @return a new generic argument mediator for the argument of this extended generic argument mediator.
	 */
	public final ArgumentMediator<A> thatIsOfType(final Class<?> type) {
		return new ArgumentMediator<A>(type.getSimpleName(), getRefArgument());
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new generic argument mediator
	 * with the given argument name and for the argument of this extended generic argument mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public ArgumentMediator<A> thatIsNamed(final String argumentName) {
		return new ArgumentMediator<A>(argumentName, getRefArgument());
	}
}
