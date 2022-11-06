//package declaration
package ch.nolix.systemapi.guiapi.inputapi;

//interface
public interface IMouseInput<MI extends IMouseInput<MI>> extends IInput<MI> {
	
	//method declaration
	int getCursorXPosition();
	
	//method declaration
	int getCursorYPosition();
	
	//method declaration
	MouseInputType getMouseInputType();
}
