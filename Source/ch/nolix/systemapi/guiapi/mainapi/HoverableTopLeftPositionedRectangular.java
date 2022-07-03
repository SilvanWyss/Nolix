//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

import ch.nolix.coreapi.geometryapi.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.coreapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
@AllowDefaultMethodsAsDesignPattern
public interface HoverableTopLeftPositionedRectangular extends TopLeftPositionedRecangular {
	
	//method declaration
	int getCursorXPosition();
	
	//method declaration
	int getCursorYPosition();
	
	//method
	default boolean isUnderCursor() {
		return containsPointRelatively(getCursorXPosition(), getCursorYPosition());
	}
}
