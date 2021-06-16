//package declaration
package ch.nolix.element.time.timevalidator;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.ArgumentMediator;
import ch.nolix.element.time.base.Time;

//class
public class TimeMediator extends ArgumentMediator<Time> {
	
	//constructor
	TimeMediator(final String argumentName, final Time argument) {
		super(argumentName, argument);
	}
	
	//constructor
	TimeMediator(final Time argument) {
		super(argument);
	}
	
	//method
	public final void isAfter(final Time time) {
		
		isNotNull();
		
		if (!getRefArgument().isAfter(time)) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is not after " + time);
		}
	}
	
	//method
	public final void isBefore(final Time time) {
		
		isNotNull();
		
		if (!getRefArgument().isBefore(time)) {
			throw new InvalidArgumentException(getArgumentName(), getRefArgument(), "is not before " + time);
		}
	}
}
