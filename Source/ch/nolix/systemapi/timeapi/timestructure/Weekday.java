//package declaration
package ch.nolix.systemapi.timeapi.timestructure;

//Java imports
import java.time.DayOfWeek;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum Weekday {
	MONDAY,
	TUESDAY,
	WEDNESDAY,
	THURSDAY,
	FRIDAY,
	SATURDAY,
	SUNDAY;
	
	//static method
	public static Weekday fromDayOfWeek(final DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
			case MONDAY:
				return MONDAY;
			case TUESDAY:
				return TUESDAY;
			case WEDNESDAY:
				return WEDNESDAY;
			case THURSDAY:
				return THURSDAY;
			case FRIDAY:
				return FRIDAY;
			case SATURDAY:
				return SATURDAY;
			case SUNDAY:
				return SUNDAY;
			default:
				throw new IllegalArgumentException("The given day of week '" + dayOfWeek + "' is not valid.");
		}
	}
	
	//static method
	public static Weekday fromSpecification(final INode<?> specification) {
		return Weekday.valueOf(specification.getSingleChildNodeHeader());
	}
}
