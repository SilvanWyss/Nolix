//package declaration
package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public class BitMediator extends Mediator {
	
	//attribute
	private final boolean argument;
	
	//constructor
	BitMediator(final boolean argument) {
		this.argument = argument;
	}
	
	//constructor
	BitMediator(final String argumentName, final boolean argument) {
		
		super(argumentName);
		
		this.argument = argument;
	}
	
	//method
	public final void isCleared() {
		if (argument) {
			throw new InvalidArgumentException(getArgumentName(), argument, "is not cleared");
		}
	}
	
	//method
	public final void isSet() {
		if (!argument) {
			throw new InvalidArgumentException(getArgumentName(), argument, "is not set");
		}
	}
}
