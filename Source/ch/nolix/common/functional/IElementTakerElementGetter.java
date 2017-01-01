//package declaration
package ch.nolix.common.functional;

//functional interface
/**
 * An element taker element getter has a method that takes an element and returns an element.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> - The type of the elements a concrete element taker element getter works with.
 */
public interface IElementTakerElementGetter<IE, OE> {

	//abstract method
	/**
	 * @param element
	 * @return an element for the given element.
	 */
	public abstract OE getOutput(IE element);
}
