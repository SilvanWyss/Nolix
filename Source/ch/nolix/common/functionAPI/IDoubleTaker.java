//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IDoubleTaker} has a method that takes a double.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 20
 */
public interface IDoubleTaker {
	
	//method declaration
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	public abstract void run(double value);
}
