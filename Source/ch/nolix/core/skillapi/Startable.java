//package declaration
package ch.nolix.core.skillapi;

//interface
/**
 * A {@link Startable} can be started once.
 * 
 * @author Silvan Wyss
 * @date 2020-04-29
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
