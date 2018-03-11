//package declaration
package ch.nolix.primitive.validator2;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.EmptyArgumentException;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

//class
/**
 * A argument mediator is a mediator for an argument with a name.
 * A argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 120
 * @param <A> The type of the argument of a argument mediator.
 */
public class ArgumentMediator<A> extends Mediator {

	//constant
	public static final String DEFAULT_ARGUMENT_NAME = "Argument";
	
	//attribute
	private final A argument;
	
	//package-visible constructor
	/**
	 * Creates new argument mediator for the given argument.
	 * 
	 * @param argument
	 */
	ArgumentMediator(final A argument) {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME, argument);
	}
	
	//package-visible constructor
	/**
	 * Creates new argument mediator for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	ArgumentMediator(final String argumentName, final A argument) {
		
		//Calls constructor of the base class.
		super(argumentName);
		
		//Sets the argument of this argument mediator.
		this.argument = argument;
	}
	
	//method
	/**
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 * @throws InvalidArgumentException if the argument of this argument mediator is null.
	 */
	public final void fulfils(IElementTakerBooleanGetter<A> condition) {
		
		//Checks if the given condition is not null.
		if (condition == null) {
			throw new NullArgumentException(VariableNameCatalogue.CONDITION);
		}
		
		//Checks if the argument of this argument mediator is not null.
		isNotNull();
		
		if (!condition.getOutput(getRefArgument())) {
			throw new InvalidArgumentException(
				new ArgumentName(getArgumentName()),
				new Argument(getRefArgument()),
				new ErrorPredicate("does not fulfil the given condition"));
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this argument mediator is null.
	 */
	public final void isNotNull() {
		
		//Checks if the argument of this argument mediator is null.
		if (argument == null) {
			throw new NullArgumentException(getArgumentName());
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
				new ArgumentName(getArgumentName()),
				new Argument(getRefArgument()),
				new ErrorPredicate("is no " + type)
			);
		}
	}
	
	//method
	/**
	 * @return the argument of this argument mediator.
	 */
	protected A getRefArgument() {
		return argument;
	}
}
