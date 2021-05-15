//package declaration
package ch.nolix.common.functionapi;

//functional interface
/**
 * A {@link IComparableGetter} has a method that returns a {@link Comparable}.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @lines 20
 * @param <E> is the type of the elements
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
