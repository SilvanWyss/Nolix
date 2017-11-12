//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
* An extended generic argument mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 50
* @param <A> The type of the argument of an extended generic argument mediator.
*/
public class ExtendedGenericArgumentMediator<A> extends GenericArgumentMediator<A> {

	//package-visible constructor
	/**
	 * Creates new extended generic argument mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedGenericArgumentMediator(final A argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param type
	 * @return a new generic argument mediator for the argument of this extended generic argument mediator.
	 */
	public final GenericArgumentMediator<A> thatIsInstanceOf(final Class<?> type) {
		return new GenericArgumentMediator<A>(type.getSimpleName(), getRefArgument());
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new generic argument mediator
	 * with the given argument name and for the argument of this extended generic argument mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public GenericArgumentMediator<A> thatIsNamed(final String argumentName) {
		return new GenericArgumentMediator<A>(argumentName, getRefArgument());
	}
}
