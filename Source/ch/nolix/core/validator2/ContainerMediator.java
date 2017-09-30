//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NonEmptyArgumentException;

//class
/**
 * A container mediator is an argument mediator for an an iterable object.
 * A container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 80
 * @param <E> The type of the elements of the argument of a container mediator.
 */
public final class ContainerMediator<E> extends ArgumentMediator<Iterable<E>> {

	//package-visible constructor
	/**
	 * Creates new container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final E[] argument) {
		
		//Calls constructor of the base class.
		super(ArrayHelper.createIterable(argument));
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this container mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isEmpty() {
		
		//Checks if the argument of this container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this container mediator is empty.
		if (IterableHelper.containsAny(getRefArgument())) {
			throw new NonEmptyArgumentException(new Argument(getRefArgument()));
		}
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this container mediator is null.
	 * @throws EmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Checks if the argument of this container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this container mediator is not empty.
		if (IterableHelper.isEmpty(getRefArgument())) {
			throw new EmptyArgumentException(new Argument(getRefArgument()));
		}
	}
	
	//method
	/**
	 * @param argumentName
	 * @return a new named container mediator with the given argument name and for the argument of this container mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public NamedContainerMediator<E> thatIsNamed(final String argumentName) {
		return new NamedContainerMediator<E>(argumentName, getRefArgument());
	}
}
