//package declaration
package ch.nolix.systemapi.guiapi.inputdeviceapi;

import ch.nolix.systemapi.guiapi.inputapi.Key;

//interface
public interface IMutableKeyBoard extends IKeyBoard {
	
	//method declaration
	void noteKeyDown(Key key);
	
	//method declaration
	void noteKeyRelease(Key key);
}
