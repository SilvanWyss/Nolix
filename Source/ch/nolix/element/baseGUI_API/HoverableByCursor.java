//package declaration
package ch.nolix.element.baseGUI_API;

//own import
import ch.nolix.common.rasterAPI.TopLeftPositionedRecangular;

//interface
public interface HoverableByCursor extends TopLeftPositionedRecangular {
	
	//method declaration
	public abstract int getCursorXPosition();
	
	//method declaration
	public abstract int getCursorYPosition();
	
	//method
	public default boolean isUnderCursor() {
		return containsPointRelatively(getCursorXPosition(), getCursorYPosition());
	}
}
