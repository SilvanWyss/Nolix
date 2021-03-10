//package declaration
package ch.nolix.element.gui.inputdeviceapi;

import ch.nolix.element.gui.input.Key;

//interface
public interface IKeyBoard {
	
	//method declaration
	boolean keyIsPressed(Key key);
	
	//method declaration
	boolean shiftIsLocked();
}
