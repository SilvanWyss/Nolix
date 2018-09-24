//package declaration
package ch.nolix.core.enums;

import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.specificationAPI.ISpecifiedEnum;

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
public enum UniDirection implements ISpecifiedEnum {
	Horizontal,
	Vertical,
	DiagonalUp,
	DiagonalDown;

	//method
	/**
	 * @param specification
	 * @return a new {@link UniDirection} from the given specification.
	 */
	public static UniDirection createFromSpecification(final DocumentNodeoid specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
