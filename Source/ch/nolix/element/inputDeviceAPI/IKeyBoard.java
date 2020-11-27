//package declaration
package ch.nolix.element.inputDeviceAPI;

//own import
import ch.nolix.element.input.Key;

//interface
public interface IKeyBoard {
	
	//method declaration
	boolean keyIsPressed(Key key);
	
	//method declaration
	boolean shiftIsLocked();
}
