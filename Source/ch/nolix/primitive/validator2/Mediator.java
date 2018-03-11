//package declaration
package ch.nolix.primitive.validator2;

import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.EmptyArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

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
	 * Creates new mediator with a default argument name.
	 */
	Mediator() {
		
		//Calls other constructor.
		this(DEFAULT_ARGUMENT_NAME);
	}
	
	//constructor
	/**
	 * Creates new argument mediator with the given argument name.
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
			throw new EmptyArgumentException(new ArgumentName("argument name"));
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
