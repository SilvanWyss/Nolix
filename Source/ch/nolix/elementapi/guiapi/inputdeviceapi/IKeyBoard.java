//package declaration
package ch.nolix.elementapi.guiapi.inputdeviceapi;

import ch.nolix.elementapi.guiapi.inputapi.Key;

//interface
public interface IKeyBoard {
	
	//method declaration
	boolean keyIsPressed(Key key);
	
	//method declaration
	boolean shiftIsLocked();
}
