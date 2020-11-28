//package declaration
package ch.nolix.system.baseguiclient;

import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.element.GUI.GUI;

//interface
abstract interface IFrontGUIClientGUIHandler {
	
	//method declaration
	BaseFrontGUIClientGUIType getGUIType();
	
	//method declaration
	GUI<?> getRefGUI();
	
	//method declaration
	void runGUICommand(ChainedNode pGUICommand);
}
