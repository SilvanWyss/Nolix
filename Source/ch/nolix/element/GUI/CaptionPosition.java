//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * @author Silvan Wyss
 * @month 2016-04
 * @lines 30
 */
public enum CaptionPosition implements SpecifiedByClassNameAndOneAttribute {
	LeftTop,
	Left,
	LeftBottom,
	RightTop,
	Right,
	RightBottom,
	TopLeft,
	Top,
	TopRight,
	BottomLeft,
	Bottom,
	BottomRight;

	//method
	/**
	 * @return the attribute of this caption position.
	 */
	public final StandardSpecification getAttribute() {
		return StandardSpecification.createSpecificationWithHeaderOnly(toString());
	}
}
