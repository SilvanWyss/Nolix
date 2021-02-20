//package declaration
package ch.nolix.system.client.baseguiclient;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.element.gui.GUI;

//interface
abstract interface IFrontGUIClientGUIHandler {
	
	//method declaration
	BaseFrontGUIClientGUIType getGUIType();
	
	//method declaration
	GUI<?> getRefGUI();
	
	//method declaration
	void runGUICommand(ChainedNode pGUICommand);
}
