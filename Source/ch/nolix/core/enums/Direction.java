//package declaration
package ch.nolix.core.enums;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * A direction defines the way
 * from a square's point to another point of the square.
 * 
 * A direction depends on the order of the start point and the end point.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 40
 */
public enum Direction implements SpecifiedByClassNameAndOneAttribute {
	LeftToRight,
	RightToLeft,
	TopToBottom,
	BottomToTop,
	TopLeftToBottomRight,
	BottomRightToTopLeft,
	TopRightToBottomLeft,
	BottomLeftToTopRight;
	
	//method
	/**
	 * @return the attribute of this direction.
	 */
	public final StandardSpecification getAttribute() {
		return
		StandardSpecification.createSpecificationWithHeaderOnly(super.toString());
	}
	
	//method
	/**
	 * @return a string representation of this direction.
	 */
	public final String toString() {
		return getSpecification().toString();
	}
}
