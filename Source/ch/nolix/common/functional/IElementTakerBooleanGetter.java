//package declaration
package ch.nolix.common.functional;

//functional interface
/**
 * An element taker boolean getter has a method that takes an element and returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements a concrete element taker boolean getter works with.
 */
public interface IElementTakerBooleanGetter<E> {

	//abstract method
	/**
	 * @param element
	 * @return a boolean for the given element.
	 */
	public abstract boolean getOutput(E element);
}
