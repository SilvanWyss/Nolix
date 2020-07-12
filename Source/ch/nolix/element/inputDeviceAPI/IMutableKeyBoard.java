//package declaration
package ch.nolix.element.inputDeviceAPI;

//own import
import ch.nolix.element.input.Key;

//interface
public interface IMutableKeyBoard extends IKeyBoard {
	
	//method declaration
	public void noteKeyPress(Key key);
	
	//method declaration
	public void noteKeyRelease(Key key);
}
