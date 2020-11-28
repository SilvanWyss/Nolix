//package declaration
package ch.nolix.common.skillapi;

//interface
/**
 * A {@link Startable} can be started once.
 * 
 * @author Silvan Wyss
 * @month 2020-04
 * @lines 20
 */
public interface Startable {
	
	//method declaration
	/**
	 * @return true if the current {@link Startable} is started.
	 */
	boolean isStarted();
	
	//method declaration
	/**
	 * Starts the current {@link Startable}.
	 */
	void start();
}
