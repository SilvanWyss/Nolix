//package declaration
package ch.nolix.common.errorcontrol.validator;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
/**
 * A string container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-12
 * @lines 90
 */
public final class MultiStringMediator extends MultiArgumentMediator<String> {

	//constructor
	/**
	 * Creates a new string container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given argument container is null.
	 */
	MultiStringMediator(final Iterable<String> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	//constructor
	/**
	 * Creates a new string container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given argument container is null.
	 */
	MultiStringMediator(final String[] arguments) {
		
		//Calls method of the base class.
		super(arguments);
	}
	
	//method
	/**
	 * for the arguments of the current {@link MultiStringMediator}.
	 * @throws ArgumentIsNullException
	 * if one of the arguments of the current {@link MultiStringMediator} is null.
	 * @throws InvalidArgumentException
	 * if one of the arguments of the current {@link MultiStringMediator} is blank.
	 */
	public void areNotBlank() {
		
		//Asserts that the arguments of the current multi string mediator are not null.
		areNotNull();
		
		//Iterates the arguments of the current multi string mediator.
		var index = 1;
		for (final var a : getRefArguments()) {
						
			//Asserts that the current argument is not blank.
			if (a.isBlank()) {
				throw new InvalidArgumentException(index + "th argument", a, "is blank");
			}
			
			//Increments index.
			index++;
		}
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if one of the arguments of this strinc container mediator is null.
	 * @throws EmptyArgumentException if one of the arguments of this string container mediator is empty.
	 */
	public void areNotEmpty() {
		
		//Asserts that the arguments of this string container mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this string container mediator.
		int index = 1;
		for (final String a : getRefArguments()) {
						
			//Asserts that the current argument is not empty.
			if (a.isEmpty()) {
				throw new EmptyArgumentException(index + "th argument", a);
			}
			
			//Increments index.
			index++;
		}
	}
}
