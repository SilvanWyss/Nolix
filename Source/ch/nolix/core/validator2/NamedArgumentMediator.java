//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//package-visible abstract class
/**
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 * @param <E> - The type of the element of a namd element mediator.
 */
public class NamedArgumentMediator<E> extends NamedMediator {

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
	public NamedArgumentMediator(final String argumentName, final E argument) {
		
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
	 * @param type
	 * @throws NullArgumentException if the argument of this named argument mediator is null.
	 * @throws InvalidArgumentException if the argument of this named argument mediator is not of the given type.
	 */
	public final void isOfType(final Class<?> type) {
		
		//Checks if the argument of this argument mediator is not null.
		isNotNull();
		
		//Checks if the argument of this argument mediator is of the given type.
		if (!type.getClass().isAssignableFrom(getRefArgument().getClass())) {
			throw new InvalidArgumentException(
				new ArgumentName(getArgumentName()),
				new Argument(getRefArgument()),
				new ErrorPredicate("is no " + type)
			);
		}
	}
	
	//method
	/**
	 * @return the argument of this named element mediator.
	 */
	protected E getRefArgument() {
		return argument;
	}
}
