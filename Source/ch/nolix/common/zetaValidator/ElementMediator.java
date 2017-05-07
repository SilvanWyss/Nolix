//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.invalidArgumentException.Argument;
import ch.nolix.common.invalidArgumentException.NotNullArgumentException;
import ch.nolix.common.invalidArgumentException.NullArgumentException;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 70
 *
 * @param <E> - The type of the element of an element mediator.
 */
abstract class ElementMediator<E> {

	//attribute
	private final E argument;
	
	//constructor
	/**
	 * Creates new element mediator with the given argument.
	 * 
	 * @param argument
	 */
	public ElementMediator(final E argument) {
		this.argument = argument;
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this element mediator is null.
	 */
	public final void isNotNull() {
		
		//Checks if the argument of this element mediator is not null.
		if (argument == null) {
			throw new NullArgumentException();
		}
	}
	
	//method
	/**
	 * @throws NotNullArgumentException if the argument of this element mediator is not null.
	 */
	public final void isNull() {
		
		//Checks if the argument of this element mediator is null.
		if (argument != null) {
			throw new NotNullArgumentException(new Argument(argument));
		}
	}
	
	//method
	/**
	 * @return the argument of this element mediator.
	 */
	protected final E getRefArgument() {
		return argument;
	}
}
