//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * An element taker element getter has a method that takes an element and returns an element.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <I> - The type of the elements an element taker element getter takes.
 * @param <O> - The type of the elements an element taker element getter returns.
 */
public interface IElementTakerElementGetter<I, O> {

	//abstract method
	/**
	 * @param element
	 * @return an element for the given element.
	 */
	public abstract O getOutput(I element);
}
