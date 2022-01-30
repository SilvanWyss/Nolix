//package declaration
package ch.nolix.system.client.baseguiclient;

import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.element.gui.base.GUI;

//interface
abstract interface IFrontGUIClientGUIHandler {
	
	//method declaration
	BaseFrontGUIClientGUIType getGUIType();
	
	//method declaration
	GUI<?> getRefGUI();
	
	//method declaration
	void runGUICommand(ChainedNode pGUICommand);
}
