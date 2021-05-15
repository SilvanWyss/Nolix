//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link ILongTakerLongGetter} has a method that takes a long and returns a long.
 * 
 * @author Silvan Wyss
 * @date 2017-12-08
 * @lines 20
 */
@FunctionalInterface
public interface ILongTakerLongGetter {
	
	//method declaration
	/**
	 * @param value
	 * @return a long for the given value.
	 */
	long getOutput(long value);
}
