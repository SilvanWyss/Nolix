//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IDoubleTaker} has a method that takes a double.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface IDoubleTaker {
	
	//abstract method
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	public abstract void run(double value);
}
