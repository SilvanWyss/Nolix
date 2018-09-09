//package declaration
package ch.nolix.core.enums;

import ch.nolix.core.specificationAPI.ISpecifiedEnum;

//enum
/**
 * A direction defines the way
 * from a square's point to another point of the square.
 * 
 * A direction depends on the order of the start point and the end point.
 * 
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 20
 */
public enum Direction implements ISpecifiedEnum {
	LeftToRight,
	RightToLeft,
	TopToBottom,
	BottomToTop,
	TopLeftToBottomRight,
	BottomRightToTopLeft,
	TopRightToBottomLeft,
	BottomLeftToTopRight
}
