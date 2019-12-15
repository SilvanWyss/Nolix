//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementTakerBooleanGetter} has a method that takes an element and returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerBooleanGetter} takes.
 */
public interface IElementTakerBooleanGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a boolean for the given element.
	 */
	public abstract boolean getOutput(E element);
}
