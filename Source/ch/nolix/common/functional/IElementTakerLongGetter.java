//package declaration
package ch.nolix.common.functional;

//functional interface
/**
 * An element taker long getter has a method that takes an element and returns a long.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements a concrete element taker long getter works with.
 */
public interface IElementTakerLongGetter<E> {

	//abstract method
	/**
	 * @param element
	 * @return a long for the given element.
	 */
	public abstract int getOutput(E element);
}
