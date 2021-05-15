//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IBooleanTaker} has a method that takes a boolean.
 * 
 * @author Silvan Wyss
 * @date 2018-09-01
 * @lines 20
 */
@FunctionalInterface
public interface IBooleanTaker {
	
	//method declaration
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	void run(boolean value);
}
