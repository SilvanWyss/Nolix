//package declaration
package ch.nolix.system.baseGUIClient;

//own import
import ch.nolix.common.chainedNode.ChainedNode;

//package-visible abstract class
abstract class BaseFrontGUIClientGUIHandler {
	
	//abstract method
	public final boolean canRunCommand(final ChainedNode command) {
		return (command.hasHeader() && canRunCommandOfType(command.getHeader()));
	}
	
	//abstract method
	public abstract boolean canRunCommandOfType(String command);
	
	//abstract method
	public abstract void noteClose();
	
	//abstract method
	public abstract BaseFrontGUIClientGUIType getGUIType();
	
	//abstract method
	public abstract String getUpdateCommandForCounterpart();

	//abstract method
	public abstract boolean providesUpdateCommandForCounterpart();

	//abstract method
	public abstract void run(ChainedNode command);
	
	//TODO
	abstract String getSetViewAreaSizeCommand();
}
