//package declaration
package ch.nolix.system.time.timevalidator;

import ch.nolix.system.time.base.Time;

//class
public final class ExtendedTimeMediator extends TimeMediator {
	
	//constructor
	ExtendedTimeMediator(final Time argument) {
		super(argument);
	}
	
	//method
	public TimeMediator thatIsNamed(final String argumentName) {
		return new TimeMediator(argumentName, getRefArgument());
	}
}
