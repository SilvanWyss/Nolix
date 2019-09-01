//package declaration
package ch.nolix.common.validator;

import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.NullArgumentException;

//package-visible abstract class
/**
 * A mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 60
 */
abstract class Mediator {
	
	//default argument name
	private static final String DEFAULT_ARGUMENT_NAME = "Argument";
	
	//attribute
	private final String argumentName;
	
	//constructor
	/**
	 * Creates a new mediator with a default argument name.
	 */
	Mediator() {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME);
	}
	
	//constructor
	/**
	 * Creates a new argument mediator with the given argument name.
	 * 
	 * @param argumentName
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument name is empty.
	 */
	Mediator(final String argumentName) {
		
		//Checks if the given argument name is not null.
		if (argumentName == null) {
			throw new NullArgumentException("argument name");
		}
		
		//Checks if the given argument name is not empty.
		if (argumentName.isEmpty()) {
			throw new EmptyArgumentException("argument name", argumentName);
		}
		
		//Sets the argument name of this mediator.
		this.argumentName = argumentName;
	}
	
	//method
	/**
	 * @return the argument name of this argument mediator.
	 */
	protected final String getArgumentName() {
		return argumentName;
	}
}
