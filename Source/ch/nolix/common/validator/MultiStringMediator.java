//package declaration
package ch.nolix.common.validator;

import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

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
	 * @return a new {@link TerminalArgumentMediator}
	 * for the arguments of the current {@link MultiStringMediator}.
	 * @throws ArgumentIsNullException
	 * if one of the arguments of the current {@link MultiStringMediator} is null.
	 * @throws InvalidArgumentException
	 * if one of the arguments of the current {@link MultiStringMediator} is blank.
	 */
	public TerminalArgumentMediator<Iterable<String>> areNotBlank() {
		
		//Checks if the arguments of the current multi string mediator are not null.
		areNotNull();
		
		//Iterates the arguments of the current multi string mediator.
		var index = 1;
		for (final var a : getRefArguments()) {
						
			//Checks if the current argument is not blank.
			if (a.isBlank()) {
				throw new InvalidArgumentException(index + "th argument", a, "is blank");
			}
			
			//Increments index.
			index++;
		}
		
		//Creates new terminal argument mediator for the arguments of the current multi string mediator.
		return new TerminalArgumentMediator<>(getRefArguments());
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if one of the arguments of this strinc container mediator is null.
	 * @throws EmptyArgumentException if one of the arguments of this string container mediator is empty.
	 */
	public void areNotEmpty() {
		
		//Checks if the arguments of this string container mediator are not null.
		areNotNull();
		
		//Iterates the arguments of this string container mediator.
		int index = 1;
		for (final String a : getRefArguments()) {
						
			//Checks if the current argument is not empty.
			if (a.isEmpty()) {
				throw new EmptyArgumentException(index + "th argument", a);
			}
			
			//Increments index.
			index++;
		}
	}
}
