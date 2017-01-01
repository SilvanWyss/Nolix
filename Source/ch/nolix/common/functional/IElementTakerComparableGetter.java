//package declaration
package ch.nolix.common.functional;

//function interface
/**
 * An element taker comparable getter has a method that takes an element and returns a comparable object.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements a concrete element taker comparable getter works with.
 */
public interface IElementTakerComparableGetter<E, T> {

	//abstract method
	/**
	 * @param element
	 * @return a comparable object for the given element.
	 */
	Comparable<T> getValue(E element);
}
