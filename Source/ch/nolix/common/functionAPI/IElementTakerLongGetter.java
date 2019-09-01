//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementTakerLongGetter} has a method that takes an element and returns a long.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerLongGetter} takes.
 */
public interface IElementTakerLongGetter<E> {
	
	//abstract method
	/**
	 * @param element
	 * @return a long for the given element.
	 */
	public abstract long getOutput(E element);
}
