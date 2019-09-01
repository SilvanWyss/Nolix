//package declaration
package ch.nolix.element.elementEnums;

import ch.nolix.common.node.BaseNode;
import ch.nolix.element.baseAPI.IElementEnum;

//enum
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 30
 */
public enum ExtendedContentPosition implements IElementEnum {
	LeftTop,
	Left,
	LeftBottom,
	Top,
	Center,
	Bottom,
	RightTop,
	Right,
	RightBottom,
	Free;
	
	//constant
	public static final String TYPE_NAME = "ExtendedContentPosition";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link ExtendedContentPosition} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static ExtendedContentPosition fromSpecification(final BaseNode specification) {
		return valueOf(specification.getOneAttributeAsString());
	}
}
