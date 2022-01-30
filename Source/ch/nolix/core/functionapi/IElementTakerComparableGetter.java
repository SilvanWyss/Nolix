//package declaration
package ch.nolix.core.functionapi;

//functional interface
/**
 * A {@link IElementTakerComparableGetter} takes an element and returns a {@link Comparable}.
 * 
 * @author Silvan Wyss
 * @date 2016-04-01
 * @lines 20
 * @param <E> is the type of the element a {@link IElementTakerComparableGetter} takes.
 * @param <E2> is the type of the element of the {@link Comparable}
 * a {@link IElementTakerComparableGetter} returns.
 */
@FunctionalInterface
public interface IElementTakerComparableGetter<E, E2> {
	
	//method declaration
	/**
	 * @param element
	 * @return a new {@link IElementTakerComparableGetter} for the given element.
	 */
	Comparable<E2> getValue(E element);
}
