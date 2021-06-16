//package declaration
package ch.nolix.element.time.timevalidator;

//own imports
import ch.nolix.element.time.base.Time;

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
