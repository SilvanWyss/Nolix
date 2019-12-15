//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link ILongTakerLongGetter} has a method that takes a long and returns a long.
 * 
 * @author Silvan Wyss
 * @month 2017-12
 * @lines 20
 */
public interface ILongTakerLongGetter {
	
	//method declaration
	/**
	 * @param value
	 * @return a long for the given value.
	 */
	public abstract long getOutput(long value);
}
