//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IElementTakerDoubleGetter} has a method that takes an element and returns a double.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> is the type of the elements a {@link IElementTakerDoubleGetter} takes.
 */
@FunctionalInterface
public interface IElementTakerDoubleGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a double for the given element.
	 */
	double getOutput(E element);
}
