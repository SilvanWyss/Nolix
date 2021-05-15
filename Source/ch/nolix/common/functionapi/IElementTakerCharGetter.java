//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IElementTakerCharGetter} has a method that takes an element and returns a char.
 * 
 * @author Silvan Wyss
 * @date 2020-05-01
 * @lines 20
 * @param <E> is the type of the elements a {@link IElementTakerCharGetter} takes.
 */
@FunctionalInterface
public interface IElementTakerCharGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a char for the given element.
	 */
	char getOutput(E element);
}
