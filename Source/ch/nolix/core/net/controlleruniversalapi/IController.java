//package declaration
package ch.nolix.core.net.controlleruniversalapi;

//own imports
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;

//interface
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public interface IController {
	
	//method declaration
	/**
	 * Lets the current {@link IController} run the given command.
	 * 
	 * @param command
	 */
	void runCommand(IChainedNode command);
	
	//method declaration
	/**
	 * Lets the current {@link IController} run the given commands.
	 * 
	 * @param command 
	 * @param commands
	 */
	void runCommands(IChainedNode command, IChainedNode... commands);
}
