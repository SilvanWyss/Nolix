//package declaration
package ch.nolix.core.functionAPI;

//functional interface
/**
 * A {@link IElementTakerElementGetter} has a method that takes an element and returns an element.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerElementGetter} takes.
 * @param <E2> The type of the elements a {@link IElementTakerElementGetter} returns.
 */
public interface IElementTakerElementGetter<E, E2> {
	
	//abstract method
	/**
	 * @param element
	 * @return an element for the given element.
	 */
	public abstract E2 getOutput(E element);
}
