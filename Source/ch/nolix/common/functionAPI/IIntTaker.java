//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IIntTaker} has a method that takes an int.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface IIntTaker {
	
	//method declaration
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	void run(int value);
}
