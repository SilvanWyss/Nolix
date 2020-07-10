//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.element.GUI.GUI;

//interface
abstract interface IFrontGUIClientGUIHandler {
	
	//method declaration
	public abstract BaseFrontGUIClientGUIType getGUIType();
	
	//method declaration
	public abstract GUI<?> getRefGUI();
	
	//method declaration
	public abstract void runGUICommand(ChainedNode pGUICommand);
}
