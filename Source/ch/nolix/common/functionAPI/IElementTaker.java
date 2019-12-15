//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementTaker} has a method that takes an element.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTaker} takes.
 */
public interface IElementTaker<E> {
	
	//method declaration
	/**
	 * Takes the given element.
	 * 
	 * @param element
	 */
	public abstract void run(E element);
}
