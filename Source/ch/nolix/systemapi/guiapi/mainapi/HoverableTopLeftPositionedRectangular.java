//package declaration
package ch.nolix.systemapi.guiapi.mainapi;

//own imports
import ch.nolix.core.griduniversalapi.TopLeftPositionedRecangular;
import ch.nolix.core.programatom.marker.AllowDefaultMethodsAsDesignPattern;

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
