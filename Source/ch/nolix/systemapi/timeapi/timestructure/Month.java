//package declaration
package ch.nolix.systemapi.timeapi.timestructure;

import ch.nolix.coreapi.documentapi.nodeapi.INode;

//enum
public enum Month {
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
				throw new IllegalArgumentException("The given month '" + month + "' is not valid.");
		}
	}
	
	//static method
	public Month fromSpecification(final INode<?> specification) {
		return Month.valueOf(specification.getSingleChildNodeHeader());
	}
}
