//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IElementTakerLongGetter} has a method that takes an element and returns a long.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> is the type of the elements a {@link IElementTakerLongGetter} takes.
 */
@FunctionalInterface
public interface IElementTakerLongGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a long for the given element.
	 */
	long getOutput(E element);
}
