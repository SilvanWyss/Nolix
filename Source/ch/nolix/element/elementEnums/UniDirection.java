//package declaration
package ch.nolix.element.elementEnums;

import ch.nolix.core.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * A {@link UniDirection} defines the way
 * between two points of a square.
 * 
 * A {@link UniDirection} does not (!) depend
 * on the order of the start point and end point.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public enum UniDirection implements IElementEnum {
	Horizontal,
	Vertical,
	DiagonalUp,
	DiagonalDown;

	//method
	/**
	 * @param specification
	 * @return a new {@link UniDirection} from the given specification.
	 */
	public static UniDirection fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
