//package declaration
package ch.nolix.common.functional;

//functional interface
/**
 * An element taker int getter has a method that takes an element and returns an int.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements a concrete element taker int getter works with.
 */
public interface IElementTakerIntGetter<E> {

	//abstract method
	/**
	 * @param element
	 * @return an int for the given element.
	 */
	public abstract int getValue(E element);
}
