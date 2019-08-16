//package declaration
package ch.nolix.system.GUIClientoid;

import ch.nolix.core.chainedNode.ChainedNode;

//package-visible abstract class
abstract class FrontGUIClientoidGUIHandler {
	
	//abstract method
	public final boolean canRunCommand(final ChainedNode command) {
		return (command.hasHeader() && canRunCommandOfType(command.getHeader()));
	}
	
	//abstract method
	public abstract boolean canRunCommandOfType(String command);
	
	//abstract method
	public abstract void noteClose();
	
	//abstract method
	public abstract FrontGUIClientoidGUIType getGUIType();
	
	//abstract method
	public abstract String getUpdateCommandForCounterpart();

	//abstract method
	public abstract boolean providesUpdateCommandForCounterpart();

	//abstract method
	public abstract void run(ChainedNode command);
}
