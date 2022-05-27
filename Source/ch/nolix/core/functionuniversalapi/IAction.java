//package declaration
package ch.nolix.core.functionuniversalapi;

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
