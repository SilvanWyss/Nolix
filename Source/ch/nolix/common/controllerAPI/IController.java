//package declaration
package ch.nolix.common.controllerAPI;

import ch.nolix.common.chainedNode.ChainedNode;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 */
public interface IController {
	
	//abstract method
	/**
	 * Lets the current {@link IController} run the given command.
	 * 
	 * @param command
	 */
	public abstract void run(ChainedNode command);
	
	//default method
	/**
	 * Lets the current {@link IController} run the given command.
	 * 
	 * @param command
	 */
	public default void run(final String command) {
		run(ChainedNode.fromString(command));
	}
}
