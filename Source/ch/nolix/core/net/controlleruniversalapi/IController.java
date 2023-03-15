//package declaration
package ch.nolix.core.net.controlleruniversalapi;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;

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
	void run(ChainedNode command);
	
	//method declaration
	/**
	 * Lets the current {@link IController} run the given commands.
	 * 
	 * @param command 
	 * @param commands
	 */
	void run(ChainedNode command, ChainedNode... commands);
}
