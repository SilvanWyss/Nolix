//package declaration
package ch.nolix.core.functionInterfaces;

//functional interface
/**
 * An element taker double getter has a method that takes an element and returns a double.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements an element taker double getter takes.
 */
public interface IElementTakerDoubleGetter<E> {

	//abstract method
	/**
	 * @param element
	 * @return a double for the given element.
	 */
	public abstract double getOutput(E element);
}
