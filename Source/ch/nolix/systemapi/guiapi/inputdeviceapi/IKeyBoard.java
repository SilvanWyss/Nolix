//package declaration
package ch.nolix.systemapi.guiapi.inputdeviceapi;

//own imports
import ch.nolix.systemapi.guiapi.inputapi.Key;

//interface
public interface IKeyBoard {
	
	//method declaration
	boolean keyIsPressed(Key key);
	
	//method declaration
	boolean shiftIsLocked();
}
