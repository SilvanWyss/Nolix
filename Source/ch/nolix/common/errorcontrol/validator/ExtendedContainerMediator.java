//package declaration
package ch.nolix.common.errorcontrol.validator;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
* An extended container mediator is not mutable.
* 
* @author Silvan Wyss
* @date 2017-11-12
* @lines 50
* @param <E> is the type of the elements of the argument of an extended container mediator.
*/
public class ExtendedContainerMediator<E> extends ContainerMediator<E> {

	//constructor
	/**
	 * Creates a new extended container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedContainerMediator(final E[] argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//constructor
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
		return new ContainerMediator<>(type.getSimpleName(), getRefArgument());
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new container mediator
	 * with the given argument name for the argument of this extended container mediator.
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public ContainerMediator<E> thatIsNamed(final String argumentName) {
		return new ContainerMediator<>(argumentName, getRefArgument());
	}
}
