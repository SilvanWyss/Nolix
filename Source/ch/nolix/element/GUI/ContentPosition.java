//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.specification.StandardSpecification;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public enum ContentPosition {
	LeftTop,
	Left,
	LeftBottom,
	Top,
	Center,
	Bottom,
	RightTop,
	Right,
	RightBottom;
	
	//type name
	public static final String TYPE_NAME = "ContentPosition";
	
	//method
	/**
	 * @return the specification of this content position.
	 */
	public final StandardSpecification getSpecification() {
		return new StandardSpecification(TYPE_NAME, toString());
	}
}
