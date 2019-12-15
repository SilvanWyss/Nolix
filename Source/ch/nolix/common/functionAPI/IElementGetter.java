//package declaration
package ch.nolix.common.functionAPI;

//functional interface
/**
 * A {@link IElementGetter} has a method that returns an element.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 20
 * @param <E> The type of the elements a {@link IElementGetter} returns.
 */
public interface IElementGetter<E> {
	
	//method declaration
	/**
	 * @return an element.
	 */
	public abstract E getOutput();
}
