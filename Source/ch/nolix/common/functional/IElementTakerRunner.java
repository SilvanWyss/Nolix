//package declaration
package ch.nolix.common.functional;

//functional interface
/**
 * An element taker runner has a method that takes an element and does something.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> - The type of the elements a concrete element taker runner works with.
 */
public interface IElementTakerRunner<E> {

	//abstract method
	/**
	 * Does something for the given element.
	 * 
	 * @param element
	 */
	public abstract void run(E element);
}
