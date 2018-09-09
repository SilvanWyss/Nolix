//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IIntTaker} has a method that takes an integer.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 10
 */
public interface IIntTaker {

	//abstract method
	/**
	 * Lets the current {@link IIntTaker} take the given value.
	 * 
	 * @param value
	 */
	public abstract void run(int value);
}
