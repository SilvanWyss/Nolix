//package declaration
package ch.nolix.core.controllerAPI;

import ch.nolix.core.documentNode.Statement;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 */
public interface IController {

	//abstract method
	/**
	 * Lets this controller run the given command.
	 * 
	 * @param command
	 */
	public abstract void run(Statement command);
	
	//default method
	/**
	 * Lets this controller run the given command.
	 * 
	 * @param command
	 */
	public default void run(final String command) {
		run(new Statement(command));
	}
}
