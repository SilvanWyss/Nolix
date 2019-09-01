//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementTakerByteGetter} has a method that takes an element and returns a byte.
 * 
 * @author Silvan Wyss
 * @month 2019-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerByteGetter} takes.
 */
public interface IElementTakerByteGetter<E> {
	
	//abstract method
	/**
	 * @param element
	 * @return a byte for the given element.
	 */
	public abstract byte getOutput(E element);
}
