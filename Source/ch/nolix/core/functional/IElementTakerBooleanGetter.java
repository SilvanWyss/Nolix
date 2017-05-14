//package declaration
package ch.nolix.core.functional;

//functional interface
/**
 * An element taker boolean getter has a method that takes an element and returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements an element taker boolean getter takes.
 */
public interface IElementTakerBooleanGetter<E> {

	//abstract method
	/**
	 * @param element
	 * @return a boolean for the given element.
	 */
	public abstract boolean getOutput(E element);
}
