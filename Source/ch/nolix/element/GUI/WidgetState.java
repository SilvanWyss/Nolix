/*
 * file:	RectangleState.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	30
 */

//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.specification.StandardSpecification;

//enum
public enum WidgetState {
	Normal,
	Focused,
	Active,
	Hovered,
	HoverFocused,
	HoverActive,
	Disabled,
	Invisible,
	Collapsed;
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "RectangleState";
	
	//method
	/**
	 * @return the specification of this rectangle state
	 */
	public StandardSpecification getSpecification() {
		return new StandardSpecification(SIMPLE_CLASS_NAME, toString());
	}
}
