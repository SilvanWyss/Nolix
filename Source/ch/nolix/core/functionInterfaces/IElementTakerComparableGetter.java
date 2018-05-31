//package declaration
package ch.nolix.core.functionInterfaces;

//function interface
/**
 * A {@link IElementTakerComparableGetter} takes an element and returns a {@link Comparable}.
 * 
 * @author Silvan Wyss
 * @month 2016-03
 * @lines 20
 * @param <E> The type of the element a {@link IElementTakerComparableGetter} takes.
 * @param <E2> The type of the element of the {@link Comparable}
 * a {@link IElementTakerComparableGetter} returns.
 */
public interface IElementTakerComparableGetter<E, E2> {

	//abstract method
	/**
	 * @param element
	 * @return a new {@link IElementTakerComparableGetter} for the given element.
	 */
	public abstract Comparable<E2> getValue(E element);
}
