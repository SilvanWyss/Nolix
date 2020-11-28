//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IElementTakerBooleanGetter} has a method that takes an element and returns a boolean.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the elements a {@link IElementTakerBooleanGetter} takes.
 */
@FunctionalInterface
public interface IElementTakerBooleanGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a boolean for the given element.
	 */
	boolean getOutput(E element);
}
