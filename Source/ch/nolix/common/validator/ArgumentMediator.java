//package declaration
package ch.nolix.common.validator;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.UnequalArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
 * A {@link ArgumentMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 140
 * @param <A> The type of the argument of an {@link ArgumentMediator}.
 */
public class ArgumentMediator<A> extends Mediator {
		
	//attribute
	private final A argument;
	
	//constructor
	/**
	 * Creates a new {@link ArgumentMediator} for the given argument.
	 * 
	 * @param argument
	 */
	ArgumentMediator(final A argument) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument);
	}
	
	//constructor
	/**
	 * Creates a new {@link ArgumentMediator} for the given argument, that has the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argumentName is null.
	 * @throws InvalidArgumentException if the given argument name is blank.
	 */
	ArgumentMediator(final String argumentName, final A argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Sets the argument of the current ArgumentMediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link ArgumentMediator} does not fulfill the given condition.
	 */
	public final void fulfills(IElementTakerBooleanGetter<A> condition) {
		
		//Checks if the given condition is not null.
		if (condition == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.CONDITION);
		}
				
		//Checks if the argument of the current ArgumentMediator fulfills the given condition.
		if (!condition.getOutput(getRefArgument())) {
			throw
			new InvalidArgumentException(getArgumentName(), getRefArgument(), "does not fulfil the given condition");
		}
	}
	
	//method
	/**
	 * @return a new {@link TerminalArgumentMediator} for the argument of the current {@link ArgumentMediator}.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link ArgumentMediator} does not equal the given object.
	 */
	public final TerminalArgumentMediator<A> isEqualTo(final A object) {
		
		//Checks if the argument of the current ArgumentMediator equals the given object.
		if (argument != null && !argument.equals(object)) {
			throw new UnequalArgumentException(argument, object);
		}
		
		return new TerminalArgumentMediator<>(getRefArgument());
	}
	
	//method
	/**
	 * @return a new {@link TerminalArgumentMediator} for the argument of the current {@link ArgumentMediator}.
	 * @throws InvalidArgumentException if the argument of the current {@link ArgumentMediator} is the given object.
	 */
	public final TerminalArgumentMediator<A> isNot(final Object object) {
		
		//Checks if the argument of the current ArgumentMediator is not the given object.
		if (argument == object) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is the given object");
		}
		
		return new TerminalArgumentMediator<>(getRefArgument());
	}
	
	//method
	/**
	 * @return a new {@link TerminalArgumentMediator} for the argument of the current {@link ArgumentMediator}.
	 * @throws ArgumentIsNullException if the argument of the current {@link ArgumentMediator} is null.
	 */
	public TerminalArgumentMediator<A> isNotNull() {
		
		//Checks if the argument of the current ArgumentMediator is not null.
		if (argument == null) {
			throw new ArgumentIsNullException(getArgumentName());
		}
		
		return new TerminalArgumentMediator<>(getRefArgument());
	}
	
	//method
	/**
	 * @param type
	 * @throws ArgumentIsNullException if the argument of the current {@link ArgumentMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link ArgumentMediator} is not of the given type.
	 */
	public final void isOfType(final Class<?> type) {
		
		//Checks if the argument of the current ArgumentMediator is not null.
		isNotNull();
		
		//Checks if the argument of the current ArgumentMediator is of the given type.
		if (!getRefArgument().getClass().getClass().isAssignableFrom(type.getClass())) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is not a " + type);
		}
	}
	
	//method
	/**
	 * @return the argument of the current {@link ArgumentMediator}.
	 */
	protected A getRefArgument() {
		return argument;
	}
}
