//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link ILongGetter} has a method that returns a long.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 10
 */
@FunctionalInterface
public interface ILongGetter {
	
	//method declaration
	/**
	 * @return a long.
	 */
	long getOutput();
}
