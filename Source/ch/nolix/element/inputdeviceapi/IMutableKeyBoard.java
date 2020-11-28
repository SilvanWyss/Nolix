//package declaration
package ch.nolix.element.inputdeviceapi;

//own import
import ch.nolix.element.input.Key;

//interface
public interface IMutableKeyBoard extends IKeyBoard {
	
	//method declaration
	void noteKeyPress(Key key);
	
	//method declaration
	void noteKeyRelease(Key key);
}
