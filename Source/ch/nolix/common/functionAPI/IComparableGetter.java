//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IComparableGetter} has a method that returns a {@link Comparable}.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> The type of the elements
 * the {@link Comparable}, a {@link IComparableGetter} returns, compares.
 */
public interface IComparableGetter<E> {
	
	//method declaration
	/**
	 * @param element
	 * @return a {@link Comparable}.
	 */
	Comparable<E> getOutput();
}
