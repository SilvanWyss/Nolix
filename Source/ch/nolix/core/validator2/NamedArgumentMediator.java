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
 * A named argument mediator is a mediator for an argument with a name.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 80
 * @param <A> The type of the argument of a named argument mediator.
 */
public class NamedArgumentMediator<A> extends NamedMediator {

	//attribute
	private final A argument;
	
	//constructor
	/**
	 * Creates new named argument mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public NamedArgumentMediator(final String argumentName, final A argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Sets the argument of this named argument mediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this named argument mediator is null.
	 */
	public void isNotNull() {
		
		//Checks if the argument of this named argument mediator is null.
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
	 * @return the argument of this named argument mediator.
	 */
	protected A getRefArgument() {
		return argument;
	}
}
