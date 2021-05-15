//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link ILongGetter} has a method that returns a long.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
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
