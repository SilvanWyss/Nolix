//package declaration
package ch.nolix.element.inputDeviceAPI;

//own import
import ch.nolix.element.input.Key;

//interface
public interface IKeyBoard {
	
	//method declaration
	public abstract boolean keyIsPressed(Key key);
	
	//method declaration
	public abstract boolean shiftIsLocked();
}
