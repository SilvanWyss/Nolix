//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A long taker long getter has a method that takes a long and returns a long.
 * 
 * @author Silvan Wyss
 * @month 2017-12
 * @lines 20
 */
public interface ILongTakerLongGetter {

	//abstract method
	/**
	 * @param value
	 * @return a long for the given value.
	 */
	public abstract long getOutput(long value);
}
