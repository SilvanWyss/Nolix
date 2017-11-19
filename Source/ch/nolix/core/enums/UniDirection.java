//package declaration
package ch.nolix.core.enums;

//own imports
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.SpecifiedByClassNameAndOneAttribute;

//enum
/**
 * An uni direction defines the way
 * between two points of a square.
 * 
 * An uni direction does not (!) depend
 * on the order of the start point and end point.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 30
 */
public enum UniDirection implements SpecifiedByClassNameAndOneAttribute {
	Horizontal,
	Vertical,
	DiagonalUp,
	DiagonalDown;

	//method
	/**
	 * @return the attribute of this uni direction.
	 */
	public final StandardSpecification getAttribute() {
		return StandardSpecification.createSpecificationWithHeaderOnly(toString());
	}
}
