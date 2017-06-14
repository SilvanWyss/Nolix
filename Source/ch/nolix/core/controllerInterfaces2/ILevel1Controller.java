/*
 * file:	ILevel1Controller.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	30
 */

//package declaration
package ch.nolix.core.controllerInterfaces2;

//own import
import ch.nolix.core.specification.Statement;

//interface
/**
 * A level 1 controller can run commands.
 * 
 * The default methods of this interface need not to be overwritten.
 */
public interface ILevel1Controller {

	//abstract method
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 */
	public abstract void run(Statement command);
	
	//default method
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 */
	public default void run(String string) {
		run(new Statement(string));
	}
}
