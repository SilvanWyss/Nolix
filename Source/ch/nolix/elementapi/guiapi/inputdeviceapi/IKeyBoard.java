//package declaration
package ch.nolix.elementapi.guiapi.inputdeviceapi;

//own imports
import ch.nolix.element.gui.input.Key;

//interface
public interface IKeyBoard {
	
	//method declaration
	boolean keyIsPressed(Key key);
	
	//method declaration
	boolean shiftIsLocked();
}
