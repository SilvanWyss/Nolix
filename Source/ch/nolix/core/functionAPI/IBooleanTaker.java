//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IBooleanTaker} has a method that takes a boolean.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface IBooleanTaker {
	
	//abstract method
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	public abstract void run(boolean value);
}
