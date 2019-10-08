//package declaration
package ch.nolix.system.baseGUIClient;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.element.GUI.GUI;

//package-visible interface
abstract interface IFrontGUIClientGUIHandler {
	
	//abstract method
	public abstract BaseFrontGUIClientGUIType getGUIType();
	
	//abstract method
	public abstract GUI<?> getRefGUI();
	
	//abstract method
	public abstract void noteClose();
	
	//abstract method
	public abstract void runGUICommand(ChainedNode pGUICommand);
}
