//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IIntTaker} has a method that takes an int.
 * 
 * @author Silvan Wyss
 * @date 2018-08-26
 * @lines 20
 */
@FunctionalInterface
public interface IIntTaker {
	
	//method declaration
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	void run(int value);
}
