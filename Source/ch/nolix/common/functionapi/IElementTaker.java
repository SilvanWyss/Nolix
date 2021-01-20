//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IElementTaker} has a method that takes an element.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> is the type of the elements a {@link IElementTaker} takes.
 */
@FunctionalInterface
public interface IElementTaker<E> {
	
	//method declaration
	/**
	 * Takes the given element.
	 * 
	 * @param element
	 */
	void run(E element);
}
