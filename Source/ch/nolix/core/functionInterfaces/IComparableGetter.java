//package declaration
package ch.nolix.core.functionInterfaces;

//functional interface
/**
 * A comparable getter has a method that returns a comparable object.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> - The type of the elements the comparable object, a comparable getter returns, compares.
 */
public interface IComparableGetter<E> {

	//abstract method
	/**
	 * @param element
	 * @return a comparable object.
	 */
	Comparable<E> getOutput();
}
