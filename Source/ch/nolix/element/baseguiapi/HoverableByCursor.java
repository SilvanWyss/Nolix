//package declaration
package ch.nolix.element.baseguiapi;

import ch.nolix.common.rasterapi.TopLeftPositionedRecangular;

//interface
public interface HoverableByCursor extends TopLeftPositionedRecangular {
	
	//method declaration
	int getCursorXPosition();
	
	//method declaration
	int getCursorYPosition();
	
	//method
	default boolean isUnderCursor() {
		return containsPointRelatively(getCursorXPosition(), getCursorYPosition());
	}
}
