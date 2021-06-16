//package declaration
package ch.nolix.common.errorcontrol.validator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.common.functionapi.IElementTakerBooleanGetter;

//class
/**
 * A {@link ArgumentMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 150
 * @param <A> is the type of the argument of an {@link ArgumentMediator}.
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
	protected ArgumentMediator(final A argument) {
		
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
	protected ArgumentMediator(final String argumentName, final A argument) {
		
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
		
		//Asserts that the given condition is not null.
		if (condition == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.CONDITION);
		}
				
		//Asserts that the argument of the current ArgumentMediator fulfills the given condition.
		if (!condition.getOutput(getRefArgument())) {
			throw
			new InvalidArgumentException(getArgumentName(), getRefArgument(), "does not fulfil the given condition");
		}
	}
	
	//method
	/**
	 * @param object
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link ArgumentMediator} does not equal the given object.
	 */
	public final void isEqualTo(final A object) {
		
		//Asserts that the argument of the current ArgumentMediator equals the given object.
		if ((argument == null && object != null) || !argument.equals(object)) {
			throw new UnequalArgumentException(argument, object);
		}
	}
	
	//method
	/**
	 * @param object
	 * @throws InvalidArgumentException if the argument of the current {@link ArgumentMediator} is the given object.
	 */
	public final void isNot(final Object object) {
		
		//Asserts that the argument of the current ArgumentMediator is not the given object.
		if (argument == object) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is the given object");
		}
	}
	
	//method
	/**
	 * @param object
	 * @throws InvalidArgumentException if
	 * the argument of the current {@link ArgumentMediator} equals the given object.
	 */
	public final void isNotEqualTo(final A object) {
		
		//Asserts that the argument of the current ArgumentMediator does not equal the given object.
		if ((argument == null && object == null) || argument.equals(object)) {
			throw new EqualArgumentException(argument, object);
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if the argument of the current {@link ArgumentMediator} is null.
	 */
	public final void isNotNull() {
		
		//Asserts that the argument of the current ArgumentMediator is not null.
		if (argument == null) {
			throw new ArgumentIsNullException(getArgumentName());
		}
	}
	
	//method
	/**
	 * @param type
	 * @throws ArgumentIsNullException if the argument of the current {@link ArgumentMediator} is null.
	 * @throws InvalidArgumentException
	 * if the argument of the current {@link ArgumentMediator} is not of the given type.
	 */
	public final void isOfType(final Class<?> type) {
		
		//Asserts that the argument of the current ArgumentMediator is not null.
		isNotNull();
		
		//Asserts that the argument of the current ArgumentMediator is of the given type.
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
