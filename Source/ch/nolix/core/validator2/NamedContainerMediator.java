//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NonEmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A named container mediator is an argument mediator for an iterable object with a name.
 * A named container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 80
 * @param <E> The type of the elements of the argument of a named container mediator.
 */
public final class NamedContainerMediator<E> extends NamedArgumentMediator<Iterable<E>> {

	//package-visible constructor
	/**
	 * Creates new named container mediator for the given argument with the given argument name.
	 * 
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	NamedContainerMediator(final String argumentName, final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new named container mediator for the given argument with the given argument name.
	 * 
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	NamedContainerMediator(final String argumentName, final E[] argument) {
		
		//Calls constructor of the base class.
		super(argumentName, ArrayHelper.createIterable(argument));
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this named container mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this named container mediator is empty.
	 */
	public void isEmpty() {
		
		//Checks if the argument of this named container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this named container mediator is empty.
		if (IterableHelper.containsAny(getRefArgument())) {
			throw new NonEmptyArgumentException(new Argument(getRefArgument()));
		}
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this named container mediator is null.
	 * @throws EmptyArgumentException if the argument of this named container mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Checks if the argument of this named container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this named container mediator is not empty.
		if (IterableHelper.isEmpty(getRefArgument())) {
			throw new EmptyArgumentException(new Argument(getRefArgument()));
		}
	}
}
