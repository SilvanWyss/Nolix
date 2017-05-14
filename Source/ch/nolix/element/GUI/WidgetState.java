/*
 * file:	RectangleState.java
 * author:	Silvan Wyss
 * month:	2016-04
 * lines:	30
 */

//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.specification.Specification;

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
	public Specification getSpecification() {
		return new Specification(SIMPLE_CLASS_NAME, toString());
	}
}
