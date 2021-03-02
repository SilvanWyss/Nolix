//package declaration
package ch.nolix.common.controllerapi;

import ch.nolix.common.document.chainednode.ChainedNode;

//interface
/**
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 30
 */
public interface IController {
	
	//method declaration
	/**
	 * Lets the current {@link IController} run the given command.
	 * 
	 * @param command
	 */
	void run(ChainedNode command);
	
	//method
	/**
	 * Lets the current {@link IController} run the given commands.
	 * 
	 * @param commands
	 */
	default void run(final ChainedNode... commands) {
		
		//Iterates the given commands.
		for (final var c : commands) {
			run(c);
		}
	}
}
