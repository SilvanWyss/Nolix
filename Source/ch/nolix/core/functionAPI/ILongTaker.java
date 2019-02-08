//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link ILongTaker} has a method that takes a long.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 */
public interface ILongTaker {
	
	//abstract method
	/**
	 * Takes the given value.
	 * 
	 * @return value
	 */
	public abstract void run(long value);
}
