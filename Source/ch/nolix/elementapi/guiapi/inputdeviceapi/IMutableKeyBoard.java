//package declaration
package ch.nolix.elementapi.guiapi.inputdeviceapi;

import ch.nolix.elementapi.guiapi.inputapi.Key;

//interface
public interface IMutableKeyBoard extends IKeyBoard {
	
	//method declaration
	void noteKeyDown(Key key);
	
	//method declaration
	void noteKeyRelease(Key key);
}
