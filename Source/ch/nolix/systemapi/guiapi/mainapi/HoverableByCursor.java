//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.core.griduniversalapi.TopLeftPositionedRecangular;

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
