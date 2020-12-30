//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link ILongTaker} has a method that takes a long.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 */
@FunctionalInterface
public interface ILongTaker {
	
	//method declaration
	/**
	 * Takes the given value.
	 * 
	 * @param value
	 */
	void run(long value);
}
