//package declaration
package ch.nolix.core.controllerInterfaces;

//own import
import ch.nolix.core.specification.Statement;

//interface
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 30
 */
public interface ILevel1Controller {

	//abstract method
	/**
	 * Lets this level 1 controller run the given command.
	 * 
	 * @param command
	 */
	public abstract void run(Statement command);
	
	//default method
	/**
	 * Lets this level 1 controller run the given command.
	 * 
	 * @param command
	 */
	public default void run(final String command) {
		run(new Statement(command));
	}
}
