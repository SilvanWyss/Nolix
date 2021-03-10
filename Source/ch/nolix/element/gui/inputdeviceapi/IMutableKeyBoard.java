//package declaration
package ch.nolix.element.gui.inputdeviceapi;

import ch.nolix.element.gui.input.Key;

//interface
public interface IMutableKeyBoard extends IKeyBoard {
	
	//method declaration
	void noteKeyPress(Key key);
	
	//method declaration
	void noteKeyRelease(Key key);
}
