//package declaration
package ch.nolix.common.canvasAPI;

//interface
public interface IOccupiableHoverableCanvas extends IHoverableCanvas {
	
	//method
	public default boolean freeAreaIsUnderCursor() {
		return !occupiedAreaIsUnderCursor();
	}
	
	//method declaration
	public abstract boolean occupiedAreaIsUnderCursor();
}
