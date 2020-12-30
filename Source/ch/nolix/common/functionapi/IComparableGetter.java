//package declaration
package ch.nolix.common.functionapi;

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
@FunctionalInterface
public interface IComparableGetter<E> {
	
	//method declaration
	/**
	 * @return a {@link Comparable}.
	 */
	Comparable<E> getOutput();
}
