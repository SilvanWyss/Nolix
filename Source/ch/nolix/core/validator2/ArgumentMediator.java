//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NotNullArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * An argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 120
 * @param <A> The type of the argument of an argument mediator.
 */
public class ArgumentMediator<A> {

	//attribute
	private final A argument;
	
	//package-visible constructor
	/**
	 * Creates new argument mediator with the given argument.
	 * 
	 * @param argument
	 */
	ArgumentMediator(final A argument) {
		
		//Sets the argument of this argument mediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @param condition
	 * @throws InvalidArgumentException if the argument of this argument mediator does not fulfil the given condition.
	 */
	public final void fulfils(final IElementTakerBooleanGetter<A> condition) {
		
		//Checks if the argument of this argument mediator fulfils the given condition.
		if (!condition.getOutput(getRefArgument())) {
			throw new InvalidArgumentException(
				new Argument(getRefArgument()),
				new ErrorPredicate("does not fulfil the given condition")
			);
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this element mediator is null.
	 */
	public final void isNotNull() {
		
		//Checks if the argument of this argument mediator is not null.
		if (getRefArgument() == null) {
			throw new NullArgumentException();
		}
	}
	
	//method
	/**
	 * @throws NotNullArgumentException if the argument of this argument mediator is not null.
	 */
	public final void isNull() {
		
		//Checks if the argument of this element mediator is null.
		if (getRefArgument() != null) {
			throw new NotNullArgumentException(new Argument(argument));
		}
	}
	
	//method
	/**
	 * @param type
	 * @throws NullArgumentException if the argument of this argument mediator is null.
	 * @throws InvalidArgumentException if the argument of this argument mediator is not of the given type.
	 */
	public final void isOfType(final Class<?> type) {
		
		//Checks if the argument of this argument mediator is not null.
		isNotNull();
		
		//Checks if the argument of this argument mediator is of the given type.
		if (!type.getClass().isAssignableFrom(getRefArgument().getClass())) {
			throw new InvalidArgumentException(
				new Argument(getRefArgument()),
				new ErrorPredicate("is no " + type)
			);
		}
	}
	
	//method
	/**
	 * @param type
	 * @return a new named argument mediator
	 * with the argument name of the given class and the argument of this argument mediator.
	 */
	public final NamedArgumentMediator<A> thatIsInstanceOf(final Class<?> type) {
		return new NamedArgumentMediator<A>(type.getSimpleName(), getRefArgument());
	}

	//method
	/**
	 * @param argumentName
	 * @return a new named argument mediator
	 * with the given argument name and the argument of this argument mediator.
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	public NamedArgumentMediator<A> thatIsNamed(final String argumentName) {
		return new NamedArgumentMediator<A>(argumentName, getRefArgument());
	}
	
	//method
	/**
	 * @return the argument of this argument mediator.
	 */
	protected final A getRefArgument() {
		return argument;
	}
}
