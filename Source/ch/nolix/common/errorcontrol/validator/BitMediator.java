//package declaration
package ch.nolix.common.errorcontrol.validator;

//own import
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;

//class
public class BitMediator extends Mediator {
	
	//attribute
	private final boolean argument;
	
	//visibility-reduced constructor
	BitMediator(final boolean argument) {
		this.argument = argument;
	}
	
	//visibility-reduced constructor
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
