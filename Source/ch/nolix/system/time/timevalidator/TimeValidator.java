//package declaration
package ch.nolix.system.time.timevalidator;

import ch.nolix.system.time.base.Time;

//class
public final class TimeValidator {
	
	//method
	public static ExtendedTimeMediator assertThat(final Time time) {
		return new ExtendedTimeMediator(time);
	}
	
	//constructor
	private TimeValidator() {}
}
