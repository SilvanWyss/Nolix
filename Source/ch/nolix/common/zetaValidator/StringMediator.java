/*
 * file:	StringMediator.java
 * author:	Silvan Wyss
 * month:	2016-12
 * lines:	80
 */

//package declaration
package ch.nolix.common.zetaValidator;

//own imports
import ch.nolix.common.exception.ArgumentException;
import ch.nolix.common.exception.EmptyArgumentException;
import ch.nolix.common.exception.NonEmptyArgumentException;
import ch.nolix.common.exception.NullArgumentException;

//class
public final class StringMediator extends ElementMediator<String> {

	//constructor
	/**
	 * Creates new string mediator with the given argument.
	 * 
	 * @param argument		The argument of this string mediator.
	 */
	public StringMediator(final String argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//method
	/**
	 * @param maxLength		The max length the argument of this string mediator is supposed to have.
	 * @throws ArgumentException if the argument of this string mediator has a bigger length than the given max length
	 */
	public void hasMaxLength(final int maxLength) {
		
		//Checks the argument of this string mediator.
		if (getArgument().length() > maxLength) {
			throw new ArgumentException(getArgument(), "has a bigger than length than " + maxLength + ".");
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null
	 * @throws EmptyArgumentException if the argument of this string mediator is not empty
	 */
	public void isEmpty() {
		
		//Checks the argument of this string mediator.
		isNotNull();
		if (!getArgument().isEmpty()) {
			throw new NonEmptyArgumentException(getArgument());
		}
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this string mediator is null
	 * @throws EmptyArgumentException if the argument of this string mediator is empty
	 */
	public void isNotEmpty() {
		
		//Checks the argument of this string mediator.
		isNotNull();
		if (getArgument().isEmpty()) {
			throw new EmptyArgumentException();
		}
	}
	
	//method
	/**
	 * @param argumentName		The name of the argument of the created named string mediator.
	 * @return a new named string mediator with the given argument name and the argument of this string mediator
	 */
	public NamedStringMediator thatIsNamed(final String argumentName) {
		return new NamedStringMediator(argumentName, getArgument());
	}
}
