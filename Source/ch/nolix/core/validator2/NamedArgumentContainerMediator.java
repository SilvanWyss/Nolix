//package declaration
package ch.nolix.core.validator2;

//own import
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A named argument container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 50
 * @param <A> - The type of the arguments of a named argument container mediator.
 */
public class NamedArgumentContainerMediator<A> extends NamedMediator {

	//attribute
	private final Iterable<A> arguments;
	
	//package-visible constructor
	/**
	 * Creates new named argument container mediator with the given argument name.
	 * 
	 * @param argumentName
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 * @throws NullArgumentException if the given argument container is null.
	 */
	NamedArgumentContainerMediator(final String argumentName, final Iterable<A> arguments) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Checks if the given arugment container is not null.
		if (arguments == null) {
			throw new NullArgumentException("argument container");
		}
		
		//Sets the argument container of this named arugment container mediator.
		this.arguments = arguments;
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument container of this named argument container mediator is null.
	 */
	public final void isNotNullContainer() {
		
		//Checks if the argument container of this named argument container mediator is null.
		if (arguments == null) {
			throw new NullArgumentException("argument container");
		}
	}
}
