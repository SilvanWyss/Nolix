//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * An element taker has a method that takes an element.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> The type of the elements an element taker takes.
 */
public interface IElementTaker<E> {

	//abstract method
	/**
	 * Takes the given element.
	 * 
	 * @param element
	 */
	public abstract void run(E element);
}
