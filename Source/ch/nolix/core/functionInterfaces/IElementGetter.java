//package declaration
package ch.nolix.core.functionInterfaces;

//functional interface
/**
 * An element getter has a method that returns an element.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> - The type of the elements an element getter returns.
 */
public interface IElementGetter<E> {

	//abstract method
	/**
	 * @return an element.
	 */
	public abstract E getOutput();
}
