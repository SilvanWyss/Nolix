//package declaration
package ch.nolix.element.time.timevalidator;

//own imports
import ch.nolix.element.time.base.Time;

//class
public final class TimeValidator {
	
	//method
	public static ExtendedTimeMediator assertThat(final Time time) {
		return new ExtendedTimeMediator(time);
	}
	
	//constructor
	private TimeValidator() {}
}
