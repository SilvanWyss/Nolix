//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
* An extended container mediator is not mutable.
* 
* @author Silvan Wyss
* @month 2017-11
* @lines 50
*/
public class ExtendedContainerMediator<E> extends ContainerMediator<E> {

	//package-visible constructor
	/**
	 * Creates new extended container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedContainerMediator(final E[] argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new extended container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ExtendedContainerMediator(final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argument);
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
