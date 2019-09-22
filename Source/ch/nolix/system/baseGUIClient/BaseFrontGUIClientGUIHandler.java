//package declaration
package ch.nolix.system.baseGUIClient;

//own import
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.element.GUI.GUI;

//package-visible interface
abstract interface BaseFrontGUIClientGUIHandler {
	
	//default method
	public default boolean canRunCommand(final ChainedNode command) {
		return (command.hasHeader() && canRunCommandOfType(command.getHeader()));
	}
	
	//abstract method
	public abstract boolean canRunCommandOfType(String command);
	
	//abstract method
	public abstract BaseFrontGUIClientGUIType getGUIType();
	
	//abstract method
	public abstract GUI<?> getRefGUI();
	
	//abstract method
	public abstract void noteClose();

	//abstract method
	public abstract void run(ChainedNode command);
}
