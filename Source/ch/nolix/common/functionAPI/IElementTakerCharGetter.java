//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementTakerCharGetter} has a method that takes an element and returns a char.
 * 
 * @author Silvan Wyss
 * @month 2020-05
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerCharGetter} takes.
 */
public interface IElementTakerCharGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a char for the given element.
	 */
	public abstract char getOutput(E element);
}
