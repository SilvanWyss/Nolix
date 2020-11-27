//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
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
