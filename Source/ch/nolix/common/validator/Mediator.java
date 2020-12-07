//package declaration
package ch.nolix.common.validator;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;

//class
/**
 * A {@link Mediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 */
abstract class Mediator {
	
	//constant
	public static final String DEFAULT_ARGUMENT_NAME = "argument";
	
	//attribute
	private final String argumentName;
	
	//constructor
	/**
	 * Creates a new {@link Mediator} with a default argument name.
	 */
	public Mediator() {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME);
	}
	
	//constructor
	/**
	 * Creates a new argument {@link Mediator} with the given argumentName.
	 * 
	 * @param argumentName
	 * @throws ArgumentIsNullException if the given argumentName is null.
	 * @throws InvalidArgumentException if the given argumentName is blank.
	 */
	public Mediator(final String argumentName) {
		
		//Asserts that the given argumentName is not null.
		if (argumentName == null) {
			throw new ArgumentIsNullException("argument name");
		}
		
		//Asserts that the given argumentName is not blank.
		if (argumentName.isBlank()) {
			throw new InvalidArgumentException("argument name", argumentName, "is blank");
		}
		
		//Sets the argumentName of the current Mediator.
		this.argumentName = argumentName;
	}
	
	//method
	/**
	 * @return the argument name of the current {@link Mediator}.
	 */
	protected final String getArgumentName() {
		return argumentName;
	}
}
