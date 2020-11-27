//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IAction} has a method that does something.
 * 
 * @author Silvan Wyss
 * @month 2016-05
 * @lines 10
 */
@FunctionalInterface
public interface IAction {
	
	//method declaration
	/**
	 * Does something.
	 */
	void run();
}
