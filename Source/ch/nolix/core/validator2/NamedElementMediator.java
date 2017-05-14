//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 50
 * @param <E> - The type of the element of a namd element mediator.
 */
abstract class NamedElementMediator<E> extends NamedArgumentMediator {

	//attribute
	private final E argument;
	
	//constructor
	/**
	 * Creates new named element mediator with the given argument name and the given argument.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is an empty string.
	 */
	public NamedElementMediator(final String argumentName, final E argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this named element mediator is null.
	 */
	public void isNotNull() {
		
		//Checks if the argument of this named element mediator is null.
		if (argument == null) {
			throw new NullArgumentException(getArgumentName());
		}
	}
	
	//method
	/**
	 * @return the argument of this named element mediator.
	 */
	protected E getArgument() {
		return argument;
	}
}
