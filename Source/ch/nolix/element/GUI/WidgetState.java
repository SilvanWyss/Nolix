//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.specification.StandardSpecification;

//enum
/**
 * A widget state defines the state of a widget.
 * 
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 30
 */
public enum WidgetState {
	Normal,
	Hovered,
	Focused,
	Disabled,
	Collapsed;
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "WidgetState";
	
	//method
	/**
	 * @return the specification of this widget state.
	 */
	public StandardSpecification getSpecification() {
		return new StandardSpecification(SIMPLE_CLASS_NAME, toString());
	}
}
