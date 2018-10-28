//package declaration
package ch.nolix.core.validator2;

import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
* An extended container mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 50
* @param <E> The type of the elements of the argument of an extended container mediator.
*/
public class ExtendedContainerMediator<E> extends ContainerMediator<E> {

	//package-visible constructor
	/**
	 * Creates a new extended container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedContainerMediator(final E[] argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates a new extended container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedContainerMediator(final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param type
	 * @return a new container mediator
	 * for the argument of this extended container mediator.
	 */
	public final ContainerMediator<E> thatIsInstanceOf(final Class<?> type) {
		return new ContainerMediator<E>(type.getSimpleName(), getRefArgument());
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new container mediator
	 * with the given argument name for the argument of this extended container mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public ContainerMediator<E> thatIsNamed(final String argumentName) {
		return new ContainerMediator<E>(argumentName, getRefArgument());
	}
}
