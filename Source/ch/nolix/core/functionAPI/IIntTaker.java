//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IIntTaker} has a method that takes an int.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface IIntTaker {
	
	//abstract method
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	public abstract void run(int value);
}
