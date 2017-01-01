//package declaration
package ch.nolix.common.functional;

//function interface
/**
 * A comparable getter has a method that returns a comparable object.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <T> - The type of the elements a concrete comparable getter works with.
 */
public interface IComparableGetter<T> {

	//abstract method
	/**
	 * @param element
	 * @return a comparable object.
	 */
	Comparable<T> getOutput();
}
