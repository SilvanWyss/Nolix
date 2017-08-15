//package declaration
package ch.nolix.core.validator2;

//own import
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 10
 */
public final class NamedContainerMediator extends NamedArgumentMediator<Iterable<?>> {

	//package-visible constructor
	/**
	 * Creates new named container mediator with the given argument name and argument.
	 * 
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	NamedContainerMediator(final String argumentName, final Iterable<?> argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new named container mediator with the given argument name and argument.
	 * 
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	NamedContainerMediator(final String argumentName, final Object[] argument) {
		
		//Calls constructor of the base class.
		super(argumentName, ArrayHelper.createIterable(argument));
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
		if (!getRefArgument().iterator().hasNext()) {
			throw new EmptyArgumentException();
		}
	}
}
