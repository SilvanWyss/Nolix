//package declaration
package ch.nolix.systemapi.timeapi.timestructure;

//Java imports
import java.time.DayOfWeek;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum Weekday implements Specified {
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
				throw InvalidArgumentException.forArgument(dayOfWeek);
		}
	}
	
	//static method
	public static Weekday fromSpecification(final BaseNode specification) {
		return Weekday.valueOf(specification.getSingleChildNodeHeader());
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(name()));
	}
}
