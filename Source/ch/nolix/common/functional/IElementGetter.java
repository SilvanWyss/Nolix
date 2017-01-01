//package declaration
package ch.nolix.common.functional;

//functional interface
/**
 * An element getter has a method that returns an element.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> - The type of the elements a concrete element getter works with.
 */
public interface IElementGetter<E> {

	//abstract method
	/**
	 * @return an element.
	 */
	public abstract E getOutput();
}
