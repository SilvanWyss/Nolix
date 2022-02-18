//package declaration
package ch.nolix.core.functionapi;

//functional interface
/**
 * A {@link IAction} has a method that does something.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
@FunctionalInterface
public interface IAction {
	
	//method declaration
	/**
	 * Does something.
	 */
	void run();
}
