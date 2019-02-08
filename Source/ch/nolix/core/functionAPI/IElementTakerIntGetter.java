//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IElementTakerIntGetter} has a method that takes an element and returns an int.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerIntGetter} takes.
 */
public interface IElementTakerIntGetter<E> {
	
	//abstract method
	/**
	 * @param element
	 * @return an int for the given element.
	 */
	public abstract int getOutput(E element);
}
