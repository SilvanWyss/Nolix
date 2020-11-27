//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementTakerIntGetter} has a method that takes an element and returns an int.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerIntGetter} takes.
 */
@FunctionalInterface
public interface IElementTakerIntGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return an int for the given element.
	 */
	int getOutput(E element);
}
