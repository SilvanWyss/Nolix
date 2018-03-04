//package declaration
package ch.nolix.core.enums;

//own import
import ch.nolix.core.specificationInterfaces.ISpecifiedEnum;

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
 * @lines 20
 */
public enum UniDirection implements ISpecifiedEnum {
	Horizontal,
	Vertical,
	DiagonalUp,
	DiagonalDown
}
