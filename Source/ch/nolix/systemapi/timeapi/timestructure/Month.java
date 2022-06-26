//package declaration
package ch.nolix.systemapi.timeapi.timestructure;

import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.elementapi.mainuniversalapi.Specified;

//enum
public enum Month implements Specified {
	JANUARY,
	FEBRUARY,
	MARCH,
	APRIL,
	MAY,
	JUNE,
	JULY,
	AUGUST,
	SEPTEMBER,
	OCTOBER,
	NOVEMBER,
	DECEMBER;
	
	//static method
	public static Month fromJavaMonth(final java.time.Month month) {
		switch (month) {
			case JANUARY:
				return JANUARY;
			case FEBRUARY:
				return FEBRUARY;
			case MARCH:
				return MARCH;
			case APRIL:
				return APRIL;
			case MAY:
				return MAY;
			case JUNE:
				return JUNE;
			case JULY:
				return JULY;
			case AUGUST:
				return AUGUST;
			case SEPTEMBER:
				return SEPTEMBER;
			case OCTOBER:
				return OCTOBER;
			case NOVEMBER:
				return NOVEMBER;
			case DECEMBER:
				return DECEMBER;
			default:
				throw InvalidArgumentException.forArgument(month);
		}
	}
	
	//static method
	public Month fromSpecification(final BaseNode<?> specification) {
		return Month.valueOf(specification.getSingleChildNodeHeader());
	}
	
	//method
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		list.addAtEnd(Node.withHeader(name()));
	}
}
